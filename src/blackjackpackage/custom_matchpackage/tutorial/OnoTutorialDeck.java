package blackjackpackage.custom_matchpackage.tutorial;

import blackjackpackage.Deck;
import blackjackpackage.cardpackage.UnoCard;
import utilities.EventBus;

public class OnoTutorialDeck extends Deck{
	public OnoTutorialDeck() {
		super();
		for (int i = 0; i < EventBus.player.playerDeck.getSize(); i++) {
			this.addCard(EventBus.player.playerDeck.getCard(i));
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < UnoCard.CardColor.values().length; j++) {
				this.addCard(new UnoCard(UnoCard.CardColor.values()[j], i));
			}
		}
	}
}
