import java.util.List;
import java.util.ArrayList;
import java.util.Random;

//chooseTeam is called once at the very start of the game
//This function should return the robots the player wants to use during the game

//requestCommands is called each turn
//This functino should return one Command for each robot awaiting command

public class TestTeam implements PlayerTeam{
    List<Robot> bots;
    Random rand;

    public List<Robot> chooseTeam(boolean teamOne, GameState state){
	rand = new Random();
	bots = new ArrayList<Robot>();
	bots.add(new TestRobot(ModelType.HippoBot, 11, teamOne));
	bots.add(new TestRobot(ModelType.CoreBot, 12, teamOne));
	bots.add(new TestRobot(ModelType.RabbitBot, 13, teamOne));
	bots.add(new TestRobot(ModelType.FalconBot, 14, teamOne));
	return bots;
    }


    public List<Command> requestCommands(List<Location> information, List<Robot> robotsAwaitingCommand, GameState state){
	
	List<Command> cmds = new ArrayList<Command>();
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
		//cmds.add(new CommandCoin(r));
		cmds.add(new CommandMove(r, dir));
	    }
	    else {
		cmds.add(new CommandMove(r, dir));
	    }
	}

	return cmds;

    }

}