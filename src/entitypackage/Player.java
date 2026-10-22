package entitypackage;
import blackjackpackage.Deck;
import blackjackpackage.cardpackage.PokerCard;
import blackjackpackage.cardpackage.PokerCardSpecial;
import utilites.EventBus;

public class Player extends Entity{
	public Deck playerDeck;
	
	public Player(int money, String name) {
		super(money, name);
		EventBus.player = this;
		playerDeck = new Deck();
		initializeTestDeck();
	}
	
	private void initializeTestDeck() 
	{
//		for (int i = 0; i < 40; i++) {
//			playerDeck.addCard(new PokerCard());
//		}
		
		for (int i = 0; i < 40; i++) {
			playerDeck.addCard(new PokerCardSpecial(PokerCard.Suit.CLUB, PokerCardSpecial.Type.QUEEN));
		}
		
		
//		for (int i = 0; i < PokerCard.Suit.values().length; i++) 
//		{
//			playerDeck.addCard(new PokerCardSpecial(PokerCard.Suit.values()[i], PokerCardSpecial.Type.ACE));
//			playerDeck.addCard(new PokerCardSpecial(PokerCard.Suit.values()[i], PokerCardSpecial.Type.QUEEN));
//			playerDeck.addCard(new PokerCardSpecial(PokerCard.Suit.values()[i], PokerCardSpecial.Type.KING));
//			playerDeck.addCard(new PokerCardSpecial(PokerCard.Suit.values()[i], PokerCardSpecial.Type.JACK));
//		}
	}
}
