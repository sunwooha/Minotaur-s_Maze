//A Robot's ID can be any integer
//The ID is used to distinguish betwen robots with the same Model

//isTeamOne should return true if the robot is a member of team one
//otherwise the function should return false

public interface Robot{
    public ModelType getModel();
    public int getID();
    public boolean isTeamOne();
}