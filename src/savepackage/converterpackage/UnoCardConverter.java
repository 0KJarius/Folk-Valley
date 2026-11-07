package savepackage.converterpackage;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import blackjackpackage.cardpackage.UnoCard;

public class UnoCardConverter extends TypeAdapter<UnoCard>{

	@Override
	public UnoCard read(JsonReader reader) throws IOException {
		UnoCard card = null;
		reader.beginObject();
		String field = null;
		
		UnoCard.CardColor color = null;
		int score = 0;
		
		while(reader.hasNext()){
			JsonToken token = reader.peek();
			if(token.equals(JsonToken.NAME)) {
				field = reader.nextName();
			}
			
			if("color".equals(field)) {
				token = reader.peek();
				color = UnoCard.CardColor.valueOf(reader.nextString());
			}
			
			if("score".equals(field)) {
				token = reader.peek();
				score = reader.nextInt();
			}
		}
		
		card = new UnoCard(color, score);
		reader.endObject();
		return card;
	}

	@Override
	public void write(JsonWriter writer, UnoCard card) throws IOException {
		writer.beginObject();
		writer.name("color");
		writer.value(card.getColor().toString());
		writer.name("score");
		writer.value(card.getCardScore());
		writer.endObject();
	}

}
