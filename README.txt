To interact with the Game Engine, you need to provide a class that implements the PlayerTeam interface.

You should change the main method code in GameEngine.java to use your class instead of TestTeam.

For testing purposes you can change the constant FULL_VISION in the Game Engine class to toggle between printing the entire maze and only printing to the screen locations that your robots can scan. (This does not change anyting except the printed output.)

You can change the Maze that is used in GameEngine:
-- ExampleMaze() is the previous small Maze
-- DiskMaze("largetest.maze") is a larger 12x11 test Maze

Note that in the text-based interface
Robots are represented by the Letter that starts the name of the Robot.
Coins are represented as follows:
  * Bronze
  @ Silver
  # Gold
  $ Diamond


============================


The Game Engine will call your chooseTeam function at the start of the game. The boolean teamOne variable tells you whether or not you are Team #1 or Team #2. (The input variable will be false if you are Team #2.) Additionally, the GameState that you are given will provide some basic information about the game setup including the size of the maze and the number of coins available. 

Each turn, the Game Engine will call your requestCommands function. You will be given a list of all Locations that you have information about. If a location is currently within scanner range of one of your robots, then you will have full information about this location (also, the isScanned function in the location will return true so you you can easily tell if a location is in scanner range of your robots). If a location is not in scanner range of one of your robots, then the location will only be in the list if a WolfBot, BatBot, or SkunkBot senses a Robot, Coin or Minotaur in that location. 

Your requestCommands function should return one Command object for each Robot in the robotsAwaitingCommand list. This list will typically include all of your robots, but in the case where a RabbitBot (or FalconBot) is getting an extra turn, it will only include the robots receiving this extra turn. (Similarly, a HippoBot will be missing from the list if the HippoBot just moved the previous turn.) If you decide not to return a Command for a particular Robot, that Robot will stand still and do nothing during the turn.

Please don't hesitate to email me if anything is unclear or if you have any questions.