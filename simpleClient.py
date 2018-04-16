import socket
<<<<<<< HEAD
import json

userid = 'null'

HOST, PORT = "localhost", 9999
data = '{"type":"connect", "userId": "null"}'
=======

HOST, PORT = "localhost", 9999
<<<<<<< HEAD
data = '{"type":"newGame", "userId": 0}'
>>>>>>> started implementing a simple server in python, with a simple python client to test the responses
=======
data = '{"type":"connect", "userId": "null"}'
>>>>>>> Crawling ahead with updates to server. BUilding response structure

# Create a socket (SOCK_STREAM means a TCP socket)
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
    # Connect to server and send data
    sock.connect((HOST, PORT))
<<<<<<< HEAD
    sock.sendall(bytes(data,'utf-8'))
=======
    sock.sendall(data + "\n")
>>>>>>> started implementing a simple server in python, with a simple python client to test the responses

    # Receive data from the server and shut down
    received = sock.recv(1024)
finally:
    sock.close()

print ("Sent:     {}".format(data))
print ("Received: {}".format(received))
<<<<<<< HEAD
res = json.loads(received)
userid = res['userId']
string = ''
while not string == 'end':
    string = input()
    if string == 'newGame':
        data = '{"type":"newGame", "userId": ' + str(userid) + '}'
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        try:
            # Connect to server and send data
            sock.connect((HOST, PORT))
            sock.sendall(bytes(data, "utf-8"))

            # Receive data from the server and shut down
            received = sock.recv(1024)
        finally:
            sock.close()
        print(received)
    elif string == 'getGames':
        data = '{"type":"getGames", "userId": ' + str(userid) + '}'
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        try:
            # Connect to server and send data
            sock.connect((HOST, PORT))
            sock.sendall(bytes(data, "utf-8"))

            # Receive data from the server and shut down
            received = sock.recv(1024)
        finally:
            sock.close()
        print(received)
    elif string == 'serverSave':
        data = '{"type":"serverSave", "userId": ' + str(userid) + '}'
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        try:
            # Connect to server and send data
            sock.connect((HOST, PORT))
            sock.sendall(bytes(data, "utf-8"))

            # Receive data from the server and shut down
            received = sock.recv(1024)
        finally:
            sock.close()
        print(received)
    elif string == 'serverLoad':
        data = '{"type":"serverLoad", "userId": ' + str(userid) + '}'
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        try:
            # Connect to server and send data
            sock.connect((HOST, PORT))
            sock.sendall(bytes(data, "utf-8"))

            # Receive data from the server and shut down
            received = sock.recv(1024)
        finally:
            sock.close()
        print(received)
=======
input()
>>>>>>> started implementing a simple server in python, with a simple python client to test the responses
