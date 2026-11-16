package shoppackage;

import java.util.*;

import blackjackpackage.cardpackage.*;

public class ShopPack {

	public enum PackType {
		POKER,
		UNO,
		MIXED
	}
	
	private ArrayList<Card> cards;
	private int price;
	private PackType packType;
	
	
	public ShopPack(int price, PackType packType) {
		super();
		this.price = price;
		this.cards = new ArrayList<>();
		this.packType = packType;
		generateCards();
		

	}
	
	private void generateCards() {
		for (int index = 0; index < 5; index++) {
			
			switch (packType) {
			case POKER:
				cards.add(ShopCardPrototype.createRandomPokerCopy());
				break;
			case UNO:
				cards.add(ShopCardPrototype.createRandomUnoCopy());
				break;
			case MIXED:
				cards.add(ShopCardPrototype.createRandomMixedCopy());
				break;
			}
		}
		
		
		
	}
	
	public int getPrice() {
		return price;
	}
	
	public ArrayList<Card> getCards() {
		return cards;
	}
	
	public PackType getPackType() {
		return packType;
	}
	
}
