//turns_remaining is the number of turns remaining for standard speed robots
//... That is, the number of turns your CoreBot has left this game
//... Extra turns from RabbitBot (FalconBot)  are not counted in turns_remaining

//At the start of the game, team_one_coins and team_two_coins will both be zero
//... total_coins includes coins for both teams as well as coins on the ground

//Locations are numbered from 0 to max_size_x -1
//... and 0 to max_size_y - 1
// That is, there are exactly max_size_x * max_size_y locations

//Clone before I hand it to you

public class GameState implements Cloneable{
    public int maze_size_x;
    public int maze_size_y;
    public int turns_remaining;
    public int team_one_coins;
    public int team_two_coins;
    public int total_coins;

    public GameState(int x, int y, int turn, int team1, int team2, int tot){
	maze_size_x = x;
	maze_size_y = y;
	turns_remaining = turn;
	team_one_coins = team1;
	team_two_coins = team2;
	total_coins = tot;
    }

    public GameState clone(){
	return new GameState(maze_size_x, maze_size_y, turns_remaining, team_one_coins, team_two_coins, total_coins);
    }
}