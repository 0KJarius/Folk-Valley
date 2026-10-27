package blackjackpackage.cardpackage;

import java.awt.Color;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import utilities.AnimationUtils;
import utilities.GlobalConstants;
import utilities.SwingUtils;

public class UnoCard extends Card{
	public enum CardColor{
		RED(Color.decode("#FF362F"), PokerCard.Suit.HEART),
		BLUE(Color.decode("#52D0EA"), PokerCard.Suit.DIAMOND),
		YELLOW(Color.decode("#FFD72F"), PokerCard.Suit.SPADE),
		GREEN(Color.decode("#45D34C"), PokerCard.Suit.CLUB);
		
		private Color color;
		private PokerCard.Suit equivalent;
		private CardColor(Color color, PokerCard.Suit equivalent) {this.color = color; this.equivalent = equivalent;}
		
		public Color getColor() {return color;}
		public PokerCard.Suit getEquivalent() {return equivalent;}
	}
	
	private CardColor color;
	
	private final String UNO_PATH = "Cards/Uno Cards/";
	private String currentSkin = UNO_PATH + "placeHolder/";
	private final String FRAME_PATH = currentSkin + "frame.png";
	private final String FRAME_SELECT_PATH = currentSkin + "frameSelect.png";
	private final String BACK_PATH = currentSkin + "back.png";
	private final String BACK_SELECTED_PATH = currentSkin + "backSelected.png";
	
	ImageIcon cardFrame = SwingUtils.getImage(FRAME_PATH);
	ImageIcon cardSelect = SwingUtils.getImage(FRAME_SELECT_PATH);
	ImageIcon back = SwingUtils.getImage(BACK_PATH);
	ImageIcon backSelect = SwingUtils.getImage(BACK_SELECTED_PATH);
	
	public UnoCard(CardColor color, int score) {
		
		this.color = color;
		this.setText("" + score);
		this.setBackground(color.getColor());
		super.setCardScore(score);
		
		this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
		
		this.setIcon(cardFrame);
		this.setSelectedIcon(cardSelect);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		
		try {
			this.setFont(SwingUtils.createFont(SwingUtils.NIKKYOU_SANS, 60));
		} catch (FontFormatException | IOException e) {}
	}
	
	public UnoCard() {
		this(CardColor.values()[new Random().nextInt(0, CardColor.values().length - 1)], new Random().nextInt(0, 10));
	}
	
	public CardColor getColor() {
		return color;
	}
	
	@Override
	public void flipCard() {
		this.setIcon(back);
		this.setSelectedIcon(backSelect);
		this.repaint();
		this.setText("");
		super.flipCard();
	}
	
	@Override
	public void unflipCard() {
		this.setIcon(cardFrame);
		this.setSelectedIcon(cardSelect);
		this.setText("" + super.getCardScore());
		
		this.repaint();
		super.unflipCard();
	}
}
