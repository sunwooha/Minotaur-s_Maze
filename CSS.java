public class CSS implements PlayerTeam{
    boolean teamOne;

    public CSS(){
    
    }

    /**
     * chooseTeam is called once at the very start of the game
     * This function should return the robots the player wants to use during the game
     * */
    public List<Robot> chooseTeam(boolean teamOne, GameState state){
        this.teamOne = teamOne;
        
    }

    /** 
     * requestCommands is called each turn
     * This function should return one Command for each robot awaiting command
     * */
    public List<Command> requestCommands(List<Location> information, List<Robot> robotsAwaitingCommand, GameState state){

    }

}