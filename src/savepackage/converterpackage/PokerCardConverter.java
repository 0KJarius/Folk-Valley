package savepackage.converterpackage;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import blackjackpackage.cardpackage.PokerCard;

public class PokerCardConverter extends TypeAdapter<PokerCard>{

	@Override
	public PokerCard read(JsonReader reader) throws IOException {
		PokerCard card = null;
		reader.beginObject();
		String field = null;
		int cardScore = 0;
		String suit = "";
		while(reader.hasNext()) {
			JsonToken token = reader.peek();
			if(token.equals(JsonToken.NAME)) {
				field = reader.nextName();
			}
			
			if("score".equals(field)){
				token = reader.peek();
				cardScore = reader.nextInt();
			}
			
			if("suit".equals(field)) {
				token = reader.peek();
				suit = reader.nextString();
			}
		}
		card = new PokerCard(cardScore, PokerCard.Suit.valueOf(suit));
		reader.endObject();
		return null;
	}

	@Override
	public void write(JsonWriter writer, PokerCard card) throws IOException {
		writer.beginObject();
		writer.name("score");
		writer.value(card.getCardScore());
		writer.name("suit");
		writer.value(card.getSuit().toString());
		writer.endObject();
	}

}
