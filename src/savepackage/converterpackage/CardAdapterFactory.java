package savepackage.converterpackage;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import blackjackpackage.cardpackage.Card;
import blackjackpackage.cardpackage.PokerCard;
import blackjackpackage.cardpackage.PokerCardSpecial;
import blackjackpackage.cardpackage.UnoCard;

public class CardAdapterFactory implements TypeAdapterFactory{

	@SuppressWarnings("unchecked")
	@Override
	public <T> TypeAdapter<T> create(Gson arg0, TypeToken<T> type) {
		if (!Card.class.isAssignableFrom(type.getRawType())) {
			return null;
		}

		return (TypeAdapter<T>) new TypeAdapter<Card>() {
			@Override
			public Card read(JsonReader arg0) throws IOException {
				return null;
			}

			@Override
			public void write(JsonWriter writer, Card card) throws IOException {
			}
			
		};
	}

}
