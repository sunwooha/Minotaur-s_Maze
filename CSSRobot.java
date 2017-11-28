import java.util.*;

public abstract class CSSRobot implements Robot {
	
	Random rand = new Random();
	ModelType model;
	int id;
	boolean is_teamOne;
	
	// Not sure how to connect current_location to the game engine
	Location current_location;
	
	// List of what types of coins this robot is able to pick up
	List<CoinType> coinTypes = new ArrayList<CoinType>();
	
	public ModelType getModel() {
		return model;
	}
	
	// Return the robot's ID
	public int getID() {
		return id;
	}
	
	public boolean isTeamOne() {
		return is_teamOne;
	}
	
	// Choose a command for the robot
	public Command choose_command(List<Location> info, GameState state) {
		if (this.canPickup() == true) {
			CommandCoin coin_get = new CommandCoin(this);
			return coin_get;
		}
		else {
			DirType next_move = this.move(info, state);
			CommandMove move_robot = new CommandMove(this, next_move);
			return move_robot;
		}
	}
	
	// Determine whether or not the robot can pick up a coin on its current turn
	public boolean canPickup() {
		boolean coinPickup;
		if (Collections.disjoint(current_location.getCoins(), this.coinTypes) == false) {
			coinPickup = true;
		}
		else {
			coinPickup = false;
		}
		return coinPickup;
	}
	
	// Different robot types should choose their moves in different ways based on different criteria, hence why this method is abstract
	public abstract DirType move(List<Location> info, GameState state);
}
