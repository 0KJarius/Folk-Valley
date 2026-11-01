package blackjackpackage.rightsidepackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.AnimationUtils;
import utilities.EventBus;
import utilities.SwingUtils;

public class BottomInfoArea extends JPanel{
	int maxCards = EventBus.player.playerDeck.getSize();
	JLabel cardCountLabel = SwingUtils.createJLabel(maxCards + "/" + maxCards, 40, Color.WHITE, SwingUtils.M6XPLUS);
	
	public BottomInfoArea() {
		this.setBounds(new Rectangle(4, 313, 201,398));
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		JPanel cardCounter = SwingUtils.createJPanel(new Dimension(0, 52), Color.BLUE);
		cardCounter.setOpaque(false);
		cardCounter.add(cardCountLabel);
		cardCountLabel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
		
		this.add(cardCounter, BorderLayout.NORTH);
	}
	
	public void updateCardCountLabel() {
		cardCountLabel.setText(EventBus.currentMatch.deck.getSize() + "/" + maxCards);
		AnimationUtils.popup(cardCountLabel, 1.1, 150, 2);
	}
}
