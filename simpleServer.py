import json
import socketserver


class ConnectedUser:
	def __init__(self, id, ip):
		self.id = id
		self.ip = ip

class GameUser:
	def __init__(self, id, health, gold, isTurn):
		self.id = id
		self.health = health
		self.gold = gold
		self.isTurn = isTurn

class Game:
	def __init__(self, user1, user2, id):
		self.user1 = user1
		self.user2 = user2
		self.id = id
		self.turnData = '""'

	def toString(self):
		if self.turnData == '' or self.turnData == None:
			self.turnData = '""'
		return '{"users":[{"id": '+ str(self.user1.id) + ', "health":'+str(self.user1.health) + ', "gold":' + str(self.user1.gold) + ', "isTurn":' + str(self.user1.isTurn).lower() + '}, {"id": ' + str(self.user2.id) + ', "health": ' + str(self.user2.health) + ', "gold":' + str(self.user2.gold) + ', "isTurn": ' + str(self.user2.isTurn).lower() + "}],\"turnData\":" + str(self.turnData) + ", \"id\":" + str(self.id) + "}"

class ServerData:
	connectedUsers = []
	userStorageDir = ''
	nextUserId = 0
	nextGameId = 0
	usersLookingForGame = []
	games = []

	def __init__(self, serverDir):
		self.userStorageDir = serverDir

	def findUserGames(self, userid):
		rs = '{ "type":"userGames", "games":['
		for i in range(len(self.games)):
			if(self.games[i].user1.id == userid or self.games[i].user2.id == userid):
				rs = rs + self.games[i].toString() + ','
		rs = rs + ']}'
		return rs

	def saveToFile(self):
		f = open(self.userStorageDir + "/serverData.json", 'w')
		writeString = '{"games":[\n' + self.games[0].toString()
		for i in range(1, len(self.games)):
			writeString = writeString + ",\n" + self.games[i].toString()
		writeString = writeString + '], "nextUserId": ' + str(self.nextUserId) + ', "nextGameId":' + str(self.nextGameId) + "}"
		try:
			f.write(writeString)
		except Exception:
			print("Save unsuccessful!")
			return
		finally:
			print("Saved")
	
	def loadFromFile(self):
		f = open(self.userStorageDir + "/serverData.json", 'r')
		data = json.loads(f.read())
		self.nextUserId = data['nextUserId']
		self.nextGameId = data['nextGameId']
		for i in range(len(data['games'])):
			game = data['games'][i]
			user1 = GameUser(game["users"][0]["id"], game["users"][0]["health"], game["users"][0]["gold"], game["users"][0]["isTurn"])
			user2 = GameUser(game["users"][1]["id"], game["users"][1]["health"], game["users"][1]["gold"], game["users"][1]["isTurn"])
			self.games.append(Game(user1, user2, game["id"]))




		

serverData = ServerData('D:/AndroidDevelopmentProjects/TDT4240_TD')

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
				self.request.sendall(bytes('{ "type":"connectResponse", "userId":' + str(serverData.nextUserId - 1) + '}', 'utf-8'))
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
			self.request.sendall(bytes(self.startTurn(response['userId'], response['gameId'], response['userHealth'], response['userGold'], response["sentCreatures"]), 'utf-8'))
		elif(response['type'] == 'getGames'):
			self.request.sendall(bytes(self.getUserGames(response['userId']), 'utf-8'))
		elif(response['type'] == 'serverSave'):
			serverData.saveToFile()
		elif(response['type'] == 'serverLoad'):
			serverData.loadFromFile()

	def startTurn(self, userid, gameid, userHealth, userGold, sentCreatures):
		global serverData
		game = serverData.games[gameid]
		if game.user1.id == userid or game.user2.id == userid:
			if game.user1.id == userid and game.user1.isTurn:
				game.user1.isTurn = False
				game.user1.health = userHealth
				game.user1.gold = userGold
				game.user2.isTurn = True
			elif game.user2.id == userid and game.user2.isTurn:
				game.user1.isTurn = True
				game.user2.isTurn = False
				game.user2.health = userHealth
				game.user2.gold = userGold
			else:
				return '{"type":"endTurnError", "message":"Something is wrong here"}'

			game.turnData = sentCreatures
			serverData.games[gameid] = game
			# Wish I could send info to the other user that it is his turn, but he just has to refresh his games...
			return '{"type":"endTurnResponse"}'
		return '{"type":"endTurnError", "message":"Player id not in game"}'
	def getUserGames(self, userId):
		global serverData
		return serverData.findUserGames(userId)
	
	def createNewGame(self):
		global serverData
		if len(serverData.usersLookingForGame) > 1:
			#OK, so we are enough players to create a new game!
			
			#First we remove the first user from the looking for game pool
			user1id = serverData.usersLookingForGame.pop(0)
			# Then we do the same for the second user. No proper matchmaking here!
			user2id = serverData.usersLookingForGame.pop(0)

			# Change the start values for health and gold here
			user1 = GameUser(user1id, 30, 500, True)
			user2 = GameUser(user2id, 30, 500, False)

			serverData.games.append(Game(user1, user2, serverData.nextGameId))
			serverData.nextGameId += 1
			return serverData.games[serverData.nextGameId - 1].toString()
		print("Not enough players looking for game yet...")
		return '{"type":"notEnoughPlayers", "message":"Not enough players in pool"}'
		
def startUp():
	HOST, PORT = 'localhost', 9999
	print("Starting server")
	server = socketserver.TCPServer((HOST, PORT), TCPHandler)
	server.serve_forever()
	print("Server stopped")
startUp()
