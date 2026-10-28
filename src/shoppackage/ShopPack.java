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
		Random random = new Random();
		
		for(int index = 0; index < 5; index++) {
			int generate = random.nextInt(2);
			
			if (generate == 0) {
				cards.add(new PokerCard());
			} else {
				cards.add(new PokerCardSpecial());
			}
					
			
		}
		
		
		
	}
	
	public int getPrice() {
		return price;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
}
