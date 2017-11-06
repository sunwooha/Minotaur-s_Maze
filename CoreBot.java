import java.util.*;

public class CoreBot extends CSSRobot{
	/* 
     * ChooseTeam is a function, not a class... I have no clue how this is
     * supposed to work, but this was preventing me from compiling,
     * so I commented it out for now.
     * 
     * ChooseTeam ourTeam = new ChooseTeam();
     * */
    ModelType model = ModelType.CoreBot;
    int size = 3;
    Location current_location;
    List<CoinType> coinTypes = new ArrayList<CoinType>();

    public CoreBot(){
		coinTypes.add(CoinType.Bronze);
		coinTypes.add(CoinType.Silver);
		coinTypes.add(CoinType.Gold);
		coinTypes.add(CoinType.Diamond);
    }
	
	public DirType move(){
		
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
}
