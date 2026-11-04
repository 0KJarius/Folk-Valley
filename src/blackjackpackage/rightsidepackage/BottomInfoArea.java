package blackjackpackage.rightsidepackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.AnimationUtils;
import utilities.EventBus;
import utilities.GlobalConstants;
import utilities.SwingUtils;

public class BottomInfoArea extends JPanel{
	protected int maxCards = EventBus.currentMatch.deck.getSize();
	protected JLabel cardCountLabel = SwingUtils.createJLabel(maxCards + "/" + maxCards, 40, Color.WHITE, GlobalConstants.M6XPLUS);
	protected JLabel currentItemLabel = SwingUtils.createJLabel("N/A", 30, Color.WHITE, GlobalConstants.M6XPLUS);
	private TerminalScreen terminalScreen = new TerminalScreen();
	private TerminalButtons buttons = new TerminalButtons(this);
	private ItemInfoScreen itemInfo = new ItemInfoScreen();
	private JPanel itemSelect = SwingUtils.createJPanel(new Dimension(201, 150), Color.cyan);
	
	public BottomInfoArea() {
		this.setBounds(new Rectangle(4, 313, 201,398));
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
		JPanel cardCounter = SwingUtils.createJPanel(new Dimension(0, 52), Color.BLUE);
		cardCounter.setOpaque(false);
		cardCounter.add(cardCountLabel);
		cardCountLabel.setBorder(BorderFactory.createEmptyBorder(10, 5, 0, 5));
		
		JPanel itemTerminal = SwingUtils.createJPanel(new Dimension(201,346), getBackground());
		setUpItemTerminal(itemTerminal);
		
		this.add(cardCounter, BorderLayout.NORTH);
		this.add(itemTerminal, BorderLayout.CENTER);
	}
	
	public void updateCardCountLabel() {
		cardCountLabel.setText(EventBus.currentMatch.deck.getSize() + "/" + maxCards);
		AnimationUtils.popup(cardCountLabel, 1.1, 150, 2);
	}
	
	public void setUpItemTerminal(JPanel terminal) 
	{
		terminal.setLayout(new BorderLayout());
		terminal.setOpaque(false);
		getItemSelect().setLayout(new BorderLayout());
		getItemSelect().setOpaque(false);
		getItemSelect().setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
		
		JPanel itemLabelContainer = SwingUtils.createJPanel(new Dimension(0,41), Color.cyan);
		itemLabelContainer.setOpaque(false);
		itemLabelContainer.add(currentItemLabel);
		currentItemLabel.setBorder(BorderFactory.createEmptyBorder(4, 5, 0, 0));
		
		
		if (!EventBus.player.inventory.isEmpty()) {
			currentItemLabel.setText(EventBus.player.inventory.get(0).getName());
			getItemSelect().add(EventBus.player.inventory.get(0));
		}
		
		terminal.add(getTerminalScreen(), BorderLayout.CENTER);
		terminal.add(itemLabelContainer, BorderLayout.NORTH);
		terminal.add(getButtons(), BorderLayout.SOUTH);
		
		getTerminalScreen().setTerminalScreen(getItemSelect());
	}

	public TerminalScreen getTerminalScreen() {return terminalScreen;}

	public TerminalButtons getButtons() {return buttons;}

	public void setButtons(TerminalButtons buttons) {this.buttons = buttons;}

	public JPanel getItemSelect() {return itemSelect;}

	public ItemInfoScreen getItemInfo() {return itemInfo;}


}
