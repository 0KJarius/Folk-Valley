package blackjackpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import entitypackage.Entity;
import entitypackage.Player;
import utilites.EventBus;
import utilites.SwingUtils;

public class BlackJackScreen extends JPanel{
	
	public MatchInfoArea infoArea;
	public BlackJackArea blackJack;
	public Deck deck;
	protected int betPool;
	public BetTable betTable;
	
	public BlackJackScreen(Entity enemy) {
		EventBus.enemy = enemy;
		EventBus.currentMatch = this;
		this.setLayout(new BorderLayout());
		this.setBounds(new Rectangle(1280, 720));
		JPanel rightArea = SwingUtils.createJPanel(new Dimension(238, 0), Color.WHITE);
		deck = EventBus.player.playerDeck.cloneDeck();
		
		blackJack = new BlackJackArea();
		infoArea = new MatchInfoArea();
		betTable = new BetTable();
		
		this.add(infoArea, BorderLayout.WEST);
		this.add(blackJack, BorderLayout.CENTER);
		this.add(rightArea, BorderLayout.EAST);
		setTable();
	}
	
	private void setTable(){
		addCard(blackJack.enemyHand);
        addCard(blackJack.enemyHand);
        
        addCard(blackJack.playerHand);
        addCard(blackJack.playerHand);
        blackJack.scoreArea.setPlayerLabelText("" + blackJack.playerHand.getScore());
        blackJack.scoreArea.setEnemyLabelText("" + blackJack.enemyHand.getScore());
        
        EventBus.frame.layerOnScreen(betTable, JLayeredPane.PALETTE_LAYER);
    }
	
	public void addCard(Hand hand) {
		Card addedCard = deck.getCard();
		deck.removeCard(addedCard);
		hand.addNewCard(addedCard);
		
		if (hand.getCards().size() == 2 && hand.getCards().get(0).getCardScore() == hand.getCards().get(1).getCardScore()) 
		{
			blackJack.buttonArea.split.setEnabled(true);
		} else {
			blackJack.buttonArea.split.setEnabled(false);
		}
	}
	
	public void reset() {
		EventBus.frame.layerOnScreen(new BetTable(), JLayeredPane.PALETTE_LAYER);
		betPool = 0;
		blackJack.reset();
		infoArea.reset();
		setTable();
		infoArea.reset();
	}
	
	public void splitCheck() 
    {
    	System.out.println(blackJack.playerHands.size());
    	if(blackJack.playerHands.size() == 1) {
    		reset();
    	} else {
    		blackJack.enemyHand.resetHand();
    		addCard(blackJack.enemyHand);
            addCard(blackJack.enemyHand);
    		
            blackJack.replacePlayerHand();
    		
            blackJack.scoreArea.updateScoreLabel();
    		this.repaint();
    		this.revalidate();
    		System.out.println("SIZE " + blackJack.playerHands.size());
    	}
    	
    }
	
	public void addBetPool(int amount) {
		betPool += amount * 2;
		infoArea.updateBetLabel();
		infoArea.subMoney(EventBus.enemy, false, amount);
		infoArea.subMoney(EventBus.player, true, amount);
	}
	
	public void onRoundOver(boolean playerWin, boolean isTie) {
		blackJack.playerHand.setDebt(Math.max(blackJack.playerHand.getDebt() - blackJack.playerHand.getScore(), 0));
		
		if (isTie) {
			return;
		}
		
		if (playerWin) {
			infoArea.addMoney(EventBus.player, playerWin, betPool);
		} else {
			infoArea.addMoney(EventBus.enemy, playerWin, betPool);
		}
	}
	
}
