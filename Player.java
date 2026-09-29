import blackjackpackage.Card;
import blackjackpackage.Deck;

public class Player {
	Deck playerDeck;
	
	public Player() {
		playerDeck = new Deck();
		initializeTestDeck();
	}
	
	private void initializeTestDeck() 
	{
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		playerDeck.addCard(new Card());
		
		playerDeck.printCards();
	}
}
