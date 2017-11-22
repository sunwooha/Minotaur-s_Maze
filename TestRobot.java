//A Robot's ID can be any integer
//The ID is used to distinguish betwen robots with the same Model

//isTeamOne should return true if the robot is a member of team one
//otherwise the function should return false

public class TestRobot implements Robot{
    ModelType m;
    int id;
    boolean t1;

    public ModelType getModel(){
	return m;
    }

    public int getID(){
	return id;
    }

    public boolean isTeamOne(){
	return t1;
    }
    
    public TestRobot(ModelType the_model, int the_id, boolean is_team1){
	m = the_model;
	id = the_id;
	t1 = is_team1;
    }

}