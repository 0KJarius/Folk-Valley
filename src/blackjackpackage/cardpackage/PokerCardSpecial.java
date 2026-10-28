package blackjackpackage.cardpackage;

import java.util.Random;

import utilities.GlobalConstants;
import utilities.SwingUtils;

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
	
	public PokerCardSpecial(Suit cardSuit, Type cardType) 
	{
		this.cardType = cardType;
		super.setSuit(cardSuit);
		super.setCardScore(cardType.getScore());
		super.setCardImg(SwingUtils.getImage(GlobalConstants.POKERCARD + currentSkin + cardSuit.toString().toLowerCase() + "/" + cardType.toString().toLowerCase() + ".png"));

		this.setBorderPainted(false);
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		
		super.setSelectedCardImg(createSelectImage(super.getCardImg()));
	}
	
	public PokerCardSpecial(){
		this(Suit.values()[new Random().nextInt(0, Suit.values().length)], Type.values()[new Random().nextInt(0, Type.values().length)]);
	}

		public Type getType() {
		return cardType;
	}
}
