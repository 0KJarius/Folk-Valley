package blackjackpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import utilites.EventBus;
import utilites.SwingUtils;

public class BlackJackArea extends JPanel{
    protected Hand playerHand;
    protected LinkedList<Hand> playerHands;
    protected EnemyHand enemyHand;
    protected ScoreArea scoreArea;
    protected ButtonArea buttonArea;
    private JPanel contentPanel;
	
	public BlackJackArea() 
	{
		this.setPreferredSize(new Dimension(805, 0));
		this.setOpaque(false);
    	this.setLayout(new BorderLayout());
    	
    	JLayeredPane basePane = new JLayeredPane();
    	basePane.setPreferredSize(new Dimension(805, 712));
    	this.add(basePane);
    	
    	contentPanel = SwingUtils.createJPanel(new Rectangle(805, 712), Color.cyan);
    	basePane.add(contentPanel, JLayeredPane.PALETTE_LAYER);
    	basePane.add(SwingUtils.createImageAt("BlackJack/playArea.png", new Point(0,0)), JLayeredPane.DEFAULT_LAYER);
    	
    	scoreArea = new ScoreArea();
    	buttonArea = new ButtonArea(this);
    	
    	playerHands = new LinkedList<Hand>();
        playerHands.addLast(new Hand());
        playerHand = playerHands.getFirst();
        enemyHand = new EnemyHand();
        setUpContentPanel(contentPanel);
        
	}
	
	private void setUpContentPanel(JPanel contentPanel) 
	{
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);
		
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		contentPanel.add(enemyHand);
		
		scoreArea.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));
		contentPanel.add(scoreArea);
		contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		contentPanel.add(playerHand);
		contentPanel.add(buttonArea);
	}
	
	public void reset()
    {	
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
		contentPanel.remove(playerHand);
        playerHands.remove(playerHand);
        playerHand = playerHands.getFirst();
        

        contentPanel.remove(buttonArea);
        contentPanel.add(playerHand);
        contentPanel.add(buttonArea);
        
        playerHand.repaint();
        playerHand.revalidate();
        repaint();
        revalidate();
	}
	
}
