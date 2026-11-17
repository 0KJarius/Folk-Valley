package shoppackage;


import javax.swing.JPanel;

import mainpackage.MainFrame;
import stageselectpackage.StageSelectScreen;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import java.awt.BorderLayout;
import java.awt.Color;

import utilities.EventBus;
import utilities.GlobalConstants;
import utilities.Signal;
import utilities.SwingUtils;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;

public class ShopTable extends JPanel
{
	private final static String SHOP_PATH = "Shop/";
	private final String SHOP_BACK = SHOP_PATH + "shopBack/";
	private final String SHOP_REROLL = SHOP_PATH + "shopReroll/";
	private final static String KEEPER = SHOP_PATH  + "keeper/";
	
	protected static JLabel moneyLabel = SwingUtils.createJLabel("V" + EventBus.player.getCurrentMoney(), 46, Color.decode("#FFD72F"), GlobalConstants.VCR_MONO);
	private static JLabel shopKeep = new JLabel(SwingUtils.getImage(KEEPER + "normal.png"));
	
	public ShopTable()
	{
		this.setBounds(new Rectangle(GlobalConstants.SCREEN_SIZE));;

		JLayeredPane baseLayer = new JLayeredPane();
		baseLayer.setPreferredSize(GlobalConstants.SCREEN_SIZE);
		this.add(baseLayer);
		
		JPanel contentPanel = SwingUtils.createJPanel(new Rectangle(GlobalConstants.SCREEN_SIZE), getBackground());
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setOpaque(false);
		
		baseLayer.add(SwingUtils.createImageAt(SHOP_PATH + "Shoplayout.png", new Point(0,0)), JLayeredPane.DEFAULT_LAYER);
		baseLayer.add(contentPanel, JLayeredPane.PALETTE_LAYER);
		baseLayer.add(SwingUtils.createImageAt(SHOP_PATH + "ShopScanlines.png", new Point(0,0)), JLayeredPane.MODAL_LAYER);
		
		JPanel shopKeeperPanel = SwingUtils.createJPanel(new Dimension(306, 530), Color.GRAY);
		setUpShopKeeperPanel(shopKeeperPanel);
		
		ShopInventory inventoryPanel = new ShopInventory();	
		JPanel dialoguePanel =	SwingUtils.createJPanel(new Dimension(0, 190), Color.DARK_GRAY);	
		dialoguePanel.setOpaque(false);
		
		
		contentPanel.add(shopKeeperPanel, BorderLayout.WEST);
		contentPanel.add(inventoryPanel, BorderLayout.CENTER);
		contentPanel.add(dialoguePanel, BorderLayout.SOUTH);
		
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
				moneyLabel.setText("V" + EventBus.player.getCurrentMoney());
			}
		});
		
		dialoguePanel.setLayout(new BorderLayout());
		dialoguePanel.add(returnButton, BorderLayout.WEST);
		dialoguePanel.add(rerollButton, BorderLayout.CENTER);
	}
	
	private void setUpShopKeeperPanel(JPanel keeper) {
		keeper.setOpaque(false);
		keeper.setLayout(new BorderLayout());
		keeper.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
		
		JPanel labelContiner = new JPanel();
		labelContiner.setLayout(new FlowLayout(FlowLayout.LEFT));
		((FlowLayout)labelContiner.getLayout()).setHgap(20);
		labelContiner.setOpaque(false);
		labelContiner.add(moneyLabel);
		shopKeep.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 20));
		keeper.add(labelContiner, BorderLayout.NORTH);
		keeper.add(shopKeep);
	}
	
	public static void onBuySomething() {
		shopKeep.setIcon(SwingUtils.getImage(KEEPER + "happy.png"));
		
		EventBus.executeAfterTime(2000, new Signal() {
			@Override
			public boolean doTask() {
				shopKeep.setIcon(SwingUtils.getImage(KEEPER + "normal.png"));
				return false;
			}
		});
	}
}
