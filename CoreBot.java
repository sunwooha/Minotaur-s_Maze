import java.util.*;

public class CoreBot implements Robot{
	ModelType model;
	int id;
	boolean is_teamOne;
	
	// Constructor
	public CoreBot(int the_id, boolean teamOne){
		this.id = the_id;
		this.is_teamOne = teamOne;
	}
	public ModelType getModel() {
		return ModelType.CoreBot;
	}
	
	public int getID() {
		return this.id;
	}
	
	public boolean isTeamOne() {
		return this.is_teamOne;
	}
}
