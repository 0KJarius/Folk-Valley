package shoppackage;

import blackjackpackage.cardpackage.*;
import java.util.*;


public class ShopCardPrototype {

    private static final ArrayList<Card> pokerPrototypes = new ArrayList<>();
    private static final ArrayList<Card> unoPrototypes = new ArrayList<>();
	
    private static final Random random = new Random();
    
	//score cards
	static {
		for (int score = 2; score <= 9; score++) {
			for(PokerCard.Suit suit : PokerCard.Suit.values()) {
				pokerPrototypes.add(new PokerCard(score, suit));
			}
		}
		
	//Special cards

		for (PokerCard.Suit suit : PokerCard.Suit.values()) {
			for (PokerCardSpecial.Type type : PokerCardSpecial.Type.values()) {
				pokerPrototypes.add(new PokerCardSpecial(suit, type));
			}
		}
		
	// UNO CARD
		for (UnoCard.CardColor color : UnoCard.CardColor.values()) {
			for (int score = 0; score <= 9; score++) {
				unoPrototypes.add(new UnoCard(color, score));
			}
	
		}
	}

		
	//RANDOM POKER PACK
	
	public static Card createRandomPokerCopy() {
        Card prototype = pokerPrototypes.get(
                random.nextInt(pokerPrototypes.size()));
		
        if (prototype instanceof PokerCardSpecial) {
        	PokerCardSpecial special = (PokerCardSpecial) prototype;
        	
        	return new PokerCardSpecial(special.getSuit(), special.getType());
        	
        	
        }
        PokerCard card = (PokerCard) prototype;
        
        return new PokerCard(card.getCardScore(), card.getSuit());
        
	}
	
	// RANDOM UNO PACK
	public static Card createRandomUnoCopy() {
        UnoCard prototype = (UnoCard) unoPrototypes.get(
                random.nextInt(unoPrototypes.size())
            );
        
        return new UnoCard(prototype.getColor(), prototype.getCardScore());
	}
	
	// BICARD
	public static Card createRandomMixedCopy() {
		if (random.nextBoolean()) {
			return createRandomPokerCopy();
		} else {
			return createRandomUnoCopy();
		}
		
		
		
	}
	
	
	
}
