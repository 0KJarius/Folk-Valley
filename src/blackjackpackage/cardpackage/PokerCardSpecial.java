package blackjackpackage.cardpackage;

import java.util.Random;

import javax.swing.ImageIcon;

import utilites.GlobalConstants;
import utilites.SwingUtils;

public class PokerCardSpecial extends PokerCard{
	public static enum Type{
		ACE(11),
		KING(10),
		QUEEN(10),
		JACK(10);
		
		private int score;
		private Type(int score) {this.score = score;}
		
		public int getScore() {return this.score;}
	}
	
	Type cardType;
	Suit cardSuit;
	
	public PokerCardSpecial(Suit cardSuit, Type cardType) 
	{
		this.cardType = cardType;
		this.cardSuit = cardSuit;
		super.setCardScore(cardType.getScore());
		ImageIcon cardImg = SwingUtils.getImage(GlobalConstants.POKERCARD + currentSkin + cardSuit.toString().toLowerCase() + "/" + cardType.toString().toLowerCase() + ".png");
		
		this.setIcon(cardImg);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		
		createSelectImage(cardImg);
	}
	
	public PokerCardSpecial(){
		Random rng = new Random();
		Suit suit = Suit.values()[rng.nextInt(0, Suit.values().length)];
		Type type = Type.values()[rng.nextInt(0, Type.values().length)];
		
		this(suit, type);
	}
}
