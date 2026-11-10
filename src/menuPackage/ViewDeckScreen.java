package menuPackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import blackjackpackage.Deck;
import blackjackpackage.cardpackage.PokerCard;
import blackjackpackage.cardpackage.PokerCardSpecial;
import blackjackpackage.cardpackage.UnoCard;
import mainpackage.MainFrame;
import utilities.EventBus;
import utilities.GlobalConstants;
import utilities.SwingUtils;

public class ViewDeckScreen extends OverlayMenu{
	private JLayeredPane basePane = new JLayeredPane();
	private JPanel deckCountPanel = SwingUtils.createJPanel(new Rectangle(20, 20, 492, 686), getBackground());
	private JPanel deckPreview = SwingUtils.createJPanel(new Rectangle(549, 49, 685, 324), getBackground());
	private JPanel itemPreview = SwingUtils.createJPanel(new Rectangle(537, 507, 576, 181), getBackground());
	private JButton closeButton;
	private JScrollPane deckScroller = new JScrollPane(deckPreview, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JScrollPane itemScroller = new JScrollPane(itemPreview);
	private int aceCounter = 0;
	private int faceCounter = 0;
	private int numberCounter = 0;
	private int spadeCounter = 0;
	private int clubCounter = 0;
	private int heartCounter = 0;
	private int diamondCounter = 0;
	private int redCounter = 0;
	private int blueCounter = 0;
	private int yellowCounter = 0;
	private int greenCounter = 0;
	
	public ViewDeckScreen() {
		this.setLayout(new BorderLayout());
		this.setBounds(new Rectangle(GlobalConstants.SCREEN_SIZE));
		//this.setBackground(new Color(0, 0, 0, 200));
		this.setOpaque(false);
		basePane.setPreferredSize(GlobalConstants.SCREEN_SIZE);
		this.add(basePane);
		
		closeButton = SwingUtils.createJButton("MenuStuff/viewDeckClose/normal.png", "MenuStuff/viewDeckClose/pressed.png");
		closeButton.setBounds(new Rectangle(closeButton.getPreferredSize()));
		closeButton.setLocation(1148, 442);
		
		deckScroller.setBounds(new Rectangle(549, 49, 685, 324));
		deckScroller.getVerticalScrollBar().setUnitIncrement(18);
		itemScroller.setBounds(new Rectangle(537, 507, 576, 181));
		
		basePane.add(SwingUtils.createImageAt("MenuStuff/viewDeckLayout.png", new Point(0, 0)), JLayeredPane.DEFAULT_LAYER);
		basePane.add(SwingUtils.createImageAt("MenuStuff/viewDeckLayoutScanLines.png", new Point(0, 0)), JLayeredPane.MODAL_LAYER);
		basePane.add(deckCountPanel, JLayeredPane.PALETTE_LAYER);
		basePane.add(deckScroller, JLayeredPane.PALETTE_LAYER);
		basePane.add(itemScroller, JLayeredPane.PALETTE_LAYER);
		basePane.add(closeButton, JLayeredPane.PALETTE_LAYER);
		
		setUpDeckPreview();
		setUpDeckCountPanel();
		setUpItemPreview();
		
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onCloseButtonClicked();
				
			}
		});
	}
	
	private void onCloseButtonClicked() {
		MainFrame.getInstance().removeLayer(this);
	}
	
	private void setUpDeckPreview() {
		Deck playerDeck =  EventBus.player.playerDeck;
		int previewRows = playerDeck.getSize() % 4 == 0 ? playerDeck.getSize() / 4 : (playerDeck.getSize() / 4) + (playerDeck.getSize() % 4);
		
		deckScroller.setOpaque(false);
		deckScroller.getViewport().setOpaque(false);
		deckScroller.setBorder(null);
		deckPreview.setOpaque(false);
		deckPreview.setLayout(new GridLayout(previewRows, 4));
		
		for (int i = 0; i < playerDeck.getSize(); i++) {
			deckPreview.add(playerDeck.getCard(i));
			checkCard(playerDeck, i);
		}
		
	}
	
	private void setUpItemPreview() {
		itemScroller.setOpaque(false);
		itemScroller.getViewport().setOpaque(false);
		itemScroller.setBorder(null);
		itemPreview.setLayout(new FlowLayout(FlowLayout.LEFT));
		itemPreview.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		
		itemPreview.setOpaque(false);
		for (int i = 0; i < EventBus.player.inventory.size(); i++) {
			itemPreview.add(EventBus.player.inventory.get(i));
		}
		
	}
	
	private void setUpDeckCountPanel() {
		int[] pokerCardCount = {aceCounter, faceCounter, numberCounter, spadeCounter, clubCounter, heartCounter, diamondCounter};
		int[] unoCardCount = {redCounter, blueCounter, yellowCounter, greenCounter};
		
		deckCountPanel.setOpaque(false);
		deckCountPanel.setLayout(new BorderLayout());
		
		JPanel center = new JPanel();
		JPanel bottom = SwingUtils.createJPanel(new Dimension(0, 116), getBackground());
		bottom.setBorder(BorderFactory.createEmptyBorder(20, 25, 0, 0));
		FlowLayout bottomLayout = new FlowLayout();
		bottomLayout.setHgap(50);
		bottom.setLayout(bottomLayout);
		bottom.setOpaque(false);
		center.setOpaque(false);
		
		FlowLayout centerLayout = new FlowLayout();
		centerLayout.setHgap(63);
		center.setLayout(centerLayout);
		
		for (int i = 0; i < pokerCardCount.length; i++) {
			JLabel countLabel = SwingUtils.createJLabel("" + pokerCardCount[i], 60, Color.white, GlobalConstants.VCR_MONO);
			countLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 65, 0));
			center.add(countLabel);
			
		}
		
		for (int i = 0; i < unoCardCount.length; i++) {
			JLabel countLabel = SwingUtils.createJLabel("" + unoCardCount[i], 60, UnoCard.CardColor.values()[i].getColor(), GlobalConstants.VCR_MONO);
			countLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			bottom.add(countLabel);

		}
		
		
		deckCountPanel.add(Box.createRigidArea(new Dimension(0, 170)), BorderLayout.NORTH);
		deckCountPanel.add(bottom, BorderLayout.SOUTH);
		deckCountPanel.add(center);
	}
	
	private void checkCard(Deck playerDeck, int i) {
		if (playerDeck.getCard(i) instanceof PokerCard) {
			switch (((PokerCard) playerDeck.getCard(i)).getSuit()) 
			{
				case SPADE:
					spadeCounter++;
					break;
				case CLUB:
					clubCounter++;
					break;
				case HEART:
					heartCounter++;
					break;
				case DIAMOND:
					diamondCounter++;
					break;
			}
			
			if (playerDeck.getCard(i) instanceof PokerCardSpecial) {
				switch (((PokerCardSpecial) playerDeck.getCard(i)).getType()) 
				{
					case ACE:
						aceCounter++;
						break;
					case KING:
					case QUEEN:
					case JACK:
						faceCounter++;
						break;
				}
			} else {
				numberCounter++;
			}
		} else if (playerDeck.getCard(i) instanceof UnoCard) {
			switch (((UnoCard) playerDeck.getCard(i)).getColor()) 
			{
				case RED:
					redCounter++;
					break;
				case BLUE:
					blueCounter++;
					break;
				case YELLOW:
					yellowCounter++;
					break;
				case GREEN:
					greenCounter++;
					break;
			}
		}
		
	}
}
