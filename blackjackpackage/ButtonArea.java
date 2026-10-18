package blackjackpackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import utilites.EventBus;

public class ButtonArea extends JPanel{
	JButton hit, stand, split, discard;	
	public ButtonArea() 
	{
		this.setBackground(Color.RED);
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
		if (EventBus.currentMatch.blackJack.playerHand.getSelectedCards().size() == EventBus.currentMatch.blackJack.playerHand.getCards().size() 
			|| EventBus.currentMatch.blackJack.playerHand.getSelectedCards().isEmpty()) {
			return;
		}
		EventBus.currentMatch.blackJack.playerHand.removeSelectedCards();
		EventBus.currentMatch.infoArea.updateDebtLabel(EventBus.currentMatch.blackJack.playerHand.getDebt());
		
		if (EventBus.currentMatch.blackJack.playerHand.getCards().size() == 2 
			&& EventBus.currentMatch.blackJack.playerHand.getCards().get(0).getCardScore() == EventBus.currentMatch.blackJack.playerHand.getCards().get(1).getCardScore()) 
		{
			EventBus.currentMatch.blackJack.buttonArea.split.setEnabled(true);
		} else {
			EventBus.currentMatch.blackJack.buttonArea.split.setEnabled(false);
		}
	}
	
	private void onStand() {
		EventBus.currentMatch.blackJack.playerHand.setScore(EventBus.currentMatch.blackJack.playerHand.getScore() - EventBus.currentMatch.blackJack.playerHand.getDebt());
		
		EventBus.currentMatch.blackJack.enemyHand.reveal();
		EventBus.currentMatch.blackJack.scoreArea.updateScoreLabel();
		
		if(EventBus.currentMatch.blackJack.enemyHand.getScore() > 21) {
			EventBus.currentMatch.onRoundOver(true, false);
			EventBus.currentMatch.splitCheck();
			
		} else if (EventBus.currentMatch.blackJack.enemyHand.getScore() == 21) {
			EventBus.currentMatch.onRoundOver(false, false);
			EventBus.currentMatch.reset();
			
		} else if (EventBus.currentMatch.blackJack.enemyHand.getScore() > EventBus.currentMatch.blackJack.playerHand.getScore()) {
			EventBus.currentMatch.onRoundOver(false, false);
			EventBus.currentMatch.reset();
			
		} else if (EventBus.currentMatch.blackJack.enemyHand.getScore() < EventBus.currentMatch.blackJack.playerHand.getScore()) {
			EventBus.currentMatch.onRoundOver(true, false);
			EventBus.currentMatch.splitCheck();
			
		} else {
			EventBus.currentMatch.onRoundOver(false, true);
			EventBus.currentMatch.splitCheck();
		}
	}
	
	private void onHit() {
		EventBus.currentMatch.addCard(EventBus.currentMatch.blackJack.playerHand);

		EventBus.currentMatch.blackJack.scoreArea.updateScoreLabel();
		
		if (EventBus.currentMatch.blackJack.playerHand.getScore() > 21) {
			EventBus.currentMatch.onRoundOver(false, false);
			EventBus.currentMatch.reset();
		} else if (EventBus.currentMatch.blackJack.playerHand.getScore() == 21) {
			EventBus.currentMatch.onRoundOver(true, false);
			EventBus.currentMatch.splitCheck();
		}
	}
	
	private void onSplitPressed() {
		EventBus.currentMatch.blackJack.playerHands.addLast(new Hand());
		EventBus.currentMatch.blackJack.playerHands.getLast().addNewCard(EventBus.currentMatch.blackJack.playerHands.getFirst().getCards().get(1));
		EventBus.currentMatch.blackJack.playerHands.getLast().repaint();
		EventBus.currentMatch.blackJack.playerHands.getFirst().removeCard(EventBus.currentMatch.blackJack.playerHands.getFirst().getCards().get(1));
		EventBus.currentMatch.blackJack.scoreArea.setEnemyLabelText("" + EventBus.currentMatch.blackJack.enemyHand.getScore());
		EventBus.currentMatch.blackJack.playerHand.revalidate();
		EventBus.currentMatch.blackJack.playerHand.repaint();
		
		EventBus.currentMatch.blackJack.scoreArea.updateScoreLabel();
    }
}
