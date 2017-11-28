import java.util.*;

public class FalconBot{
    
    public FalconBot(int the_id, boolean teamOne){
		model = ModelType.WolfBot;
		id = the_id;
		is_teamOne = teamOne;
		
		coinTypes.add(CoinType.Bronze);
        coinTypes.add(CoinType.Silver);
        coinTypes.add(CoinType.Gold);
    }
}
