import java.net.*;
import java.util.Scanner;

public class UDPSender {
	private final static int PACKETSIZE = 100 ;

	public static void main(String[] args) 
   {
	      // Check the arguments
	      if( args.length != 3 )
	      {
	         System.out.println( "usage: java UDPSender host port n" ) ;
	         return ;
	      }
	      DatagramSocket socket = null ;
	      try
	      {
	         // Convert the arguments first, to ensure that they are valid
	         InetAddress host = InetAddress.getByName( args[0] ) ;
	         int port         = Integer.parseInt( args[1] ) ;
			 int num_msgs     = Integer.parseInt( args[2] ) ;
	         socket = new DatagramSocket() ;
     
	         Scanner in;
	         in = new Scanner (System.in);
	         String message = null;
	         while (true)
	         {
					while (num_msgs > 0) {
						System.out.println("Enter text to be sent, ENTER to quit ");
						message = in.nextLine();
						if (message.length()==0) break;
						num_msgs--;
					
						byte [] data = message.getBytes() ;
						DatagramPacket packet = new DatagramPacket( data, data.length, host, port ) ;
						socket.send( packet ) ;
						
						DatagramPacket packet_r = new DatagramPacket( new byte[PACKETSIZE], PACKETSIZE ) ;
						socket.receive( packet_r ) ;
						System.out.println(new String(packet_r.getData()).trim());
					}
					break;
	         } 
	         System.out.println ("Closing down");
	      }
	      catch( Exception e )
	      {
	         System.out.println( e ) ;
	      }
	      finally
	      {
	         if( socket != null )
	            socket.close() ;
      }
   }
}

