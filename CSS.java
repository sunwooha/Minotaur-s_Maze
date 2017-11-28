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
        bot2 = new WolfBot(8, teamOne);
        bot3 = new SkunkBot(9, teamOne);
        bot4 = new FalconBot(10, teamOne);
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

    /** 
     * requestCommands is called each turn
     * This function should return one Command for each robot awaiting command
     * */
    // Returns a list of commands obtained by getCommand()
    public List<Command> requestCommands(List<Location> information, List<Robot> robotsAwaitingCommand, GameState state){
		List<Command> ourCommands = new ArrayList<Command>();
		
		for(Robot r: robotsAwaitingCommand){
			if (canPickup(r) == true){
				ourCommands.add(new CommandCoin(r));
			}
			else {
				int num = rand.nextInt(4);
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
				ourCommands.add(new CommandMove(r, dir));
			}
		}
		return ourCommands;
    }
    
    // Lists what coin types each robot can pick up
    public List<CoinType> getCoinTypes(Robot r) {
		
		List<CoinType> coinTypes = new ArrayList<CoinType>();
		
		if (r.getModel() == ModelType.WolfBot || r.getModel() == ModelType.SkunkBot) {
			coinTypes.add(CoinType.Bronze);
			coinTypes.add(CoinType.Silver);
		}
		else if (r.getModel() == ModelType.FalconBot) {
			coinTypes.add(CoinType.Bronze);
			coinTypes.add(CoinType.Silver);
			coinTypes.add(CoinType.Gold);
		}
		else if (r.getModel() == ModelType.CoreBot) {
			coinTypes.add(CoinType.Bronze);
			coinTypes.add(CoinType.Silver);
			coinTypes.add(CoinType.Gold);
			coinTypes.add(CoinType.Diamond);
		}
		
		return coinTypes;
	}
	
	// Determine whether or not a robot can pick up a coin on its current turn
	public boolean canPickup(Robot r) {
		boolean coinPickup;
		if (Collections.disjoint(current_location.getCoins(), getCoinTypes(r)) == false) {
			coinPickup = true;
		}
		else {
			coinPickup = false;
		}
		return coinPickup;
	}

}
