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
import blackjackpackage.handpackage.EnemyHand;
import blackjackpackage.handpackage.Hand;
import blackjackpackage.handpackage.PlayerHand;
import entitypackage.Entity;
import mainpackage.MainFrame;
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
	protected int natural = 21;
	private int maxMoney = EventBus.player.getCurrentMoney();
	private int roundCounter = 0;
	protected int debtCounter = 0;
	private BetTable betTable = new BetTable();
	
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
		this.setLayout(new BorderLayout());
		this.setBounds(new Rectangle(1280, 720));
		this.setBackground(new Color(88, 47, 44));
		EventBus.enemy = enemy;
		
		
	}
	
	private void startMatch() {
		EventBus.currentMatch = this;
		
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
        
        MainFrame.getInstance().layerOnScreen(betTable, JLayeredPane.PALETTE_LAYER);
    }
	
	public void addCard(Hand hand) {
		Card addedCard = deck.getCard();
		deck.removeCard(addedCard);
		hand.addNewCard(addedCard);
		
		
		if (hand instanceof PlayerHand && hand.verifySplit()) 
		{
			blackJack.buttonArea.split.setEnabled(true);
		} else {
			blackJack.buttonArea.split.setEnabled(false);
		}
		System.out.println(deck.getSize());
	}
	
	public void reset() {
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
	
	public void onRoundOver(boolean playerWin, boolean isTie, boolean isSplit) {
		blackJack.playerHand.setDebt(Math.max(blackJack.playerHand.getDebt() - blackJack.playerHand.getScore(), 0));
		roundCounter++;
		
		if (isTie) {
			infoArea.addMoney(EventBus.player, playerWin, betPool / 2);
			infoArea.addMoney(EventBus.enemy, playerWin, betPool / 2);
			blackJack.scoreArea.setPlayerLabelText("TIE");
			blackJack.scoreArea.setEnemyLabelText("TIE");
			
			try {
				SoundBus.playSFX("blackJack/tie.wav");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
		}
		
		if (playerWin && !isTie) {
			infoArea.addMoney(EventBus.player, playerWin, betPool);
			blackJack.scoreArea.setPlayerLabelText("WIN");
			blackJack.scoreArea.setEnemyLabelText("LOSE");
			
			
			try {
				SoundBus.playSFX("blackJack/winCheer.wav");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
			
		} else if (!playerWin && !isTie){
			infoArea.addMoney(EventBus.enemy, playerWin, betPool);
			blackJack.scoreArea.setPlayerLabelText("LOSE");
			blackJack.scoreArea.setEnemyLabelText("WIN");
			
			try {
				SoundBus.playSFX("blackJack/loseBoo.wav");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
		}
		
		if (EventBus.player.getCurrentMoney() >= maxMoney) {
			maxMoney = EventBus.player.getCurrentMoney();
		}
		
		if (EventBus.player.getCurrentMoney() <= 0) {
			MainFrame.getInstance().layerOnScreen(new GameOverScreen(maxMoney, debtCounter, roundCounter), JLayeredPane.POPUP_LAYER);
			return;
		}
		
		EventBus.executeAfterTime(2000, new Signal() {
			@Override
			public void doTask() {
				if (isSplit) {
					splitCheck();
				} else {
					reset();
				}
			}
		});
	}
	
	public void checkResults() {
		if (blackJack.enemyHand.getScore() > EventBus.currentMatch.natural && blackJack.playerHand.getScore() > EventBus.currentMatch.natural) {
			if (blackJack.enemyHand.getScore() > blackJack.playerHand.getScore()) {
				onRoundOver(false, false, false);
			} else if (blackJack.enemyHand.getScore() == blackJack.playerHand.getScore()) {
				onRoundOver(false, true, true);
			} else {
				onRoundOver(true, false, true);
			}
		} else if (blackJack.enemyHand.getScore() > EventBus.currentMatch.natural || blackJack.playerHand.getScore() > EventBus.currentMatch.natural) {
			if(blackJack.enemyHand.getScore() > EventBus.currentMatch.natural) {
				onRoundOver(true, false, true);
			} else {
				onRoundOver(false, false, false);
			}
		} else if (blackJack.enemyHand.getScore() == EventBus.currentMatch.natural || blackJack.playerHand.getScore() == EventBus.currentMatch.natural) {
			if (blackJack.enemyHand.getScore() == blackJack.playerHand.getScore()) {
				onRoundOver(false, true, true);
			} else if (blackJack.enemyHand.getScore() == EventBus.currentMatch.natural) {
				onRoundOver(false, false, false);
			} else {
				onRoundOver(true, false, true);
			}
		} else if (blackJack.enemyHand.getScore() > blackJack.playerHand.getScore()){
			onRoundOver(false, false, false);
		} else if (blackJack.enemyHand.getScore() < blackJack.playerHand.getScore()){
			onRoundOver(true, false, true);
		} else {
			onRoundOver(false, true, true);
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
	
	@Override
	public void addNotify() {
		startMatch();
		
		try {
			SoundBus.playSong("letsPlay.wav", true);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
		super.addNotify();
	}
	
	@Override
	public void removeNotify() {
		SoundBus.closeCurrentSong();
		super.removeNotify();
	}
}
