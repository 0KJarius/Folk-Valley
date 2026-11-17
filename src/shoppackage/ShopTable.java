package shoppackage;


import javax.swing.JPanel;

import mainpackage.MainFrame;
import stageselectpackage.StageSelectScreen;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;

import utilities.EventBus;
import utilities.GlobalConstants;
import utilities.SwingUtils;
import java.awt.Dimension;
import java.awt.Rectangle;

public class ShopTable extends JPanel
{
	private final String SHOP_PATH = "Shop/";
	private final String SHOP_BACK = SHOP_PATH + "shopBack/";
	private final String SHOP_REROLL = SHOP_PATH + "shopReroll/";
	
	public ShopTable()
	{
		this.setBounds(new Rectangle(GlobalConstants.SCREEN_SIZE));;
		this.setLayout(new BorderLayout(10,10));
		
		
		JPanel shopKeeperPanel = SwingUtils.createJPanel(new Dimension(300, 450), Color.GRAY);
		
		ShopInventory inventoryPanel = new ShopInventory();	
		
		JPanel dialoguePanel =	SwingUtils.createJPanel(new Dimension(900, 180), Color.DARK_GRAY);		
		
		
		this.add(shopKeeperPanel, BorderLayout.WEST);
		this.add(inventoryPanel, BorderLayout.CENTER);
		this.add(dialoguePanel, BorderLayout.SOUTH);
		
		JButton returnButton = SwingUtils.createJButton(SHOP_BACK + "normal.png", SHOP_BACK + "pressed.png");
		JButton rerollButton = SwingUtils.createJButton(SHOP_REROLL + "normal.png", SHOP_REROLL + "pressed.png");
		
		returnButton.addActionListener(e -> {
			EventBus.selectedStage.completeStage();
			EventBus.selectedStage.getNextStage().unlockStage();
			EventBus.selectedStage = null;
			MainFrame.getInstance().setScreen(StageSelectScreen.getInstance());
		});
		
		rerollButton.addActionListener(e -> {
			if (EventBus.player != null && EventBus.player.getCurrentMoney() >= inventoryPanel.getRerollPrice()) {
				inventoryPanel.reroll();
			}
		});
		
		dialoguePanel.setLayout(new BorderLayout());
		dialoguePanel.add(returnButton, BorderLayout.WEST);
		dialoguePanel.add(rerollButton, BorderLayout.CENTER);
	}	
}
