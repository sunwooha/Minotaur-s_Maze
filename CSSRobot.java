import java.util.*;

public abstract class CSSRobot implements Robot {
	
	Random rand = new Random();
	ModelType model;
	int size;
	Location current_location;
	List<CoinType> coinTypes;
	
	public ModelType getModel() {
		return model;
	}
	
	public int getID() {
		int max = 999;
        int min = 100;
        int id = rand.nextInt((max - min) + 1) + min;
        return id;
	}
	
	public boolean isTeamOne() {
		return true; //for now
	}
	
	public Command choose_command() {
		if (this.canPickup() == true) {
			CommandCoin coin_get = new CommandCoin(this);
			return coin_get;
		}
		else {
			DirType next_move = this.move();
			CommandMove move_robot = new CommandMove(this, next_move);
			return move_robot;
		}
	}
	
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
	
	public abstract DirType move();
	
	public List<DirType> getVision(){
		List<DirType> knownLocations = new ArrayList<DirType>();
		return knownLocations;
    }
    
    public void retreat(Location homeBase){
		current_location = homeBase;
    }
}
