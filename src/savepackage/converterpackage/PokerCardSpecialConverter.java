package savepackage.converterpackage;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import blackjackpackage.cardpackage.PokerCard;
import blackjackpackage.cardpackage.PokerCardSpecial;

public class PokerCardSpecialConverter extends TypeAdapter<PokerCardSpecial>{

	@Override
	public PokerCardSpecial read(JsonReader reader) throws IOException {
		PokerCardSpecial card = null;
		reader.beginObject();
		String field = null;
		
		PokerCardSpecial.Type type = null;
		PokerCard.Suit suit = null;
		while(reader.hasNext()) {
			JsonToken token = reader.peek();
			if(token.equals(JsonToken.NAME)) {
				field = reader.nextName();
			}
			
			if("type".equals(field)){
				token = reader.peek();
				type = PokerCardSpecial.Type.valueOf(reader.nextString());
			}
			
			if("suit".equals(field)) {
				token = reader.peek();
				suit = PokerCard.Suit.valueOf(reader.nextString());
			}
		}
		card = new PokerCardSpecial(suit, type);
		reader.endObject();
		return card;
	}

	@Override
	public void write(JsonWriter writer, PokerCardSpecial card) throws IOException {
		writer.beginObject();
		writer.name("type");
		writer.value(card.getType().toString());
		writer.name("suit");
		writer.value(card.getSuit().toString());
		writer.endObject();
	}

}
