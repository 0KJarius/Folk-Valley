package itempackage;

import utilities.EventBus;
import utilities.SwingUtils;

public class Reverse extends Item{

	public Reverse() {
		super(
			SwingUtils.getImage(Item.ITEM_PATH + "reverse.png"), 
			"Reverse", 
			"Switches current Player score with Opponent's score"
		);
	}

	@Override
	public boolean doTask() {
		int playerScore = EventBus.currentMatch.blackJack.playerHand.getScore();
		int enemyScore = EventBus.currentMatch.blackJack.getEnemyHand().getScore();
		
		EventBus.currentMatch.blackJack.playerHand.setScore(enemyScore);
		EventBus.currentMatch.blackJack.getEnemyHand().setScore(playerScore);
		EventBus.currentMatch.blackJack.getScoreArea().updateScoreLabel();
		return true;
	}

}
