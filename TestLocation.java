import java.util.List;

//getDirections returns the directions one can move from this location
//... That is, the directions that are not blocked by a wall

//isScanned returns true if the location is within scanner range
//... If this function returns true then all information will be present 
//... If this function is false then some information may be missing
//... If information is missing because the location is not scanned
//    then the corresponding function will return null

//isTeamOneStart returns true if the location is the starting point for Team 1
//isTeamTwoStart returns true if the location is the starting point for Team 2
//... You cannot a robot into the starting location of the opposing team

//getX and getY return the coordinates of the location
//... (0,0) is the north-west corner of the maze

public class TestLocation implements Location{
    List<CoinType> coins;
    List<TestRobot> bots;
    List<DirType> dirs;
    int x;
    int y;
    boolean is;
    boolean t1st;
    boolean t2st;

    public List<CoinType> getCoins(){
	return coins;
    }

    public List<TestRobot> getRobots(){
	return bots;
    }

    public List<DirType> getDirections(){
	return dirs;
    }

    public int getX(){
	return x;
    }

    public int getY(){
	return y;
    }

    public boolean isScanned(){
	return is;
    }

    public boolean isTeamOneStart(){
	return t1st;
    }

    public boolean isTeamTwoStart(){
	return t2st;
    }
}