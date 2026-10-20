package entitypackage;
import blackjackpackage.Deck;
import blackjackpackage.cardpackage.Card;
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
		for (int i = 0; i < 54; i++) {
			playerDeck.addCard(new PokerCardSpecial());
		}
		
	}
}
