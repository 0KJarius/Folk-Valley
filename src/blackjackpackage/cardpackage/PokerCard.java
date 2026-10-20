package blackjackpackage.cardpackage;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;

import utilites.GlobalConstants;
import utilites.SwingUtils;

public class PokerCard extends Card{
	//public static final String[] SUITS = {"spade", "diamond", "heart", "club"};
	public static String currentSkin = "placeHolderCards/";
	protected static enum Suit{
		CLUB,
		DIAMOND,
		HEART,
		SPADE
	};
	private Suit cardSuit;
	
	public PokerCard(int score, Suit suit) {
		ImageIcon cardImg = SwingUtils.getImage(GlobalConstants.POKERCARD + currentSkin + suit.toString().toLowerCase() + "/" + score + ".png");
		
		cardSuit = suit;
		super.setCardScore(score);
		this.setIcon(cardImg);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		
		createSelectImage(cardImg);
	}
	
	public PokerCard() {
		Random rng = new Random();
		int score = rng.nextInt(2, 10);
		
		Suit suit = Suit.values()[rng.nextInt(0, Suit.values().length)];
		this(score, suit);
	}
	
	
	protected void createSelectImage(ImageIcon cardImg) {
		Image card = cardImg.getImage();
		BufferedImage cardBuff = new BufferedImage(cardImg.getIconWidth(), cardImg.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D draw = cardBuff.createGraphics();
		
		draw.drawImage(card, 0, 0, null);
		draw.setComposite(AlphaComposite.SrcAtop);
		draw.setColor(new Color(50, 50, 50, (int)(0.7f * 255)));
		draw.fillRect(0, 0, cardBuff.getWidth(), cardBuff.getHeight());
		
		draw.dispose();
		
		this.setSelectedIcon(new ImageIcon(cardBuff));
	}
	
	
}
