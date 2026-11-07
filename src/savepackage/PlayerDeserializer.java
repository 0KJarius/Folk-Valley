package savepackage;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import blackjackpackage.Deck;
import blackjackpackage.cardpackage.Card;
import blackjackpackage.cardpackage.PokerCard;
import blackjackpackage.cardpackage.PokerCardSpecial;
import blackjackpackage.cardpackage.UnoCard;
import entitypackage.Player;
import itempackage.Flip;
import itempackage.Item;
import itempackage.PlusTwo;
import itempackage.Reverse;
import itempackage.colorpicker.ColorPicker;

public class PlayerDeserializer implements JsonDeserializer<Player>{

	@Override
	public Player deserialize(JsonElement el, Type type, JsonDeserializationContext context) throws JsonParseException {
		JsonObject jObject = el.getAsJsonObject();
		String name = jObject.get("name").getAsString();
		int currentMoney = jObject.get("currentMoney").getAsInt();
		int maxMoney = jObject.get("maxMoney").getAsInt();
		int stagesCompleted = jObject.get("stagesCompleted").getAsInt();
		int stagesUnlocked = jObject.get("stagesUnlocked").getAsInt();
		Deck loadDeck = new Deck();
		ArrayList<Item> loadInventory = new ArrayList<Item>();
		
		JsonArray jDeck = jObject.getAsJsonObject("playerDeck").getAsJsonArray("cardDeck");
		
		for (int i = 0; i < jDeck.size(); i++) {
			JsonObject jCard = jDeck.get(i).getAsJsonObject();
			loadDeck.addCard(cardDecoder(jCard));
		}
		
		JsonArray jInventory = jObject.getAsJsonArray("inventory");
		for (int i = 0; i < jInventory.size(); i++) {
			loadInventory.add(itemDecoder(jInventory.get(i).getAsJsonObject()));
		}
		
		return new Player(currentMoney, name, currentMoney, maxMoney, stagesCompleted, stagesUnlocked, loadDeck, loadInventory);
	}
	
	private Card cardDecoder(JsonObject jCard) {
		Card returnCard = null;
		if (jCard.has("type") && jCard.has("suit")) {
			returnCard = generatePokerSpecial(jCard.get("type").getAsString(), jCard.get("suit").getAsString());
		} else if (jCard.has("suit")) {
			returnCard = generatePoker(jCard.get("score").getAsInt(), jCard.get("suit").getAsString());
		} else if (jCard.has("color")) {
			returnCard = generateUno(jCard.get("score").getAsInt(), jCard.get("color").getAsString());
		}
		
		
		return returnCard;
	}
	
	private Item itemDecoder(JsonObject jItem) {
		Item item = null;
		switch(jItem.get("name").getAsString()) {
		case "Flip Cards":
			item = new Flip();
			break;
		case "Plus Two":
			item = new PlusTwo();
			break;
		case "Reverse":
			item = new Reverse();
			break;
		case "Color Picker":
			item = new ColorPicker();
			break;
		}
		
		return item;
	}
	
	private PokerCardSpecial generatePokerSpecial(String type, String suit) {
		return new PokerCardSpecial(PokerCard.Suit.valueOf(suit), PokerCardSpecial.Type.valueOf(type));
	}
	
	private PokerCard generatePoker(int score, String suit) {
		return new PokerCard(score, PokerCard.Suit.valueOf(suit));
	}
	
	private UnoCard generateUno(int score, String color) {
		return new UnoCard(UnoCard.CardColor.valueOf(color), score);
	}
	
}
