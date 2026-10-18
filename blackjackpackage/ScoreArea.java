package blackjackpackage;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utilites.EventBus;

public class ScoreArea extends JPanel{
	private JLabel playerLabel = new JLabel("0");
	private JLabel enemyLabel = new JLabel("0");
	
	public ScoreArea() {
		this.setPreferredSize(new Dimension(0, 70));
		this.setBackground(Color.DARK_GRAY);
		playerLabel.setForeground(Color.BLUE);
		enemyLabel.setForeground(Color.RED);
		this.add(playerLabel);
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
