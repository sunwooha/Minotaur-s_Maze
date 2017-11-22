import java.util.*;

public class CSS implements PlayerTeam{
    boolean teamOne;
    GameState theState;
    Random rand;
    
    // List of robots on the team (getCommand() only works if this is defined as a list of CSSRobots, not just Robots)
    List<CSSRobot> teamComp = new ArrayList<CSSRobot>();
    CSSRobot bot1;
    CSSRobot bot2;
    CSSRobot bot3;
    CSSRobot bot4;

    /**
     * chooseTeam is called once at the very start of the game
     * This function should return the robots the player wants to use during the game
     * */
    // Creates two lists with the same contents: a list of CSSRobots (teamComp) and a list of Robots (theCSSTeam)
    // This is necessary for getCommand() to work
    public List<Robot> chooseTeam(boolean teamOne, GameState state){
		rand = new Random();
        this.teamOne = teamOne;
        this.theState = state;
        List<Robot> theCSSTeam = new ArrayList<Robot>();
        bot1 = new CoreBot(7, teamOne);
        bot2 = new CoreBot(8, teamOne);
        bot3 = new CoreBot(9, teamOne);
        bot4 = new CoreBot(10, teamOne);
        teamComp.add(bot1);
        teamComp.add(bot2);
        teamComp.add(bot3);
        teamComp.add(bot4);
        theCSSTeam.add(bot1);
        theCSSTeam.add(bot2);
        theCSSTeam.add(bot3);
        theCSSTeam.add(bot4);
        return theCSSTeam;
    }
    
    /*
    // Asks a CSSRobot for its desired command
    public Command getCommand(Robot r, List<Location> info, GameState state) {
		Command com;
		
		if (r == bot1) {
			com = bot1.choose_command(info, state);
		}
		else if (r == bot2) {
			com = bot2.choose_command(info, state);
		}
		else if (r == bot3) {
			com = bot3.choose_command(info, state);
		}
		else if (r == bot4) {
			com = bot4.choose_command(info, state);
		}
		else {
			com = null;
		}
		
		return com;
	}
	* */

    /** 
     * requestCommands is called each turn
     * This function should return one Command for each robot awaiting command
     * */
    // Returns a list of commands obtained by getCommand()
    public List<Command> requestCommands(List<Location> information, List<Robot> robotsAwaitingCommand, GameState state){
		List<Command> ourCommands = new ArrayList<Command>();
		
		for(Robot r: robotsAwaitingCommand){
			int num = rand.nextInt(4);
			int flip = rand.nextInt(2);
			DirType dir = null;

			switch(num){
				case 0: dir = DirType.North;
				break;
				case 1: dir = DirType.South;
				break;
				case 2: dir = DirType.East;
				break;
				case 3: dir = DirType.West;
				break;
			}
	    
			if (flip ==0){
				ourCommands.add(new CommandMove(r, dir));
			}
			else {
				ourCommands.add(new CommandMove(r, dir));
			}
		}
		
		/*
		for(Robot bot : robotsAwaitingCommand) {
			Command com = this.getCommand(bot, information, state);
			ourCommands.add(com);
		}
		*/
		
		return ourCommands;
    }
}
