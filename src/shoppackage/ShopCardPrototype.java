package shoppackage;

import blackjackpackage.cardpackage.*;
import java.util.*;


public class ShopCardPrototype {

	private static final ArrayList<Card> prototypes = new ArrayList<>();
	
	//score cards
	static {
		for (int score = 2; score <= 9; score++) {
			for(PokerCard.Suit suit : PokerCard.Suit.values()) {
				prototypes.add(new PokerCard(score, suit));
			}
		}
		
	//Special cards

		for (PokerCard.Suit suit : PokerCard.Suit.values()) {
			for (PokerCardSpecial.Type type : PokerCardSpecial.Type.values()) {
				prototypes.add(new PokerCardSpecial(suit, type));
			}
		}
	}
	
	public static Card createRandomCopy() {
		Random random = new Random();
		
		Card prototype = prototypes.get(
				random.nextInt(prototypes.size())
				);
		
		if (prototype instanceof PokerCardSpecial) {
			PokerCardSpecial special = (PokerCardSpecial) prototype;
			
			return new PokerCardSpecial(
					special.getSuit(),
					special.getType()
					
					);
			
		}
		
		PokerCard card = (PokerCard) prototype;
		
		return new PokerCard(
				card.getCardScore(),
				card.getSuit()
				);
		
		
	}
	
}
