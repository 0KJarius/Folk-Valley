package itempackage;

import javax.swing.ImageIcon;

import utilities.EventBus;
import utilities.SwingUtils;

public class PlusTwo extends Item{

	public PlusTwo() {
		super(
			SwingUtils.getImage(Item.ITEM_PATH + "plusTwo.png"), 
			"Plus Two", 
			"Adds 2 to the Natural"
		);
	}

	@Override
	public boolean doTask() {
		EventBus.currentMatch.rightArea.addNatural(2);
		return true;
	}

}
