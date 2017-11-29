import java.util.*;

public class FalconBot implements Robot{
  ModelType model;
  int id;
  boolean is_teamOne;

  public FalconBot(int the_id, boolean teamOne){
    this.id = the_id;
    this.is_teamOne = teamOne;
  }
  public ModelType getModel() {
		return ModelType.FalconBot;
	}

	public int getID() {
		return this.id;
	}
	
	public boolean isTeamOne() {
		return this.is_teamOne;
	}

}
