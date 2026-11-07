package savepackage.converterpackage;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import itempackage.*;
import itempackage.Item;
import itempackage.colorpicker.ColorPicker;

public class ItemAdapterFactory implements TypeAdapterFactory{
	@SuppressWarnings("unchecked")
	@Override
	public <T> TypeAdapter<T> create(Gson arg0, TypeToken<T> type) {
		if (!Item.class.isAssignableFrom(type.getRawType())) {
			return null;
		}
		return (TypeAdapter<T>) new TypeAdapter<Item>() {

			@Override
			public Item read(JsonReader reader) throws IOException {
				Item item = null;
				reader.beginObject();
				String field = null;
				
				while(reader.hasNext()) {
					JsonToken token = reader.peek();
					if(token.equals(JsonToken.NAME)) {
						field = reader.nextName();
					}
					
					if("name".equals(field)) {
						token = reader.peek();
						switch(reader.nextString()) {
							case "Flip Cards":
								System.out.println("flip");
								item = new Flip();
								break;
							case "Plus Two":
								System.out.println("plus");
								item = new PlusTwo();
								break;
							case "Reverse":
								System.out.println("reverse");
								item = new Reverse();
								break;
							case "Color Picker":
								System.out.println("pick");
								item = new ColorPicker();
								break;
						}
					}
				}
				
				reader.endObject();
				return item;
			}

			@Override
			public void write(JsonWriter writer, Item item) throws IOException {
				writer.beginObject();
				writer.name("name");
				writer.value(item.getName());
				writer.endObject();
			}
			
			
		};
	}

}
