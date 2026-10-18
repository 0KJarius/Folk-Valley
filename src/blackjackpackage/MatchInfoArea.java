package blackjackpackage;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import entitypackage.Entity;
import utilites.EventBus;
import utilites.SwingUtils;

public class MatchInfoArea extends JPanel{
	private JProgressBar playerMoneyProgressBar;
    private JProgressBar enemyMoneyProgressBar;
    private JLabel debtLabel;
    private JLabel betPoolLabel;
	
	public MatchInfoArea() {
		this.setPreferredSize(new Dimension(238, 0));
		this.setBackground(Color.YELLOW);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	this.add(SwingUtils.createJLabel("BET POOL", getFont(), 40, Color.black));
    	debtLabel = SwingUtils.createJLabel(""+ EventBus.currentMatch.blackJack.playerHand.getDebt(), getFont(), 30, Color.RED, Color.BLACK);
        betPoolLabel = SwingUtils.createJLabel("V " + EventBus.currentMatch.betPool, getFont(), 30, Color.GREEN, Color.BLACK);
    	
    	this.add(betPoolLabel);
    	this.add(SwingUtils.createJLabel("DEBT", getFont(), 40, Color.black));
    	this.add(debtLabel);
    	
    	JPanel progressBarArea = SwingUtils.createJPanel(new Dimension(0, 1003), Color.MAGENTA);
    	playerMoneyProgressBar = SwingUtils.createJProgressBar(
    			0, 
    			EventBus.player.getCurrentMoney(), 
    			true, 
    			EventBus.player.getName()+" "+EventBus.player.getCurrentMoney(), 
    			EventBus.player.getCurrentMoney(), Color.BLACK, 
    			Color.white, 
    			new Dimension(50, 480));
        enemyMoneyProgressBar = SwingUtils.createJProgressBar(
        		0, 
        		EventBus.enemy.getCurrentMoney(), 
        		true, EventBus.enemy.getName()+" "+EventBus.enemy.getCurrentMoney(), 
        		EventBus.enemy.getCurrentMoney(), 
        		Color.BLACK, 
        		Color.white, 
        		new Dimension(50, 480));
    	
    	progressBarArea.add(playerMoneyProgressBar);
    	progressBarArea.add(enemyMoneyProgressBar);
    	this.add(progressBarArea);
	}
	
	public void subMoney(Entity entity, boolean isPlayer, int amount)
    {
    	entity.subMoney(amount);
    	updateMoneyBar(entity, isPlayer);
    }
    
	public void addMoney(Entity entity, boolean isPlayer, int amount)
    {
    	entity.addMoney(amount);
    	updateMoneyBar(entity, isPlayer);
    	
    }
	
	public void updateMoneyBar(Entity entity, boolean isPlayer) {
		if (isPlayer) {
			playerMoneyProgressBar.setValue(entity.getCurrentMoney());
			playerMoneyProgressBar.setString(entity.getName()+" "+entity.getCurrentMoney());
		} else {
			enemyMoneyProgressBar.setValue(entity.getCurrentMoney());
    		enemyMoneyProgressBar.setString(entity.getName()+" "+entity.getCurrentMoney());
		}
    	
    }
	
	public void updateDebtLabel(int debtAmount)
    {
        debtLabel.setText("" + debtAmount);
    }
	
	public void updateBetLabel() {
		betPoolLabel.setText("" + EventBus.currentMatch.betPool);
	}
	
	public void setBetLabelText(String text) {
		betPoolLabel.setText(text);
	}
	
	public void reset() {
		setBetLabelText("" + EventBus.currentMatch.betPool);
		updateDebtLabel(EventBus.currentMatch.blackJack.playerHand.getDebt());
        updateMoneyBar(EventBus.player, true);
        updateMoneyBar(EventBus.enemy, false);
	}
}
