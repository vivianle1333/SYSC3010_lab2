# Source: https://pymotw.com/2/socket/udp.html

import socket, sys, time

host = sys.argv[1]
textport = sys.argv[2]
num = sys.argv[3]
num_msgs = int(num)


s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
port = int(textport)
server_address = (host, port)

while num_msgs is not 0:
    print ("Enter data to transmit: ENTER to quit")
    data = sys.stdin.readline().strip()
    if not len(data):
        break
    s.sendto(data.encode('utf-8'), server_address)
    num_msgs = num_msgs - 1
    buf, address = s.recvfrom(port)
    print(buf.decode('utf-8'))

s.shutdown(1)

