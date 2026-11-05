package entitypackage;
import java.util.ArrayList;

import blackjackpackage.Deck;
import blackjackpackage.cardpackage.PokerCard;
import blackjackpackage.cardpackage.PokerCardSpecial;
import blackjackpackage.cardpackage.UnoCard;
import itempackage.Item;
import itempackage.colorpicker.ColorPicker;
import utilities.EventBus;

public class Player extends Entity{
	public Deck playerDeck;
	public ArrayList<Item> inventory = new ArrayList<Item>();
	
	public Player(int money, String name) {
		super(money, name);
		EventBus.player = this;
		playerDeck = new Deck();
		initializeTestDeck();
		inventory.add(new ColorPicker());
		inventory.add(new ColorPicker());
		inventory.add(new ColorPicker());
		inventory.add(new ColorPicker());
		inventory.add(new ColorPicker());
		inventory.add(new ColorPicker());
	}
	
	public void setInventory(ArrayList<Item> items) {
		inventory.clear();
		if (!items.isEmpty()) {
			for (Item item : items) {
				inventory.add(item);
			}
		}
	}
	
	public ArrayList<Item> copyInventory() {
		ArrayList<Item> inventoryCopy = new ArrayList<Item>();
		for (Item item : inventory) {
			inventoryCopy.add(item);	
		}
		return inventoryCopy;
	}
	
	private void initializeTestDeck() 
	{
		for (int i = 0; i < 40; i++) {
			playerDeck.addCard(new PokerCard());
		}
		
		for (int i = 0; i < 40; i++) {
			playerDeck.addCard(new UnoCard());
		}
		
		for (int i = 0; i < PokerCard.Suit.values().length; i++) 
		{
			playerDeck.addCard(new PokerCardSpecial(PokerCard.Suit.values()[i], PokerCardSpecial.Type.ACE));
			playerDeck.addCard(new PokerCardSpecial(PokerCard.Suit.values()[i], PokerCardSpecial.Type.QUEEN));
			playerDeck.addCard(new PokerCardSpecial(PokerCard.Suit.values()[i], PokerCardSpecial.Type.KING));
			playerDeck.addCard(new PokerCardSpecial(PokerCard.Suit.values()[i], PokerCardSpecial.Type.JACK));
		}
	}
}
