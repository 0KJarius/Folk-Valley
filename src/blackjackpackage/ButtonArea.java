package blackjackpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import utilites.EventBus;
import utilites.SwingUtils;

public class ButtonArea extends JPanel{
	JButton hit, stand, split, discard;
	private final String BUTTON_PATH = "BlackJack/blackJackButtons/";
	private final String HIT_PATH = BUTTON_PATH + "hit/";
	private final String STAND_PATH = BUTTON_PATH + "stand/";
	private final String SPLIT_PATH = BUTTON_PATH + "split/";
	private final String DISCARD_PATH = BUTTON_PATH + "discard/";
	
	BlackJackArea blackJack;
	
	public ButtonArea(BlackJackArea blackJackArea) 
	{
		this.blackJack = blackJackArea;
		this.setPreferredSize(new Dimension(0, 48));
		this.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
		this.setOpaque(false);
		this.setBackground(Color.RED);
		
		hit = SwingUtils.createJButton(HIT_PATH + "hit.png", HIT_PATH + "hit_highlight.png", HIT_PATH + "hit_pressed.png", HIT_PATH + "hit_disable.png");
		stand = SwingUtils.createJButton(STAND_PATH + "stand.png", STAND_PATH + "stand_highlight.png", STAND_PATH + "stand_pressed.png", STAND_PATH + "stand_disable.png");
		split = SwingUtils.createJButton(SPLIT_PATH + "split.png", SPLIT_PATH + "split_highlight.png", SPLIT_PATH + "split_pressed.png", SPLIT_PATH + "split_disable.png");
		discard = SwingUtils.createJButton(DISCARD_PATH + "discard.png", DISCARD_PATH + "discard_highlight.png", DISCARD_PATH + "discard_pressed.png", DISCARD_PATH + "discard_disable.png");
		this.add(hit);
		this.add(stand);
		this.add(split);
		this.add(discard);
		setUpHit();
		setUpStand();
		setUpSplit();
		setUpDiscard();
	}
	
	private void setUpHit() {
		hit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onHit();
			}
		});
	}
	
	private void setUpStand() {
		stand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onStand();
			}
		});
		
	}
	
	private void setUpSplit() {
		split.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onSplitPressed();
			}
		});
	}
	
	private void setUpDiscard() {
		discard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onDiscard();
			}
			
		});
	}
	
	private void onDiscard() {
		if (blackJack.playerHand.getSelectedCards().size() == blackJack.playerHand.getCards().size() 
			||blackJack.playerHand.getSelectedCards().isEmpty()) {
			return;
		}
		blackJack.playerHand.removeSelectedCards();
		EventBus.currentMatch.infoArea.updateDebtLabel(blackJack.playerHand.getDebt());
		
		if (blackJack.playerHand.getCards().size() == 2 
			&& blackJack.playerHand.getCards().get(0).getCardScore() == blackJack.playerHand.getCards().get(1).getCardScore()) 
		{
			blackJack.buttonArea.split.setEnabled(true);
		} else {
			blackJack.buttonArea.split.setEnabled(false);
		}
	}
	
	private void onStand() {
		blackJack.playerHand.setScore(blackJack.playerHand.getScore() - blackJack.playerHand.getDebt());
		
		blackJack.enemyHand.reveal();
		blackJack.scoreArea.updateScoreLabel();
		
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
	
	private void onHit() {
		EventBus.currentMatch.addCard(blackJack.playerHand);

		blackJack.scoreArea.updateScoreLabel();
		
		
		if (blackJack.playerHand.getScore() > EventBus.currentMatch.natural) {
			EventBus.currentMatch.onRoundOver(false, false);
			EventBus.currentMatch.reset();
		} else if (blackJack.playerHand.getScore() == EventBus.currentMatch.natural && blackJack.playerHand.getDebt() <= 0) {
			EventBus.currentMatch.onRoundOver(true, false);
			EventBus.currentMatch.splitCheck();
		}
	}
	
	private void onSplitPressed() {
		blackJack.playerHand.setDebt(blackJack.playerHand.getDebt() / 2);
		EventBus.currentMatch.addNatural(blackJack.playerHand.getDebt() % 2);
		
		blackJack.playerHands.addLast(new Hand());
		blackJack.playerHands.getLast().addNewCard(EventBus.currentMatch.blackJack.playerHands.getFirst().getCards().get(1));
		blackJack.playerHands.getFirst().removeCard(EventBus.currentMatch.blackJack.playerHands.getFirst().getCards().get(1));
		blackJack.scoreArea.setPlayerLabelText("" + EventBus.currentMatch.blackJack.playerHand.getScore());
		blackJack.playerHand.revalidate();
		blackJack.playerHand.repaint();
		
		EventBus.currentMatch.infoArea.updateDebtLabel(blackJack.playerHand.getDebt());
		EventBus.currentMatch.blackJack.scoreArea.updateScoreLabel();
		split.setEnabled(false);
    }
}
