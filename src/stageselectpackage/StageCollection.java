package stageselectpackage;

import java.awt.Color;
import java.util.ArrayList;

import blackjackpackage.BlackJackScreen;
import blackjackpackage.custom_matchpackage.tutorial.TutorialMatch;
import entitypackage.Entity;
import shoppackage.ShopTable;
import utilities.EventBus;

public class StageCollection {
	static ArrayList<Stage> stages = new ArrayList<Stage>();
	private static final StageCollection STAGES = new StageCollection();
	
	private StageCollection() {
		stages.add(new Stage("protosuer", new Color(255, 177, 177), "fight.png", false, new BlackJackScreen(new Entity(200, "Protosuer"), EventBus.player.playerDeck.cloneDeck())));
		stages.add(new Stage("Shop", Color.white, "shop.png" ,true, new ShopTable(),  stages.get(0)));
		stages.add(new Stage("TUTORIAL", new Color(255, 177, 177), "fight.png", false, new TutorialMatch(new Entity(300, "Pursuer")), stages.get(1)));
	}
	//Stages are stored in reverse order (last level is on index 0)
	public static StagePage createStagePage(int startIndex, int endIndex) {
		StagePage page = new StagePage();
		for (int i = startIndex; i >= endIndex; i--) {
			page.addStage(stages.get(i));
		}
		
		
		return page;
	}
	
	public static StageCollection getInstance() {
		return STAGES;
	}
}
