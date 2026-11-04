package blackjackpackage.custom_matchpackage.tutorial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import menuPackage.OverlayMenu;
import utilities.SwingUtils;

public class TutorialPrompt extends JPanel{
	private final Color BLOCKER_COLOR = new Color(0, 0, 0, 200);
	private OverlayMenu blockerTop = new OverlayMenu(BLOCKER_COLOR);
	private OverlayMenu blockerBot = new OverlayMenu(BLOCKER_COLOR);
	private OverlayMenu blockerLeft = new OverlayMenu(BLOCKER_COLOR);
	private OverlayMenu blockerRight = new OverlayMenu(BLOCKER_COLOR);
	private OverlayMenu blockerFull = new OverlayMenu(new Dimension(1280, 720), BLOCKER_COLOR);
	private JTextArea tutorialText = SwingUtils.creatJTextArea(new Dimension(100, 100), getName(), 30, 2, Color.white, Color.gray);
	private JLayeredPane basePane;
	
	
	public TutorialPrompt() {
		this.setBounds(0, 0, 1280, 720);
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		basePane = new JLayeredPane();
		basePane.setPreferredSize(new Dimension(1280, 720));
		this.add(basePane);
		
		JPanel content = SwingUtils.createJPanel(new Rectangle(1280, 720), BLOCKER_COLOR);
		content.setLayout(new BorderLayout());
		content.setOpaque(false);
		basePane.add(content, JLayeredPane.DEFAULT_LAYER);
		
		content.add(blockerFull);
		content.add(blockerTop, BorderLayout.NORTH);
		content.add(blockerBot, BorderLayout.SOUTH);
		content.add(blockerLeft, BorderLayout.WEST);
		content.add(blockerRight, BorderLayout.EAST);
	}
	
	public void setBlockers(int topHeight, int botHeight, int leftWidth, int rightWidth) {
		blockerTop.setPreferredSize(new Dimension(0, topHeight));
		blockerBot.setPreferredSize(new Dimension(0, botHeight));
		blockerLeft.setPreferredSize(new Dimension(leftWidth, 0));
		blockerRight.setPreferredSize(new Dimension(rightWidth, 0));
	}
}
