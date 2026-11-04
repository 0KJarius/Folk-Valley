package blackjackpackage.custom_matchpackage.tutorial;

import blackjackpackage.Deck;
import blackjackpackage.cardpackage.PokerCard;
import blackjackpackage.cardpackage.PokerCardSpecial;

public class TutorialDeck extends Deck{
	public TutorialDeck() {
		super();
		for (int i = 2; i < 11; i++) {
			for (int j = 0; j < PokerCard.Suit.values().length; j++) {
				addCard(new PokerCard(i, PokerCard.Suit.values()[j]));
			}
		}
		
		for (int i = 0; i < PokerCard.Suit.values().length; i++) 
		{
			addCard(new PokerCardSpecial(PokerCard.Suit.values()[i], PokerCardSpecial.Type.ACE));
			addCard(new PokerCardSpecial(PokerCard.Suit.values()[i], PokerCardSpecial.Type.QUEEN));
			addCard(new PokerCardSpecial(PokerCard.Suit.values()[i], PokerCardSpecial.Type.KING));
			addCard(new PokerCardSpecial(PokerCard.Suit.values()[i], PokerCardSpecial.Type.JACK));
		}
	}
}
