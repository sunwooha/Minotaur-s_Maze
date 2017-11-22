import java.util.List;

//chooseTeam is called once at the very start of the game
//This function should return the robots the player wants to use during the game

//requestCommands is called each turn
//This functino should return one Command for each robot awaiting command

public interface PlayerTeam{
    public List<? extends Robot> chooseTeam(boolean teamOne, GameState state);
    public List<Command> requestCommands(List<Location> information, List<Robot> robotsAwaitingCommand, GameState state);
}