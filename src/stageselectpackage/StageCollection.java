package stageselectpackage;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLayeredPane;

import blackjackpackage.BlackJackScreen;
import blackjackpackage.custom_matchpackage.tutorial.TutorialMatch;
import menuPackage.BattlePrompt;
import shoppackage.ShopTable;
import utilities.EventBus;
import cutscenepackage.CutsceneDialogue;
import entitypackage.enemypackage.*;

public class StageCollection {
	private static int pageCount = 0;
	static ArrayList<Stage> stages = new ArrayList<Stage>();
	private static final StageCollection STAGES = new StageCollection();
	
	private StageCollection() {
		StageBuilder builder = new StageBuilder();
		builder.setStageFrameName("fight.png");
		builder.setStageName("PROTOSUER");
		builder.setLayer(JLayeredPane.PALETTE_LAYER);
		builder.setTextColor(new Color(255, 177, 177));
		builder.setDestination(
				new BattlePrompt(
						EnemyCollection.PROTOSUER.getEnemy(), 
						new CutsceneDialogue(EnemyCollection.PROTOSUER.getEnemy(), "placeValley.png", 
						new BlackJackScreen(EnemyCollection.PURSUER.getEnemy(), EventBus.player.playerDeck))
				)
		);
		
		
		stages.add(builder.build());
		stages.add(stages.get(0).copyStage(true));
		stages.add(stages.get(0).copyStage(true, stages.get(stages.size() - 1)));
		stages.add(new Stage("Shop", Color.white, "shop.png" ,true, new ShopTable(),  stages.get(stages.size() - 1)));
		
		builder.setStageName("TUTORIAL");
		builder.setDestination(new BattlePrompt(
						EnemyCollection.PURSUER.getEnemy(), 
						new CutsceneDialogue(EnemyCollection.PURSUER.getEnemy(), "placeValley.png", 
						new TutorialMatch(EnemyCollection.PURSUER.getEnemy()))
				));
		builder.setNextStage(stages.get(stages.size() - 1));
		stages.add(builder.build());
	}
	//Stages are stored in reverse order (last level is on index 0)
	public static StagePage createStagePage(int startIndex, int endIndex) {
		StagePage page = new StagePage();
		for (int i = startIndex; i >= endIndex; i--) {
			page.addStage(stages.get(i));
		}
		
		pageCount++;
		return page;
	}
	
	public static StageCollection getInstance() {
		return STAGES;
	}
	
	public static int getPageCount() {
		return pageCount;
	}
	
	public static void onNewGame() {
		for (int i = stages.size() - 1; i >= 0; i--) {
			stages.get(i).setComplete(false);
		}
		
		for (int i = 0; i < stages.size(); i++) {
			stages.get(i).lockStage();
		}
		
		stages.get(stages.size() - 1).unlockStage();
		EventBus.player.stagesCompleted = 0;
		EventBus.player.stagesUnlocked = 1;
	}
	
	public static void onGameLoaded() {
		int unlock = EventBus.player.stagesUnlocked;
		int complete = EventBus.player.stagesCompleted;
		for (int i = unlock; i > 0; i--) {
			stages.get((stages.size() - 1) - (i-1)).unlockStage();
		}
		
		for (int i = complete; i > 0; i--) {
			stages.get((stages.size()) - i).completeStage();
		}
		EventBus.player.stagesUnlocked -= unlock;
		EventBus.player.stagesCompleted -= complete;
	}
	
}
