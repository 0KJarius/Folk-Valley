package entitypackage;
import blackjackpackage.Card;
import blackjackpackage.Deck;

public class Player extends Entity{
	public Deck playerDeck;
	
	public Player(int money, String name) {
		super(money, name);
		playerDeck = new Deck();
		initializeTestDeck();
	}
	
	private void initializeTestDeck() 
	{
		for (int i = 0; i < 54; i++) {
			playerDeck.addCard(new Card());
		}
		
	}
}
