# Connect4-Web-Service
A Spring-boot Webservice that uses a HTTP to communicate Connect 4 game logic to a seperate GUI
Game Objective
Connect 4 is a two-player vertical board game where players take turns placing pieces on the board.
The first player to connect four pieces (vertically, horizontally or diagonally) is the winner. </br>
Summary:
All 69 possible winning combinations are stored in a text file. Each turn, the client GUI makes a request to the
server.
This request sends the current status of the game board and asks if a winning combination has been found and which player has won the game.
