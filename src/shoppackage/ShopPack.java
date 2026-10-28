package shoppackage;

import java.util.*;

import blackjackpackage.cardpackage.*;

public class ShopPack {

	private ArrayList<Card> cards;
	private int price;
	public ShopPack(int price) {
		super();
		this.price = price;
		this.cards = new ArrayList<>();
		
		generateCards();
		

	}
	
	private void generateCards() {
		for (int index = 0; index < 5; index++) {
			cards.add(ShopCardPrototype.createRandomCopy());
		}
		
		
		
	}
	
	public int getPrice() {
		return price;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
}
