package itempackage;

import javax.swing.ImageIcon;

import utilities.EventBus;
import utilities.SwingUtils;

public class Flip extends Item{
	public Flip() {
		super(
			SwingUtils.getImage(Item.ITEM_PATH + "flip.png"), 
			"Flip Cards", 
			"Flips all cards!"
		);
	}

	@Override
	public boolean doTask() {
		EventBus.currentMatch.blackJack.playerHand.reverseCards();
		EventBus.currentMatch.blackJack.getEnemyHand().reverseCards();
		
		return true;
	}

}
