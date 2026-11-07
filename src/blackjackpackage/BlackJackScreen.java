package blackjackpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import blackjackpackage.cardpackage.Card;
import blackjackpackage.handpackage.Hand;
import blackjackpackage.handpackage.PlayerHand;
import blackjackpackage.rightsidepackage.RightInfoArea;
import entitypackage.Entity;
import itempackage.Item;
import mainpackage.MainFrame;
import menuPackage.GameOverScreen;
import menuPackage.WinScreen;
import utilities.EventBus;
import utilities.Signal;
import utilities.SoundBus;
import utilities.SwingUtils;

public class BlackJackScreen extends JPanel{
	
	public MatchInfoArea infoArea;
	public BlackJackArea blackJack;
	public RightInfoArea rightArea;
	protected Deck deck;
	private Deck fullDeck;
	protected int betPool;
	private int natural = 21;
	protected int baseNat = 21;
	private int maxMoney = EventBus.player.getCurrentMoney();
	private int roundCounter = 0;
	protected int debtCounter = 0;
	protected BetTable betTable = new BetTable();
	protected Entity enemy;
	public JLabel scanLines;
	protected ArrayList<Item> playerInventoryCopy;
	
	protected Signal onStand = new Signal() {

		public boolean doTask() {
			Timer hitTimer = new Timer();
            TimerTask hitTask = new TimerTask() {
				@Override
				public void run() {
					hitTimer.cancel();
					addCard(blackJack.getEnemyHand());
					
					blackJack.getScoreArea().setEnemyLabelText("" + blackJack.getEnemyHand().getScore());
					
					try {
						SoundBus.playSFX("blackJack/reveal.wav");
					} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
					
					if (blackJack.getEnemyHand().getScore() < ((getNatural() * 16) / 21)) {
						doTask();
					} else {
						checkTimer();
					}
				}	
            };
            
            hitTimer.scheduleAtFixedRate(hitTask, 1000, 1);
            return true;
		}
	};
	
	public BlackJackScreen(Entity enemy, Deck deck) {
		this.setLayout(new BorderLayout());
		this.setBounds(new Rectangle(1280, 720));
		this.setBackground(new Color(88, 47, 44));
		fullDeck = deck.cloneDeck();
		this.deck = deck;
		this.enemy = enemy;
		
		
	}
	
	public BlackJackScreen(Entity enemy, int natural, Deck deck) {
		this(enemy, deck);
		baseNat = natural;
		this.setNatural(natural);
	}
	
	protected void startMatch() {
		EventBus.currentMatch = this;
		
		rightArea = new RightInfoArea();
		blackJack = new BlackJackArea();
		infoArea = new MatchInfoArea();
		this.add(infoArea, BorderLayout.WEST);
		this.add(blackJack, BorderLayout.CENTER);
		this.add(rightArea, BorderLayout.EAST);
		setTable();
	}
	
	
	
	protected void setTable(){
		addCard(blackJack.getEnemyHand());
        addCard(blackJack.getEnemyHand());
        
        addCard(blackJack.playerHand);
        addCard(blackJack.playerHand);
        blackJack.getScoreArea().setPlayerLabelText("" + blackJack.playerHand.getScore());
        blackJack.getScoreArea().setEnemyLabelText("" + blackJack.getEnemyHand().getScore());
        
        MainFrame.getInstance().layerOnScreen(betTable, JLayeredPane.PALETTE_LAYER);
    }
	
	public void addCard(Hand hand) {
		Card addedCard = getDeck().getCard();
		getDeck().removeCard(addedCard);
		hand.addNewCard(addedCard);
		
		
		if (hand instanceof PlayerHand && hand.verifySplit()) 
		{
			blackJack.buttonArea.split.setEnabled(true);
		} else {
			blackJack.buttonArea.split.setEnabled(false);
		}
		rightArea.getBottom().updateCardCountLabel();
		if (getDeck().getSize() == 0) {
			MainFrame.getInstance().layerOnScreen(new GameOverScreen(maxMoney, debtCounter, roundCounter), JLayeredPane.POPUP_LAYER);
		}
	}
	
	public void reset() {
		betPool = 0;
		blackJack.reset();
		infoArea.reset();
		setTable();
		infoArea.reset();
		
	}
	
	public void hardResetStats() {
		enemy.resetWallet();
		roundCounter = 0;
		debtCounter = 0;
		maxMoney = EventBus.player.getCurrentMoney();
	}
	
	public void resetDeck() {
		System.out.println(fullDeck.getSize());
		deck = fullDeck.cloneDeck();
	}
	
	public void splitCheck() 
    {
    	if(blackJack.playerHands.size() == 1) {
    		reset();
    	} else {
    		blackJack.getEnemyHand().resetHand();
    		addCard(blackJack.getEnemyHand());
            addCard(blackJack.getEnemyHand());
    		
            blackJack.replacePlayerHand();
    		
            blackJack.getScoreArea().updateScoreLabel();
            blackJack.buttonArea.setButtonsActivated(true, 2);
    		this.repaint();
    		this.revalidate();
    	}
    	
    }
	
	public void addNatural(int amount) {
		setNatural(getNatural() + amount);
		rightArea.updateNaturalLabel();
	}
	
