import java.util.*;

public class SkunkBot{

    public SkunkBot(int the_id, boolean teamOne){
		model = ModelType.SkunkBot;
		id = the_id;
		is_teamOne = teamOne;
		
		coinTypes.add(CoinType.Bronze);
		coinTypes.add(CoinType.Silver);
    }
}
