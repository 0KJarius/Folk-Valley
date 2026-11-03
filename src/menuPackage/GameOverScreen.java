package menuPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import mainpackage.MainFrame;
import stageselectpackage.StageSelectScreen;
import utilities.EventBus;
import utilities.GlobalConstants;
import utilities.Signal;
import utilities.SwingUtils;

public class GameOverScreen extends OverlayMenu
{
	private JPanel statsArea = SwingUtils.createJPanel(new Rectangle(25, 97, 401, 443), Color.BLUE);
	private JPanel gameOverArea = SwingUtils.createJPanel(new Rectangle(444, 98, 382, 535), Color.RED);
	
	private JLabel moneyLabel = SwingUtils.createJLabel("V" + EventBus.player.getMaxMoney(), 50, new Color(255, 214, 55), SwingUtils.VCR_MONO);
	private JLabel debtLabel = SwingUtils.createJLabel("0000", 50, Color.RED, SwingUtils.VCR_MONO);
	private JLabel roundsLabel = SwingUtils.createJLabel("5", 50, new Color(69, 211, 76), SwingUtils.VCR_MONO);
	
	public GameOverScreen(int maxMoney, int totalDebt, int roundCount) 
	{
		this.setBounds(new Rectangle(GlobalConstants.SCREEN_SIZE));
		this.setBackground(new Color(0, 0, 0, 100));
		this.setOpaque(false);
		
		JLayeredPane basePane = new JLayeredPane();
		basePane.setPreferredSize(GlobalConstants.SCREEN_SIZE);
		this.add(basePane);
		
		moneyLabel.setText("V" + maxMoney);
		debtLabel.setText("" + totalDebt);
		roundsLabel.setText("" + roundCount);
		
		setUpStatsArea();
		setUpGameOverPanel();
		basePane.add(SwingUtils.createJPanel(new Rectangle(1280, 720), new Color(0, 0, 0, 100)), JLayeredPane.DEFAULT_LAYER);
		basePane.add(SwingUtils.createImageAt("MenuStuff/gameOverLayout.png", new Point(0, 0)), JLayeredPane.PALETTE_LAYER);
		basePane.add(statsArea, JLayeredPane.MODAL_LAYER);
		basePane.add(gameOverArea, JLayeredPane.MODAL_LAYER);
		this.addMouseListener(this);
		
	}
	
	private void setUpStatsArea() 
	{
		statsArea.setOpaque(false);
		statsArea.setLayout(new BoxLayout(statsArea, BoxLayout.Y_AXIS));
		
		statsArea.add(Box.createRigidArea(new Dimension(17, 145)));
		statsArea.add(moneyLabel);
		statsArea.add(Box.createRigidArea(new Dimension(0, 56)));
		statsArea.add(debtLabel);
		statsArea.add(Box.createRigidArea(new Dimension(0, 60)));
		statsArea.add(roundsLabel);
	}
	
	private void setUpGameOverPanel() 
	{
		GameOverScreen self = this;
		gameOverArea.setOpaque(false);
		Signal retryTask = new Signal() {
			@Override
			public boolean doTask() {
				EventBus.hardResetMatch();
				MainFrame.getInstance().removeLayer(self);
				return true;
			}
		};
		
		Signal menuTask = new Signal() {
			@Override
			public boolean doTask() {
				EventBus.player.resetWallet();
				MainFrame.getInstance().removeLayer(EventBus.currentMatch.scanLines);
				MainFrame.getInstance().setScreen(StageSelectScreen.getInstance());
				EventBus.currentMatch = null;
				MainFrame.getInstance().removeLayer(self);
				
				return true;
			}
		};
		
		
		JarButton retryButton = new JarButton(false, "RETRY", 60, retryTask);
		JarButton mainMenuButton = new JarButton(false, "MAIN MENU", 60, menuTask);
		
		gameOverArea.add(Box.createRigidArea(new Dimension(356, 180)));
		gameOverArea.add(retryButton);
		gameOverArea.add(mainMenuButton);
	}
	

}
