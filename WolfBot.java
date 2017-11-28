import java.util.*;

public class WolfBot{
    
    public WolfBot(int the_id, boolean teamOne){
		model = ModelType.WolfBot;
		id = the_id;
		is_teamOne = teamOne;
		
		coinTypes.add(CoinType.Bronze);
		coinTypes.add(CoinType.Silver);
    }
}
