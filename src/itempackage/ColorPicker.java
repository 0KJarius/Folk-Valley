package itempackage;

import javax.swing.ImageIcon;

import utilities.EventBus;
import utilities.SwingUtils;

public class ColorPicker extends Item{
		
	public ColorPicker() {
		super(SwingUtils.getImage(Item.ITEM_PATH + "colorPicker.png"), "Color Picker", 
			"Pick One Color! All Ono Cards of that color get deleted, free of charge !");
	}

	@Override
	public boolean doTask() {
		EventBus.currentMatch.blackJack.playerHand.deleteUnoCards();
		return true;
	}

}
