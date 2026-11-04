package blackjackpackage.custom_matchpackage.tutorial;

import javax.swing.JLayeredPane;

import blackjackpackage.BlackJackScreen;
import entitypackage.Entity;
import mainpackage.MainFrame;

public class TutorialMatch extends BlackJackScreen{
	private int roundCount = 1;
	
	
	public TutorialMatch(Entity enemy) {
		super(enemy, new TutorialDeck());
	}
	
	public TutorialMatch(Entity enemy, int natural) {
		super(enemy, natural, new TutorialDeck());
	}

	@Override
	public void onRoundOver(boolean playerWin, boolean isTie, boolean isSplit) {
		// TODO Auto-generated method stub
		super.onRoundOver(playerWin, isTie, isSplit);
	}

	@Override
	public void checkResults() {
		roundCount ++;
		super.checkResults();
		System.out.println(roundCount);
	}

	@Override
	public void addNotify() {
		super.addNotify();
		MainFrame.getInstance().layerOnScreen(new TutorialPrompt(), JLayeredPane.DRAG_LAYER);
	}
}
