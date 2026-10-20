package entitypackage;
import blackjackpackage.Card;
import blackjackpackage.Deck;
import blackjackpackage.PokerCard;
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
			playerDeck.addCard(new PokerCard());
		}
		
	}
}
