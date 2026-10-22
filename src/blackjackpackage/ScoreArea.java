package blackjackpackage;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utilites.EventBus;
import utilites.SwingUtils;

public class ScoreArea extends JPanel{
	private JLabel playerLabel = SwingUtils.createJLabel("0", 55, Color.BLUE, SwingUtils.M6XPLUS);
	private JLabel enemyLabel = SwingUtils.createJLabel("0", 55, Color.RED, SwingUtils.M6XPLUS);
	
	public ScoreArea() {
		this.setPreferredSize(new Dimension(0, 70));
		this.setOpaque(false);
		//this.setBackground(Color.DARK_GRAY);
		playerLabel.setForeground(Color.BLUE);
		enemyLabel.setForeground(Color.RED);
		this.add(playerLabel);
		this.add(SwingUtils.createJLabel(":", 55, Color.WHITE, SwingUtils.M6XPLUS));
		this.add(enemyLabel);
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
