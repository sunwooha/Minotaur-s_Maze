import java.util.*;

public class WolfBot implements Robot{
	ModelType model;
	int id;
	boolean is_teamOne;
    
    // Constructor
	public WolfBot(int the_id, boolean teamOne){
		this.id = the_id;
		this.is_teamOne = teamOne;
	}

	public ModelType getModel() {
		return ModelType.WolfBot;
	}

	public int getID() {
		return this.id;
	}
	
	public boolean isTeamOne() {
		return this.is_teamOne;
	}
}
