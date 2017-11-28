import java.util.*;

public abstract class CSSRobot implements Robot {
	
	Random rand = new Random();
	ModelType model;
	int id;
	boolean is_teamOne;
	
	// Not sure how to connect current_location to the game engine
	Location current_location;
	
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
}
