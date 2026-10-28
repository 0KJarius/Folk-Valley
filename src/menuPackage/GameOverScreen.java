package menuPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import mainpackage.MainFrame;
import utilities.EventBus;
import utilities.GlobalConstants;
import utilities.Signal;
import utilities.SwingUtils;

public class GameOverScreen extends JPanel
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
			public void doTask() {
				EventBus.hardResetMatch();
				MainFrame.getInstance().removeLayer(self);
			}
		};
		
		JarButton retryButton = new JarButton(false, "RETRY", 60, retryTask);
		JarButton mainMenuButton = new JarButton(false, "RETRY", 60, retryTask);
		
		gameOverArea.add(Box.createRigidArea(new Dimension(356, 180)));
		gameOverArea.add(retryButton);
		gameOverArea.add(mainMenuButton);
	}
	
	@Override
	public void addNotify() {
		//this.setLocation(new Point(0, 720));
		super.addNotify();
	}
}
