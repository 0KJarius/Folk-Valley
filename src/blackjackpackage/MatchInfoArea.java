package blackjackpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import entitypackage.Entity;
import utilities.EventBus;
import utilities.SwingUtils;

public class MatchInfoArea extends JPanel{
	private JProgressBar playerMoneyProgressBar;
    private JProgressBar enemyMoneyProgressBar;
    private JLabel playerMoney;
    private JLabel enemyMoney;
    private JLabel debtLabel;
    private JLabel betPoolLabel;
    
    private JLayeredPane basePane;
	
	public MatchInfoArea() {
		this.setPreferredSize(new Dimension(260, 0));
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		basePane = new JLayeredPane();
		basePane.setPreferredSize(new Dimension(260, 720));
		this.add(basePane, BorderLayout.CENTER);
		
		basePane.add(SwingUtils.createImageAt("BlackJack/infoArea.png", new Point(0,0)));
		
		JPanel topInfoContainer = SwingUtils.createJPanel(new Rectangle(5, 6, 251,189), getBackground());
		setUpTopPanel(topInfoContainer);
		
		basePane.add(topInfoContainer, JLayeredPane.PALETTE_LAYER);
    	
    	JPanel progressBarArea = SwingUtils.createJPanel(new Rectangle(5, 204, 249, 512), Color.MAGENTA);
    	setUpProgressPanel(progressBarArea);
    	
        basePane.add(progressBarArea, JLayeredPane.PALETTE_LAYER);
	}
	
	private void setUpTopPanel(JPanel topPanel) {
		topPanel.setOpaque(false);
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		
		debtLabel = SwingUtils.createJLabel(""+ EventBus.currentMatch.blackJack.playerHand.getDebt(), 40, Color.RED, SwingUtils.VCR_MONO);
        betPoolLabel = SwingUtils.createJLabel("V" + EventBus.currentMatch.betPool, 40, new Color(255, 214, 55), SwingUtils.VCR_MONO);
        debtLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        betPoolLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        
        topPanel.add(Box.createRigidArea(new Dimension(0, 48)));
        topPanel.add(betPoolLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 53)));
        topPanel.add(debtLabel);
	}
	
	private void setUpProgressPanel(JPanel progPanel) {
		progPanel.setOpaque(false);
		progPanel.setLayout(new BorderLayout());
		
		playerMoney = SwingUtils.createJLabel(EventBus.player.getCurrentMoney() + "/" + EventBus.player.getMaxMoney(), 45, new Color(125, 212, 63), SwingUtils.NIKKYOU_SANS);
		enemyMoney = SwingUtils.createJLabel(EventBus.enemy.getCurrentMoney() + "/" + EventBus.enemy.getMaxMoney(), 45, new Color(125, 212, 63), SwingUtils.NIKKYOU_SANS);
		enemyMoney.setHorizontalAlignment(SwingConstants.RIGHT);
		
		enemyMoney.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
		playerMoney.setBorder(BorderFactory.createEmptyBorder(2, 5, 0, 0));
		
		JPanel barContainer = new JPanel();
		barContainer.setOpaque(false);
		
		playerMoneyProgressBar = SwingUtils.createJProgressBar(
    			0, 
    			EventBus.player.getCurrentMoney(), 
    			true, 
    			EventBus.player.getName(), 
    			EventBus.player.getCurrentMoney(), 
    			new Color(125, 212, 63), 
    			Color.BLACK, 
    			new Dimension(50, 404));
        enemyMoneyProgressBar = SwingUtils.createJProgressBar(
        		0, 
        		EventBus.enemy.getCurrentMoney(), 
        		true, EventBus.enemy.getName(), 
        		EventBus.enemy.getCurrentMoney(), 
        		new Color(125, 212, 63), 
        		Color.BLACK, 
        		new Dimension(50, 404));
        
        barContainer.add(playerMoneyProgressBar);
        barContainer.add(Box.createRigidArea(new Dimension(125, 0)));
        barContainer.add(enemyMoneyProgressBar);
        
        progPanel.add(barContainer, BorderLayout.CENTER);
        progPanel.add(playerMoney, BorderLayout.NORTH);
		progPanel.add(enemyMoney, BorderLayout.SOUTH);
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
			playerMoney.setText(entity.getCurrentMoney() + "/" + entity.getMaxMoney());
			playerMoneyProgressBar.setValue(entity.getCurrentMoney());
			playerMoneyProgressBar.setString(entity.getName());
			playerMoneyProgressBar.repaint();
		} else {
			enemyMoney.setText(entity.getCurrentMoney() + "/" + entity.getMaxMoney());
			enemyMoneyProgressBar.setValue(entity.getCurrentMoney());
    		enemyMoneyProgressBar.setString(entity.getName());
    		enemyMoneyProgressBar.repaint();
		}
    	
    }
	
	public void updateDebtLabel(int debtAmount)
    {
        debtLabel.setText("" + debtAmount);
    }
	
	public void updateBetLabel() {
		betPoolLabel.setText("V" + EventBus.currentMatch.betPool);
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
