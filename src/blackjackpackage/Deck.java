package blackjackpackage;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> cardDeck;
	
	public Deck() {
		cardDeck = new ArrayList<Card>();
	}
	
	public void addCard(Card newCard) 
	{
		cardDeck.addLast(newCard);
	}
	
	public void removeCard(Card newCard) 
	{
		if (cardDeck.contains(newCard)) {
			cardDeck.remove(newCard);
		}
	}
	
	public Card getCard(int index) {
		if (cardDeck.size() == 0) {
			return null;
		}
		
		return cardDeck.get(index);
	}
	
	public Card getCard() {
		if (cardDeck.size() == 0) {
			return null;
		}
		
		Random rng = new Random();
		int randomIndex = rng.nextInt(0, cardDeck.size() - 1);
		return getCard(randomIndex);
	}
	
	public void printCards() 
	{
		for (Card card : cardDeck) {
			System.out.println(card.getCardScore());
		}
	}
	
	public Deck cloneDeck() 
	{
		Deck clone = new Deck();
		for(Card card : cardDeck) {
			clone.addCard(card);
		}
		
		return clone;
	}
	
}
