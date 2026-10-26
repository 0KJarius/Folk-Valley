package blackjackpackage.cardpackage;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;

import utilities.AnimationUtils;
import utilities.GlobalConstants;
import utilities.SwingUtils;

public class PokerCard extends Card{
	public static String currentSkin = "placeHolderCards/";
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
		
		cardSelectImg = createSelectImage(cardImg);
		this.setSelectedIcon(cardSelectImg);
		
		flipSelectImg = createSelectImage(FLIP_IMG);
	}
	
	public PokerCard() {
		this(new Random().nextInt(2, 10), Suit.values()[new Random().nextInt(0, Suit.values().length)]);
	}
	
	
	protected ImageIcon createSelectImage(ImageIcon cardImg) {
		Image card = cardImg.getImage();
		BufferedImage cardBuff = new BufferedImage(cardImg.getIconWidth(), cardImg.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D draw = cardBuff.createGraphics();
		
		draw.drawImage(card, 0, 0, null);
		draw.setComposite(AlphaComposite.SrcAtop);
		draw.setColor(new Color(50, 50, 50, (int)(0.7f * 255)));
		draw.fillRect(0, 0, cardBuff.getWidth(), cardBuff.getHeight());
		
		draw.dispose();
		
		return new ImageIcon(cardBuff);
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
	}
	
	@Override
	public void unflipCard() {
		this.setIcon(cardImg);
		this.setSelectedIcon(cardSelectImg);
		AnimationUtils.popup(this, 2, 500, 29);
		
		this.repaint();
	}
}
