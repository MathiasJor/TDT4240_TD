import json
import socketserver

class ConnectedUser:
	def __init__(self, id, ip):
		self.id = id
		self.ip = ip

class ServerData:
	connectedUsers = []
	userStorageDir = ''
	nextUserId = 0
	usersLookingForGame = []
	games = []

serverData = ServerData

class TCPHandler(socketserver.BaseRequestHandler):

	def handle(self):
		self.handleResponse(self.request.recv(1024).strip())

	def handleResponse(self, res):
		global serverData
		response = json.loads(res)
		print(response)

		if(response['type'] == 'connect'):
			if(response['userId'] == 'null'):
				serverData.connectedUsers.append(ConnectedUser(serverData.nextUserId, self.client_address[0]))
				serverData.nextUserId += 1
				print(serverData.nextUserId)
				self.request.sendall(bytes("You have successfully connected the client! Your userId is " + str(serverData.nextUserId - 1), 'utf-8'))
			else:
				serverData.connectedUsers.append(response['userId'])
				self.request.sendall(bytes("You have successfully connected the client!", 'utf-8'))				
		elif(response['type'] == 'newGame'):
			if response["userId"] == "null":
				return
			if(serverData.usersLookingForGame.__contains__(response['userId'])):
				self.request.sendall(bytes(self.createNewGame(), 'utf-8'))
			else:			
				serverData.usersLookingForGame.append(response['userId'])
				self.request.sendall(bytes(self.createNewGame(), 'utf-8'))
		elif(response['type'] == 'endTurn'):
			#TODO: Implement response when a user has ended their turn
			self.startTurn(response['nextUser'], response['gameId'])
		elif(response['type'] == 'getGames'):
			self.getUserGames(response['userId'])

	def startTurn(self, receiver, gameid):
		#TODO: Implement code to send response to desired user
		return "{}"
	
	def getUserGames(self, userId):
		return "{}"
	
	def createNewGame(self):
		global serverData
		if len(serverData.usersLookingForGame) > 1:
			#OK, so we are enough players to create a new game!
			
			#First we remove the first user from the looking for game pool
			user1 = serverData.usersLookingForGame.pop(0)
			# Then we do the same for the second user. No proper matchmaking here!
			user2 = serverData.usersLookingForGame.pop(0)

			serverData.games.append("{users:[{id: "+ str(user1) + ", health: 30, isTurn: true}, {id: " + str(user2) + ", health: 30, isTurn: false}], id:" + str(len(serverData.games)) + "}")
			return serverData.games[len(serverData.games) - 1]
		print("Not enough players looking for game yet...")
		return "Not enough players in pool, please wait..."
		
def startUp():
	HOST, PORT = 'localhost', 9999
	print("Starting server")
	server = socketserver.TCPServer((HOST, PORT), TCPHandler)
	server.serve_forever()
	print("Server stopped")
startUp()