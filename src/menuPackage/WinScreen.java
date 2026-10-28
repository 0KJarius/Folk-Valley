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
import stageselectpackage.StageSelectScreen;
import utilities.EventBus;
import utilities.GlobalConstants;
import utilities.Signal;
import utilities.SwingUtils;

public class WinScreen extends JPanel{
	private JPanel statsArea = SwingUtils.createJPanel(new Rectangle(25, 97, 401, 443), Color.BLUE);
	private JPanel winArea = SwingUtils.createJPanel(new Rectangle(444, 98, 382, 535), Color.RED);
	
	private JLabel moneyLabel = SwingUtils.createJLabel("V" + EventBus.player.getMaxMoney(), 50, new Color(255, 214, 55), SwingUtils.VCR_MONO);
	private JLabel debtLabel = SwingUtils.createJLabel("0000", 50, Color.RED, SwingUtils.VCR_MONO);
	private JLabel roundsLabel = SwingUtils.createJLabel("0", 50, new Color(69, 211, 76), SwingUtils.VCR_MONO);
	
	public WinScreen(int totalDebt, int roundCount) {
		this.setBounds(new Rectangle(GlobalConstants.SCREEN_SIZE));
		this.setBackground(new Color(0, 0, 0, 100));
		this.setOpaque(false);
		
		JLayeredPane basePane = new JLayeredPane();
		basePane.setPreferredSize(GlobalConstants.SCREEN_SIZE);
		this.add(basePane);
		
		moneyLabel.setText("V" + EventBus.player.getCurrentMoney());
		debtLabel.setText("" + totalDebt);
		roundsLabel.setText("" + roundCount);
		
		setUpStatsArea();
		setUpWinPanel();
		basePane.add(SwingUtils.createJPanel(new Rectangle(1280, 720), new Color(0, 0, 0, 100)), JLayeredPane.DEFAULT_LAYER);
		basePane.add(SwingUtils.createImageAt("MenuStuff/winLayout.png", new Point(0, 0)), JLayeredPane.PALETTE_LAYER);
		basePane.add(statsArea, JLayeredPane.MODAL_LAYER);
		basePane.add(winArea, JLayeredPane.MODAL_LAYER);
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
	
	private void setUpWinPanel() 
	{
		WinScreen self = this;
		winArea.setOpaque(false);
		Signal menuTask = new Signal() {
			@Override
			public void doTask() {
				MainFrame.getInstance().setScreen(StageSelectScreen.getInstance());
				EventBus.currentMatch = null;
				MainFrame.getInstance().removeLayer(self);
			}
		};
		
		JarButton mainMenuButton = new JarButton(false, "MAIN MENU", 60, menuTask);
		
		winArea.add(Box.createRigidArea(new Dimension(356, 180)));
		winArea.add(mainMenuButton);
	}
		
}
