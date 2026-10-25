package blackjackpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilites.EventBus;
import utilites.SwingUtils;

public class ScoreArea extends JPanel{
	private JLabel playerLabel = SwingUtils.createJLabel("0", 65, new Color(82, 208, 234), SwingUtils.M6XPLUS);
	private JLabel enemyLabel = SwingUtils.createJLabel("0", 65, new Color(255, 54, 47), SwingUtils.M6XPLUS);
	
	public ScoreArea() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setPreferredSize(new Dimension(0, 120));
		this.setOpaque(false);
		
		JLabel scoreLogo = new JLabel(SwingUtils.getImage("BlackJack/scoreLabel.png"));
		scoreLogo.setAlignmentX(CENTER_ALIGNMENT);
		JPanel scoreContainer = new JPanel();
		scoreContainer.setOpaque(false);
		scoreContainer.add(playerLabel);
		scoreContainer.add(SwingUtils.createJLabel(":", 55, Color.WHITE, SwingUtils.M6XPLUS));
		scoreContainer.add(enemyLabel);
		
		this.add(scoreLogo);
		this.add(scoreContainer);
	}
	
	public void setPlayerLabelText(String text) {
		playerLabel.setText(text);
	}
	
	public void setEnemyLabelText(String text) {
		enemyLabel.setText(text);
	}
	
	public void updateScoreLabel() {
		playerLabel.setText("" + EventBus.currentMatch.blackJack.playerHand.getScore());
		enemyLabel.setText("" + EventBus.currentMatch.blackJack.enemyHand.getScore());
	}
}
