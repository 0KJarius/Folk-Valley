package blackjackpackage.cardpackage;

import java.awt.Color;
import java.util.Random;

import javax.swing.ImageIcon;

import utilities.AnimationUtils;
import utilities.GlobalConstants;
import utilities.SwingUtils;

public class PokerCard extends Card{
	public static String currentSkin = "mainCards/";
	private ImageIcon cardImg;
	private ImageIcon cardSelectImg;
	private final ImageIcon FLIP_IMG = SwingUtils.getImage(GlobalConstants.POKERCARD + currentSkin + "back.png");
	private ImageIcon flipSelectImg;
	
	public static enum Suit{
		CLUB,
		DIAMOND,
		HEART,
		SPADE
	};
	private Suit cardSuit;
	
	public PokerCard(int score, Suit suit) {
		cardImg = SwingUtils.getImage(GlobalConstants.POKERCARD + currentSkin + suit.toString().toLowerCase() + "/" + score + ".png");
		cardSuit = suit;
		
		super.setCardScore(score);
		this.setIcon(cardImg);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		
		cardSelectImg = SwingUtils.createTintedImage(cardImg, new Color(50, 50, 50, (int)(0.7f * 255)));
		this.setSelectedIcon(cardSelectImg);
		
		flipSelectImg = SwingUtils.createTintedImage(FLIP_IMG, new Color(50, 50, 50, (int)(0.7f * 255)));
	}
	
	public PokerCard() {
		this(new Random().nextInt(2, 10), Suit.values()[new Random().nextInt(0, Suit.values().length)]);
	}
		
	public Suit getSuit() {
		return cardSuit;
	}
	
	public void setSuit(Suit suit) {
		cardSuit = suit;
	}
	
	public void setCardImg(ImageIcon cardImg) {
		this.cardImg = cardImg;
		this.setIcon(cardImg);
	}
	
	public void setSelectedCardImg(ImageIcon cardSelectImg) {
		this.cardSelectImg = cardSelectImg;
		this.setSelectedIcon(cardSelectImg);
	}
	
	public ImageIcon getCardImg() {
		return cardImg;
	}
	
	@Override
	public void flipCard() {
		this.setIcon(FLIP_IMG);
		this.setSelectedIcon(flipSelectImg);
		this.repaint();
		super.flipCard();
	}
	
	@Override
	public void unflipCard() {
		this.setIcon(cardImg);
		this.setSelectedIcon(cardSelectImg);
		AnimationUtils.popup(this, 2, 500, 29);
		
		this.repaint();
		super.unflipCard();
	}
	
}
