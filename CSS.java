import java.util.*;

public class CSS implements PlayerTeam{
    boolean teamOne;
    GameState theState;
	Random rand;

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
        Robot bot1 = new CoreBot(7, teamOne);
        Robot bot2 = new WolfBot(8, teamOne);
        Robot bot3 = new SkunkBot(9, teamOne);
        Robot bot4 = new FalconBot(10, teamOne);
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
			Location current_location = currentLocation(r, information);
			if (canPickup(r, current_location) == true){
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
	public boolean canPickup(Robot r, Location currentLoc) {
		boolean coinPickup = false;
		if(currentLoc.getCoins() ==  null){
			return false;
		}
		else{
			if (Collections.disjoint(currentLoc.getCoins(), getCoinTypes(r)) == false) {
				coinPickup = true;
			}
			else {
				coinPickup = false;
			}
		}
		return coinPickup;
	}

	public Location currentLocation(Robot r, List<Location> information){
		Location currentLoc = null;
		for(Location loc: information){
			List<? extends Robot> the_bots = loc.getRobots();
			if(the_bots == null){
				continue;
			}
			else{
				for (Robot bot: the_bots){
					if(bot.getID() == r.getID()){
						currentLoc = loc;
					}
				}
			}
		}
		return currentLoc;
	}
	/**
	 * Need a way to look 2 spaces N,S,W,E from current position
	 */
	public List<Location> vision(Robot r, Location currentLoc){
		List<Location> the_vision = new ArrayList<Location>();
		int x = currentLoc.getX();
		int y = currentLoc.getY();

		return the_vision;
	}
}
