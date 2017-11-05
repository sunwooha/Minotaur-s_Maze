public class CommandMove implements Command{
    private Robot the_robot;
    private DirType direction;

    public Robot getRobot(){
	return the_robot;
    }

    public DirType getDir(){
	return direction;
    }

    public CommandMove(Robot rob, DirType dir){
	the_robot = rob;
	direction = dir;
    }

}