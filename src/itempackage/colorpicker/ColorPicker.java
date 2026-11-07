package itempackage.colorpicker;

import itempackage.Item;
import utilities.EventBus;
import utilities.SwingUtils;

public class ColorPicker extends Item{
		
	public ColorPicker() {
		super(SwingUtils.getImage(Item.ITEM_PATH + "colorPicker.png"), "Color Picker", 
			"Pick One Color! All Ono Cards of that color get deleted, free of charge !");
	}

	@Override
	public boolean doTask() {
		boolean canDelete = !EventBus.currentMatch.blackJack.playerHand.getUnoCards().isEmpty();
		if (canDelete) {
			EventBus.currentMatch.rightArea.getBottom().getTerminalScreen().setTerminalScreen(new ColorPickScreen());
			EventBus.currentMatch.rightArea.getBottom().getButtons().setButtonActivity(false);
		}
		
		return canDelete;
	}

}
