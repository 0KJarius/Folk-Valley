package savepackage.converterpackage;

import java.io.IOException;

import javax.swing.ImageIcon;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import utilities.ImageTracker;
import utilities.SwingUtils;

public class ImageIconConverter extends TypeAdapter<ImageIcon>{

	@Override
	public ImageIcon read(JsonReader reader) throws IOException {
		ImageIcon image = null;
		reader.beginObject();
		String field = null;
		
		while(reader.hasNext()) {
			JsonToken token = reader.peek();
			
			if(token.equals(JsonToken.NAME)) {
				field = reader.nextName();
			}
			if ("path".equals(field)) {
				token = reader.peek();
				image = SwingUtils.getImage(reader.nextString());
				
			}
			
		}
		
		reader.endObject();
		return image;
	}

	@Override
	public void write(JsonWriter writer, ImageIcon image) throws IOException {
		writer.beginObject();
		writer.name("path");
		writer.value(ImageTracker.getImageMap().get(image));
		writer.endObject();
	}

}
