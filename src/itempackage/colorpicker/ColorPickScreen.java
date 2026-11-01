package itempackage.colorpicker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import blackjackpackage.cardpackage.UnoCard;
import utilities.EventBus;
import utilities.SwingUtils;

public class ColorPickScreen extends JPanel{
	public ColorPickScreen() {
		this.setOpaque(false);
		this.setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 0));
		this.setPreferredSize(new Dimension(201, 150));
		FlowLayout layout = new FlowLayout();
		layout.setVgap(0);
		this.setLayout(layout);
		
		JPanel buttonPanel = SwingUtils.createJPanel(new Dimension(164, 150), getBackground());
		buttonPanel.setLayout(new GridLayout(2, 2));
		
		Dimension buttonSize = new Dimension(100, 75);
		JButton red = SwingUtils.createJButton(buttonSize, Color.decode("#FF362f"), 3, Color.decode("#090A15"));
		JButton blue = SwingUtils.createJButton(buttonSize, Color.decode("#52D0EA"), 3, Color.decode("#090A15"));
		JButton yellow = SwingUtils.createJButton(buttonSize, Color.decode("#FFD72F"), 3, Color.decode("#090A15"));
		JButton green = SwingUtils.createJButton(buttonSize, Color.decode("#45D34C"), 3, Color.decode("#090A15"));
		
		red.addActionListener(e -> onButtonPressed(UnoCard.CardColor.RED));
		blue.addActionListener(e -> onButtonPressed(UnoCard.CardColor.BLUE));
		yellow.addActionListener(e -> onButtonPressed(UnoCard.CardColor.YELLOW));
		green.addActionListener(e -> onButtonPressed(UnoCard.CardColor.GREEN));
		
		buttonPanel.add(red);
		buttonPanel.add(blue);
		buttonPanel.add(yellow);
		buttonPanel.add(green);
		
		this.add(buttonPanel);
	}
	
	private void onButtonPressed(UnoCard.CardColor color) {
		EventBus.currentMatch.blackJack.playerHand.deleteUnoCards(color);
		EventBus.currentMatch.rightArea.getBottom().getTerminalScreen().setTerminalScreen(EventBus.currentMatch.rightArea.getBottom().getItemSelect());
		EventBus.currentMatch.rightArea.getBottom().getButtons().setButtonActivity(true);
	}
}
