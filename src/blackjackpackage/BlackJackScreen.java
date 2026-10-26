package blackjackpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import blackjackpackage.cardpackage.Card;
import entitypackage.Entity;
import menuPackage.GameOverScreen;
import utilities.AnimationUtils;
import utilities.EventBus;
import utilities.Signal;
import utilities.SoundBus;
import utilities.SwingUtils;

public class BlackJackScreen extends JPanel{
	
	protected MatchInfoArea infoArea;
	protected BlackJackArea blackJack;
	protected RightInfoArea rightArea;
	protected  Deck deck;
	protected int betPool;
	protected BetTable betTable;
	protected int natural = 21;
	private int maxMoney = EventBus.player.getCurrentMoney();
	private int roundCounter = 0;
	protected int debtCounter = 0;
	
	protected Signal onStand = new Signal() {

		public void doTask() {
			Timer hitTimer = new Timer();
            TimerTask hitTask = new TimerTask() {
				@Override
				public void run() {
					hitTimer.cancel();
					addCard(blackJack.enemyHand);
					blackJack.scoreArea.setEnemyLabelText("" + blackJack.enemyHand.getScore());
					
					try {
						SoundBus.playSFX("blackJack/reveal.wav");
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
					
					if (blackJack.enemyHand.getScore() < ((natural * 16) / 21)) {
						doTask();
					} else {
						checkTimer();
					}
				}	
            };
            
            hitTimer.scheduleAtFixedRate(hitTask, 1000, 1);
		}
	};
	
	public BlackJackScreen(Entity enemy) {
		EventBus.enemy = enemy;
		EventBus.currentMatch = this;
		this.setLayout(new BorderLayout());
		this.setBounds(new Rectangle(1280, 720));
		this.setBackground(new Color(88, 47, 44));
		
		deck = EventBus.player.playerDeck.cloneDeck();
		rightArea = new RightInfoArea();
		blackJack = new BlackJackArea();
		infoArea = new MatchInfoArea();
		betTable = new BetTable();
		
		this.add(infoArea, BorderLayout.WEST);
		this.add(blackJack, BorderLayout.CENTER);
		this.add(rightArea, BorderLayout.EAST);
		setTable();
	}
	
	public BlackJackScreen(Entity enemy, int natural) {
		this(enemy);
		this.natural = natural;
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
		
		
		if (hand.getCards().size() == 2 && hand.getCards().get(0).getCardScore() == hand.getCards().get(1).getCardScore() && !(hand instanceof EnemyHand)) 
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
	
	public void hardResetStats() {
		roundCounter = 0;
		debtCounter = 0;
		maxMoney = EventBus.player.getCurrentMoney();
	}
	
	public void resetDeck() {
		deck = EventBus.player.playerDeck.cloneDeck();
	}
	
	public void splitCheck() 
    {
    	if(blackJack.playerHands.size() == 1) {
    		reset();
    	} else {
    		blackJack.enemyHand.resetHand();
    		addCard(blackJack.enemyHand);
            addCard(blackJack.enemyHand);
    		
            blackJack.replacePlayerHand();
    		
            blackJack.scoreArea.updateScoreLabel();
            blackJack.buttonArea.setButtonsActivated(true);
    		this.repaint();
    		this.revalidate();
    	}
    	
    }
	
	public void addNatural(int amount) {
		natural += amount;
		rightArea.updateNaturalLabel();
	}
	
	public void addBetPool(int amount) {
		betPool += amount * 2;
		infoArea.updateBetLabel();
		infoArea.subMoney(EventBus.enemy, false, amount);
		infoArea.subMoney(EventBus.player, true, amount);
	}
	
	public void onRoundOver(boolean playerWin, boolean isTie) {
		blackJack.playerHand.setDebt(Math.max(blackJack.playerHand.getDebt() - blackJack.playerHand.getScore(), 0));
		roundCounter++;
		
		if (isTie) {
			infoArea.addMoney(EventBus.player, playerWin, betPool / 2);
			infoArea.addMoney(EventBus.enemy, playerWin, betPool / 2);
			return;
		}
		
		if (playerWin) {
			infoArea.addMoney(EventBus.player, playerWin, betPool);
		} else {
			infoArea.addMoney(EventBus.enemy, playerWin, betPool);
		}
		
		if (EventBus.player.getCurrentMoney() >= maxMoney) {
			maxMoney = EventBus.player.getCurrentMoney();
		}
		
		if (EventBus.player.getCurrentMoney() <= 0) {
			EventBus.frame.layerOnScreen(new GameOverScreen(maxMoney, debtCounter, roundCounter), JLayeredPane.POPUP_LAYER);
		}
	}
	
	public void checkResults() {
		if(blackJack.enemyHand.getScore() > EventBus.currentMatch.natural) {
			EventBus.currentMatch.onRoundOver(true, false);
			EventBus.currentMatch.splitCheck();
			
		} else if (blackJack.enemyHand.getScore() == EventBus.currentMatch.natural) {
			EventBus.currentMatch.onRoundOver(false, false);
			EventBus.currentMatch.reset();
			
		} else if (blackJack.enemyHand.getScore() > blackJack.playerHand.getScore()) {
			EventBus.currentMatch.onRoundOver(false, false);
			EventBus.currentMatch.reset();
			
		} else if (blackJack.enemyHand.getScore() < blackJack.playerHand.getScore()) {
			EventBus.currentMatch.onRoundOver(true, false);
			EventBus.currentMatch.splitCheck();
			
		} else {
			EventBus.currentMatch.onRoundOver(false, true);
			EventBus.currentMatch.splitCheck();
		}
	}
	
	protected void checkTimer() {
		EventBus.executeAfterTime(1000, new Signal() {
			@Override
			public void doTask() {
				checkResults();								
			}
		});
		
	}
}
