import java.util.List;
import java.util.ArrayList;

class GameEngine{
    final int NUM_TURNS = 40;
    final boolean FULL_VISION = true;

    PlayerTeam team1;
    PlayerTeam team2;
    Maze the_maze;
    GameState state;
    boolean is_done;
    CommandExecution execution;

    public static void main(String[] args){
	GameEngine engine = new GameEngine();
	engine.execution = new StandardExecution();

	engine.team1 = new CSS();
	engine.team2 = new TestTeam();

	engine.startGame();
	engine.runGame();
	
    }

    void startGame(){
	the_maze = new DiskMaze("largetest.maze");
	is_done = false;

	int x = the_maze.getMaxX();
	int y = the_maze.getMaxY();
	int num_coin = the_maze.getNumCoin();

	state = new GameState(x, y, NUM_TURNS, 0, 0, num_coin);

	List<? extends Robot> robots1 = team1.chooseTeam(true, state);
	List<? extends Robot> robots2 = team2.chooseTeam(false, state);

	addRobots(true, robots1);
	addRobots(false, robots2);
	addMinotaur();

    }

    List<Location> mazeToLocations(Maze scan_maze){
	List<Location> loc_list = new ArrayList<Location>();
	int max_x = scan_maze.getMaxX();
	int max_y = scan_maze.getMaxY();

	for (int i=0; i<max_x; i++){
	    for(int j=0; j<max_y; j++){
		Location cur_loc = scan_maze.getLocation(i,j);
		if (cur_loc != null){
		    loc_list.add(cur_loc);
		}
	    }
	}
	return loc_list;
    }

    void runGame(){
	List<Command> team1_cmds;
	List<Command> team2_cmds;
	List<Location> scan1_locations;
	List<Location> scan2_locations;
	List<Robot> team1_ready;
	List<Robot> team2_ready;
	MinotaurLogic mino_logic = new MinotaurLogicStandard();
	Command mino_cmd;

	while (!is_done){
	    // DISPLAY CODE IS HERE
	    if (FULL_VISION){
		MazeDisplay.display(the_maze);
	    }
	    else{
		MazeDisplay.display(doTeamScan(true));
	    }
	    System.out.print("Coins Collected by Team 1: "); 
		System.out.println(state.team_one_coins); 
		System.out.print("Coins Collected by Team 2: "); 
		System.out.println(state.team_two_coins); 
	    System.out.print("Turns Remaining: ");
	    System.out.println(state.turns_remaining);
	    // *******************


            // REGULAR PART OF THE TURN	    
	    scan1_locations = mazeToLocations(doTeamScan(true));
	    scan2_locations = mazeToLocations(doTeamScan(false));
	    team1_ready = getReadyRobots(true, false);
	    team2_ready = getReadyRobots(false, false);
	    
	    team1_cmds = team1.requestCommands(scan1_locations,team1_ready , state.clone());
	    team2_cmds = team2.requestCommands(scan2_locations,team2_ready , state.clone());

	    mino_cmd = mino_logic.getCommand(the_maze);
	    
	    addFreeCommands(team1_cmds, getTeamRobots(true));
	    addFreeCommands(team2_cmds, getTeamRobots(false));

	    execution.executeCommands(the_maze, team1_cmds, team2_cmds, mino_cmd, state);


            // EXTRA PART OF THE TURN FOR FAST ROBOTS	  
	    // Hard-coded into the engine that the minotaur is fast
	    scan1_locations = mazeToLocations(doTeamScan(true));
	    scan2_locations = mazeToLocations(doTeamScan(false));
	    team1_ready = getReadyRobots(true, true);
	    team2_ready = getReadyRobots(false, true);
	    
	    team1_cmds = team1.requestCommands(scan1_locations,team1_ready , state.clone());
	    team2_cmds = team2.requestCommands(scan2_locations,team2_ready , state.clone());
	    
	    mino_cmd = mino_logic.getCommand(the_maze);

	    execution.executeCommands(the_maze, team1_cmds, team2_cmds, mino_cmd, state);



	    state.turns_remaining = state.turns_remaining - 1;
	    if (state.turns_remaining == 0){
		is_done = true;
	    }
	}
    }

    void addRobots(boolean teamOne, List<? extends Robot> robots){
	MazeLocation startLoc = the_maze.getTeamStart(teamOne);
	
	for (Robot bot : robots){
	    MazeRobot new_bot = MazeRobotFactory.makeMazeRobot(bot.getModel(), bot.getID(), teamOne, startLoc);

	    if (startLoc.getRobots() == null){
		startLoc.setTheRobots(new ArrayList<MazeRobot>());
	    }
	    startLoc.getRobots().add(new_bot);
	}

    }

    void addMinotaur(){
	int x = the_maze.getMaxX()/2;
	int y = the_maze.getMaxY()/2;
	MazeLocation minoLoc = the_maze.getLocation(x, y);
	MazeRobot new_bot = MazeRobotFactory.makeMazeRobot(ModelType.MinotaurBot, 0, false, minoLoc);
	if (minoLoc.getRobots() == null){
	    minoLoc.setTheRobots(new ArrayList<MazeRobot>());
	}
	minoLoc.getRobots().add(new_bot);

    }

    Maze doTeamScan(boolean teamOne){
	List<MazeRobot> bots = getTeamRobots(teamOne);
	DarkMaze scan_info = new DarkMaze(the_maze);

	for(MazeRobot r : bots){
	    r.doScan(the_maze, scan_info);
	}
	return scan_info;
    }

    void addFreeCommands(List<Command> cmds, List<MazeRobot> bots){
	for(MazeRobot r : bots){
	    Command free = r.freeCommand(the_maze);
	    if (free != null){
		cmds.add(free);
	    }
	}
    }

    List<Robot> getReadyRobots(boolean teamOne, boolean extra_turn){
	List<Robot> ready_bots = new ArrayList<Robot>();
	for(MazeRobot the_bot : getTeamRobots(teamOne)){
	    if (the_bot.isReady(extra_turn)){
		ready_bots.add(new EmptyRobot(the_bot));
	    }
	}
	return ready_bots;
    }


    List<MazeRobot> getTeamRobots(boolean teamOne){
	int max_x = the_maze.getMaxX();
	int max_y = the_maze.getMaxY();
	List<MazeRobot> team_bots = new ArrayList<MazeRobot>();

	for(int i=0; i<max_x; i++){
	    for(int j=0; j<max_y; j++){
		List<MazeRobot> bots = the_maze.getLocation(i,j).getRobots();
		if(bots != null){
		    for(MazeRobot the_bot: bots){
			if(the_bot.isTeamOne() == teamOne){
			    if(the_bot.getModel() != ModelType.MinotaurBot){
				team_bots.add(the_bot);
			    }
			}
		    }
		}
	    }
	}
	return team_bots;
    }

}
	
