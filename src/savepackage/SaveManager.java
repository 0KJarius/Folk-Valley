package savepackage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import blackjackpackage.cardpackage.PokerCard;
import blackjackpackage.cardpackage.PokerCardSpecial;
import blackjackpackage.cardpackage.UnoCard;
import entitypackage.Player;
import savepackage.converterpackage.*;
import utilities.EventBus;


public class SaveManager{
	private static SaveManager MANAGER = new SaveManager();
	private static Gson gson;
	
	private SaveManager() {
		GsonBuilder buildGson = new GsonBuilder().setPrettyPrinting();
		buildGson.registerTypeAdapterFactory(new CardAdapterFactory());
		buildGson.registerTypeAdapterFactory(new ItemAdapterFactory());
		buildGson.registerTypeAdapter(ImageIcon.class, new ImageIconConverter());
		buildGson.registerTypeAdapter(PokerCard.class, new PokerCardConverter());
		buildGson.registerTypeAdapter(PokerCardSpecial.class, new PokerCardSpecialConverter());
		buildGson.registerTypeAdapter(UnoCard.class, new UnoCardConverter());
		buildGson.registerTypeAdapter(Player.class, new PlayerDeserializer());
		gson = buildGson.create();
	}

	public static SaveManager getInstance() {
		return MANAGER;
	}
	
	public static void saveGame(Player player) {
		String playerInfo = gson.toJson(player);
		try (FileWriter writer = new FileWriter("Save.json")){
			writer.write(playerInfo);
			System.out.println("SAVED GAME");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void loadGame() {
		try(FileReader reader = new FileReader("Save.json")){
			EventBus.player = gson.fromJson(reader, Player.class);
			
			System.out.println("Loaded Game");
		} catch (FileNotFoundException e) {
			System.out.println("Save File Not Found!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
