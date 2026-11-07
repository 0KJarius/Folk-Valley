package stageselectpackage;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLayeredPane;

import blackjackpackage.BlackJackScreen;
import blackjackpackage.custom_matchpackage.tutorial.TutorialMatch;
import entitypackage.EnemyCollection;
import menuPackage.BattlePrompt;
import shoppackage.ShopTable;
import utilities.EventBus;

public class StageCollection {
	private static int pageCount = 0;
	static ArrayList<Stage> stages = new ArrayList<Stage>();
	private static final StageCollection STAGES = new StageCollection();
	
	private StageCollection() {
		stages.add(new Stage(
				"protosuer", 
				new Color(255, 177, 177), 
				"fight.png", 
				false,
				new BattlePrompt(
						EnemyCollection.PROTOSUER.getEnemy(), 
						new BlackJackScreen(EnemyCollection.PROTOSUER.getEnemy(), EventBus.player.playerDeck)),
				JLayeredPane.PALETTE_LAYER
		));
		stages.add(stages.get(0).copyStage(true));
		stages.add(stages.get(0).copyStage(true));
		stages.add(stages.get(0).copyStage(true));
		stages.add(stages.get(0).copyStage(true));
		stages.add(stages.get(0).copyStage(true));
		stages.add(stages.get(0).copyStage(true));
		stages.add(stages.get(0).copyStage(true));
		stages.add(new Stage("Shop", Color.white, "shop.png" ,true, new ShopTable(),  stages.get(stages.size() - 3)));
		stages.add(new Stage(
				"TUTORIAL",
				new Color(255, 177, 177), 
				"fight.png",
				false,
				new BattlePrompt(
						EnemyCollection.PURSUER.getEnemy(),
						new TutorialMatch(EnemyCollection.PURSUER.getEnemy())),
				JLayeredPane.PALETTE_LAYER,
				stages.get(stages.size() - 1)
		));
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
	
}
