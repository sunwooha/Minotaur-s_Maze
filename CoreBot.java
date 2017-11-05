import java.util.Random;

public class CoreBot implements Robot{
    Random rand = new Random();
    ChooseTeam ourTeam = new ChooseTeam();
    int size = 3;

    public CoreBot(){

    }

    public ModelType getModel(){
        ModelType type = CoreBot;
        return type;
    }

    public int getID(){
        int max = 999;
        int min = 100;
        int id = rand.nexInt((max - min) + 1) + min;
        return id;
    }

    public boolean isTeamOne(){
        return (ourTeam.teamOne);
    }

    public DirType move(){

    }

    public List<DirType> getVision(){

    }
    
    public void pickCoin(){

    }

    public Location retreat(){
        
    }
}