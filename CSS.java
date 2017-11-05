public class CSS implements PlayerTeam{
    boolean teamOne;
    GameState theState;

    public CSS(){
    
    }

    /**
     * chooseTeam is called once at the very start of the game
     * This function should return the robots the player wants to use during the game
     * */
    public List<Robot> chooseTeam(boolean teamOne, GameState state){
        this.teamOne = teamOne;
        this.theState = state;
        List<Robot> theCSSTeam = new List();
        CoreBot number1 = new CoreBot();
        CoreBot number2 = new CoreBot();
        CoreBot number3 = new CoreBot();
        CoreBot number4 = new CoreBot();
        theCSSTeam.add(number1);
        theCSSTeam.add(number2);
        theCSSTeam.add(number3);
        theCSSTeam.add(number4);
        return theCSSTeam;
    }

    /** 
     * requestCommands is called each turn
     * This function should return one Command for each robot awaiting command
     * */
    public List<Command> requestCommands(List<Location> information, List<Robot> robotsAwaitingCommand, GameState state){

    }

}