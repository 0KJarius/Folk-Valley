package blackjackpackage;

import utilites.SwingUtils;

public class PokerCard extends Card{
	private final String POKERCARD = "Cards/Poker Cards/";
	private String currentSkin = "placeHolderCards/";
	private String suit;
	
	public PokerCard(int score, String suit) {
		this.suit = suit;
		super.setCardScore(score);
		
		this.setIcon(SwingUtils.getImage(POKERCARD + currentSkin + suit + "/" + score + ".png"));
		
		this.setText(super.getCardScore() + " of " + suit+ "s");
	}
}
