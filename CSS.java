import java.util.*;

public class CSS implements PlayerTeam{
    boolean teamOne;
    GameState theState;
	Random rand;

    /**
     * chooseTeam is called once at the very start of the game
     * This function should return the robots the player wants to use during the game
     * */
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
				List<DirType> possibleDirections = current_location.getDirections();
				int num = rand.nextInt(possibleDirections.size());
				DirType dir = possibleDirections.get(num);
				/*List<Location> adjacentLocations = directionsToLocations(current_location,information);
				List<Location> finalLocations = nextMove(r, adjacentLocations);
				int nextInt = rand.nextInt(finalLocations.size());
				Location nextLocation = finalLocations.get(nextInt);
				DirType dir = locationToDirection(nextLocation, current_location);*/
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

	public List<Location> nextMove(Robot r, List<Location> possibleLoc){
		List<Location> finalLocations = new ArrayList<Location>();
		for(Location loc: possibleLoc){
			if(loc.getRobots() == null || loc.getRobots().isEmpty()){
				finalLocations.add(loc);
			}
			else if(getEnemyRobots(loc, r)){
				continue;
			}
			else{
				finalLocations.add(loc);
			}
			if(canPickup(r, loc) && !(getEnemyRobots(loc, r))){
				finalLocations.add(loc);
			}
		}
		return finalLocations;
	}
	
	// Determine whether or not a robot can pick up a coin on its current turn
	public boolean canPickup(Robot r, Location currentLoc) {
		boolean coinPickup = false;
		if(currentLoc.getCoins() == null || currentLoc.getCoins().isEmpty()){
			coinPickup = false;
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
	
	// Determines a robot's current location in the maze
	public Location currentLocation(Robot r, List<Location> information){
		Location currentLoc = null;
		for(Location loc: information){
			List<? extends Robot> the_bots = loc.getRobots();
			if(the_bots == null || the_bots.isEmpty()){
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
		if (currentLoc == null) {
			System.out.println("test");
		}
		return currentLoc;
	}

	public DirType locationToDirection(Location loc, Location currentLocation){
		DirType finalDir = null;
		int x = loc.getX();
		int y = loc.getY();
		int currX = currentLocation.getX();
		int currY = currentLocation.getY();

		if(currX == x && (y - 1) == currY){
			finalDir = DirType.North;
		}
		else if(currX == x && (y + 1) == currY){
			finalDir = DirType.South;
		}
		else if(currY == y && (x - 1) == currX){
			finalDir = DirType.East;
		}
		else if(currY == y && (x + 1) == currX){
			finalDir = DirType.West;
		}
		return finalDir;
	}

	public List<Location> directionsToLocations(Location loc, List<Location> information) {
		int oldX = loc.getX();
		int oldY = loc.getY();
		List<DirType> possibleDir = loc.getDirections();
		List<Location> adjacentLocations = new ArrayList<Location>();
		for (DirType D : possibleDir) {
			int newX = loc.getX();
			int newY = loc.getY();
			switch (D) {
				case North:
					newY = oldY - 1;
					break;
				case South:
					newY = oldY + 1;
					break;
				case West:
					newX = oldX - 1;
					break;
				case East:
					newX = oldX + 1;
					break;
			}
			for (Location L : information) {
				if (L.getY() == newY && L.getX() == newX) {
					adjacentLocations.add(L);
				}
			}
		}
		return adjacentLocations;
	}

	public boolean getEnemyRobots(Location loc, Robot r) {
		List<? extends Robot> allRobotsHere = loc.getRobots();
		List<Robot> onlyEnemiesHere = new ArrayList<Robot>();
		List<ModelType> enemyRobots = new ArrayList<ModelType>();
		
		if (r.getModel() == ModelType.WolfBot) {
			enemyRobots.add(ModelType.MinotaurBot);
		}
		else if (r.getModel() == ModelType.FalconBot) {
			enemyRobots.add(ModelType.CoreBot);
			enemyRobots.add(ModelType.WolfBot);
			enemyRobots.add(ModelType.MinotaurBot);
		}
		else if (r.getModel() == ModelType.CoreBot) {
			enemyRobots.add(ModelType.WolfBot);
			enemyRobots.add(ModelType.MinotaurBot);
		}
		else if (r.getModel() == ModelType.SkunkBot) {
			enemyRobots.add(ModelType.CoreBot);
			enemyRobots.add(ModelType.WolfBot);
		}

		if(allRobotsHere == null){
			return false;
		}

		for(Robot rob : allRobotsHere){
			if(rob.isTeamOne() != r.isTeamOne()){
				onlyEnemiesHere.add(rob);
			}
			else{
				continue;
			}
		}

		if(loc.getRobots() == null || onlyEnemiesHere == null){
			return false;
		}
		else if (Collections.disjoint(onlyEnemiesHere, enemyRobots) == false) {
			return true;
		}
		else {
			return false;
		}
	}
}