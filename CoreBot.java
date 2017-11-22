import java.util.*;

public class CoreBot extends CSSRobot{
	
	// Constructor
    public CoreBot(int the_id, boolean teamOne){
		model = ModelType.CoreBot;
		id = the_id;
		is_teamOne = teamOne;
		
		// CoreBots can pick up all coin types
		/*
		coinTypes.add(CoinType.Bronze);
		coinTypes.add(CoinType.Silver);
		coinTypes.add(CoinType.Gold);
		coinTypes.add(CoinType.Diamond);
		* */
    }
	
	/*
	// Determine how CoreBots move
	public DirType move(List<Location> info, GameState state){
		
		// Random movement as a placeholder until we figure out something better
		
		int max = 4;
		int min = 1;
		int dir = rand.nextInt((max - min) + 1) + min;
		
		if (dir == 1) {
			return DirType.North;
		}
		else if (dir == 2) {
			return DirType.South;
		}
		else if (dir == 3) {
			return DirType.East;
		}
		else if (dir == 4) {
			return DirType.West;
		}
		else {
			return DirType.North;
		}
    }
    * */
}
