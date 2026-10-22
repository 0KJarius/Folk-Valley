package blackjackpackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import utilites.EventBus;

public class ButtonArea extends JPanel{
	JButton hit, stand, split, discard;
	BlackJackArea blackJack;
	
	public ButtonArea(BlackJackArea blackJackArea) 
	{
		this.blackJack = blackJackArea;
		this.setOpaque(false);
		//this.setBackground(Color.RED);
		hit = new JButton("HIT");
		stand = new JButton("STAND");
		split = new JButton("SPLIT");
		discard = new JButton("DISCARD");
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
		
		if(blackJack.enemyHand.getScore() > 21) {
			EventBus.currentMatch.onRoundOver(true, false);
			EventBus.currentMatch.splitCheck();
			
		} else if (blackJack.enemyHand.getScore() == 21) {
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
		
		
		if (blackJack.playerHand.getScore() > 21) {
			EventBus.currentMatch.onRoundOver(false, false);
			EventBus.currentMatch.reset();
		} else if (blackJack.playerHand.getScore() == 21) {
			EventBus.currentMatch.onRoundOver(true, false);
			EventBus.currentMatch.splitCheck();
		}
	}
	
	private void onSplitPressed() {
		blackJack.playerHands.addLast(new Hand());
		blackJack.playerHands.getLast().addNewCard(EventBus.currentMatch.blackJack.playerHands.getFirst().getCards().get(1));
		blackJack.playerHands.getFirst().removeCard(EventBus.currentMatch.blackJack.playerHands.getFirst().getCards().get(1));
		blackJack.scoreArea.setPlayerLabelText("" + EventBus.currentMatch.blackJack.playerHand.getScore());
		blackJack.playerHand.revalidate();
		blackJack.playerHand.repaint();
		
		
		EventBus.currentMatch.blackJack.scoreArea.updateScoreLabel();
		split.setEnabled(false);
    }
}
