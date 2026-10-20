package blackjackpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import utilites.EventBus;

public class BlackJackArea extends JPanel{
    public Hand playerHand;
    public LinkedList<Hand> playerHands;
    public EnemyHand enemyHand;
    public ScoreArea scoreArea;
    public ButtonArea buttonArea;
	
	public BlackJackArea() 
	{
		this.setPreferredSize(new Dimension(804, 0));
		this.setBackground(Color.CYAN);
		this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	
    	scoreArea = new ScoreArea();
    	buttonArea = new ButtonArea();
    	
    	playerHands = new LinkedList<Hand>();
        playerHands.addLast(new Hand());
        playerHand = playerHands.getFirst();
        enemyHand = new EnemyHand();
        
        this.add(enemyHand);
        this.add(scoreArea);
        this.add(playerHand);
        this.add(buttonArea);
	}
	
	public void reset()
    {	
    	System.out.println("RESET");
        playerHands.clear();
		playerHands.addLast(playerHand);

        enemyHand.resetHand();
        playerHand.resetHand();
        this.revalidate();
        this.repaint();   
    }
	
	public void setTable(){
        EventBus.currentMatch.addCard(enemyHand);
        EventBus.currentMatch.addCard(enemyHand);
        
        EventBus.currentMatch.addCard(playerHand);
        EventBus.currentMatch.addCard(playerHand);
    }
	
	public void replacePlayerHand() {
		buttonArea.split.setEnabled(false);
		this.remove(playerHand);
        playerHands.remove(playerHand);
        playerHand = playerHands.getFirst();
        
        this.remove(buttonArea);
        this.add(playerHand);
        this.add(buttonArea);
        
        playerHand.repaint();
        playerHand.revalidate();
        repaint();
        revalidate();
	}
	
}
