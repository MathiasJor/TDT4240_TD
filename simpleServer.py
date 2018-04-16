import json
import socketserver
import azure as az

class TCPHandler(socketserver.BaseRequestHandler):
	connectedUsers = []
	userStorageDir = ''
	usersLookingForGame = [0, 1]
	games = []

	def handle(self):
		self.handleResponse(self.request.recv(1024).strip())

	def handleResponse(self, res):
		response = json.loads(res)
		print(response)

		if(response['type'] == 'connect'):
			self.connectedUsers.append(response['userId'])
			self.request.sendall(bytes("You have successfully connected the client!", 'utf-8'))
		elif(response['type'] == 'newGame'):
			if(self.usersLookingForGame.__contains__(response['userId'])):
				self.request.sendall(bytes(self.createNewGame(), 'utf-8'))
			else:			
				self.usersLookingForGame.append(response['userId'])
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
		if len(self.usersLookingForGame) > 1:
			#OK, so we are enough players to create a new game!
			
			#First we remove the first user from the looking for game pool
			user1 = self.usersLookingForGame.pop(0)
			# Then we do the same for the second user. No proper matchmaking here!
			user2 = self.usersLookingForGame.pop(0)

			self.games.append("{users:[{id: "+ str(user1) + ", health: 30, isTurn: true}, {id: " + str(user2) + ", health: 30, isTurn: false}], id:" + str(len(self.games)) + "}")
			return self.games[len(self.games) - 1]
		print("Not enough players looking for game yet...")
		return "Not enough players in pool, please wait..."
		
def startUp():
	HOST, PORT = 'localhost', 9999
	print("Starting server")
	server = socketserver.TCPServer((HOST, PORT), TCPHandler)
	server.serve_forever()
	print("Server stopped")
startUp()