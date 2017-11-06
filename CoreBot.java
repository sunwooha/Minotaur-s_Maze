import java.util.*;

public class CoreBot implements Robot{
    Random rand = new Random();
    ChooseTeam ourTeam = new ChooseTeam();
    int size = 3;

    public CoreBot(){

    }

    public ModelType getModel(){
        ModelType type = ModelType.CoreBot;
        return type;
    }

    public int getID(){
        int max = 999;
        int min = 100;
        int id = rand.nextInt((max - min) + 1) + min;
        return id;
    }

    public boolean isTeamOne(){
        return (ourTeam.teamOne);
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
    }

    public List<DirType> getVision(){

    }
    
    public void pickCoin(){

    }

    public Location retreat(){
        
    }
}