	public void addBetPool(int amount) {
		int enemyBet = enemy.getCurrentMoney() < amount ? enemy.getCurrentMoney() : amount;
		betPool += amount + enemyBet;
		infoArea.updateBetLabel();
		infoArea.subMoney(enemy, false, enemyBet);
		infoArea.subMoney(EventBus.player, true, amount);
	}
	
	public void onRoundOver(boolean playerWin, boolean isTie, boolean isSplit) {
		blackJack.playerHand.setDebt(Math.max(blackJack.playerHand.getDebt() - blackJack.playerHand.getScore(), 0));
		roundCounter++;
		
		if (isTie) {
			infoArea.addMoney(EventBus.player, playerWin, betPool / 2);
			infoArea.addMoney(enemy, playerWin, betPool / 2);
			blackJack.getScoreArea().setPlayerLabelText("TIE");
			blackJack.getScoreArea().setEnemyLabelText("TIE");
			
			try {
				SoundBus.playSFX("blackJack/tie.wav");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
		}
		
		if (playerWin && !isTie) {
			infoArea.addMoney(EventBus.player, playerWin, betPool);
			blackJack.getScoreArea().setPlayerLabelText("WIN");
			blackJack.getScoreArea().setEnemyLabelText("LOSE");
			
			
			try {
				SoundBus.playSFX("blackJack/winCheer.wav");
			} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
			
		} else if (!playerWin && !isTie){
			infoArea.addMoney(enemy, playerWin, betPool);
			blackJack.getScoreArea().setPlayerLabelText("LOSE");
			blackJack.getScoreArea().setEnemyLabelText("WIN");
			
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
		
		if (enemy.getCurrentMoney() <= 0) {
			MainFrame.getInstance().layerOnScreen(new WinScreen(debtCounter, roundCounter), JLayeredPane.POPUP_LAYER);
			return;
		}
		
		EventBus.executeAfterTime(2000, new Signal() {
			@Override
			public boolean doTask() {
				if (isSplit) {
					splitCheck();
				} else {
					reset();
				}
				return true;
			}
		});
	}
	
	public void checkResults() {
		if (blackJack.getEnemyHand().getScore() > EventBus.currentMatch.getNatural() && blackJack.playerHand.getScore() > EventBus.currentMatch.getNatural()) {
			if (blackJack.getEnemyHand().getScore() > blackJack.playerHand.getScore()) {
				onRoundOver(false, false, false);
			} else if (blackJack.getEnemyHand().getScore() == blackJack.playerHand.getScore()) {
				onRoundOver(false, true, true);
			} else {
				onRoundOver(true, false, true);
			}
		} else if (blackJack.getEnemyHand().getScore() > EventBus.currentMatch.getNatural() || blackJack.playerHand.getScore() > EventBus.currentMatch.getNatural()) {
			if(blackJack.getEnemyHand().getScore() > EventBus.currentMatch.getNatural()) {
				onRoundOver(true, false, true);
			} else {
				onRoundOver(false, false, false);
			}
		} else if (blackJack.getEnemyHand().getScore() == EventBus.currentMatch.getNatural() || blackJack.playerHand.getScore() == EventBus.currentMatch.getNatural()) {
			if (blackJack.getEnemyHand().getScore() == blackJack.playerHand.getScore()) {
				onRoundOver(false, true, true);
			} else if (blackJack.getEnemyHand().getScore() == EventBus.currentMatch.getNatural()) {
				onRoundOver(false, false, false);
			} else {
				onRoundOver(true, false, true);
			}
		} else if (blackJack.getEnemyHand().getScore() > blackJack.playerHand.getScore()){
			onRoundOver(false, false, false);
		} else if (blackJack.getEnemyHand().getScore() < blackJack.playerHand.getScore()){
			onRoundOver(true, false, true);
		} else {
			onRoundOver(false, true, true);
		}
		
	}
	
	protected void checkTimer() {
		EventBus.executeAfterTime(1000, new Signal() {
			@Override
			public boolean doTask() {
				checkResults();	
				return true;
			}
		});
		
	}
	
	public void leaveMatch() {
		betPool = 0;
		blackJack.reset();
		infoArea.reset();
		deck = fullDeck.cloneDeck();
		setNatural(baseNat);
		this.removeAll();
	}
	
	public int getNatural() {
		return natural;
	}

	public void setNatural(int natural) {
		this.natural = natural;
	}
	
	public BetTable getBetTable() {
		return betTable;
	}
	
	public Deck getDeck() {
		return deck;
	}

	public ArrayList<Item> getPlayerInventoryCopy() {
		return playerInventoryCopy;
	}
	
	
	public void setPlayerInventoryCopy(ArrayList<Item> playerInventoryCopy) {
		this.playerInventoryCopy = playerInventoryCopy;
	}

	public void setBetTable(BetTable betTable) {
		this.betTable = betTable;
	}

	@Override
	public void addNotify() {
		super.addNotify();
		playerInventoryCopy = EventBus.player.copyInventory();
		
		
		startMatch();
		scanLines = SwingUtils.createImageAt("BlackJack/scanLinesOverlay.png", new Point(0, 0));
		MainFrame.getInstance().layerOnScreen(scanLines, JLayeredPane.MODAL_LAYER);
		
		try {
			SoundBus.playSong("letsPlay.wav", true);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {}
	}
	

	@Override
	public void removeNotify() {
		leaveMatch();
		
		SoundBus.closeCurrentSong();
		super.removeNotify();
	}
}
