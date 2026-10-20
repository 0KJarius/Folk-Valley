package blackjackpackage;

public class PokerCard extends Card{
	private String suit;
	
	public PokerCard(int score, String suit) {
		this.suit = suit;
		super.setCardScore(score);

		this.setText(super.getCardScore() + " of " + suit+ "s");
	}
}
