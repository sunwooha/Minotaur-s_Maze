public class CommandCoin implements Command{
    private Robot the_robot;

    public Robot getRobot(){
	return the_robot;
    }

    public CommandCoin(Robot rob){
	the_robot = rob;
    }

}