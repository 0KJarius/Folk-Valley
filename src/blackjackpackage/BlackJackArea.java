package blackjackpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import blackjackpackage.handpackage.EnemyHand;
import blackjackpackage.handpackage.PlayerHand;
import utilities.EventBus;
import utilities.SwingUtils;

public class BlackJackArea extends JPanel{
    public PlayerHand playerHand;
    protected LinkedList<PlayerHand> playerHands;
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
    	
    	playerHands = new LinkedList<PlayerHand>();
        playerHands.addLast(new PlayerHand());
        playerHand = playerHands.getFirst();
        
        
        enemyHand = new EnemyHand(EventBus.currentMatch.onStand);
        setUpContentPanel(contentPanel);
        
	}
	
	private void setUpContentPanel(JPanel contentPanel) 
	{
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setOpaque(false);
		
		contentPanel.add(getEnemyHand());
		contentPanel.add(getScoreArea());
		
		contentPanel.add(playerHand);
		contentPanel.add(buttonArea);
	}
	
	public void reset()
    {	
        playerHands.clear();
		playerHands.addLast(playerHand);

        getEnemyHand().resetHand();
        playerHand.resetHand();
        getScoreArea().updateScoreLabel();
        buttonArea.setButtonsActivated(true);
        this.revalidate();
        this.repaint();   
    }
	
	public void setTable(){
        EventBus.currentMatch.addCard(getEnemyHand());
        EventBus.currentMatch.addCard(getEnemyHand());
        
        EventBus.currentMatch.addCard(playerHand);
        EventBus.currentMatch.addCard(playerHand);
        buttonArea.setButtonsActivated(true);
    }
	
	public void replacePlayerHand() {
		buttonArea.split.setEnabled(false);
		contentPanel.remove(playerHand);
		
		int inheritDebt = playerHand.getDebt();
		
        playerHands.remove(playerHand);
        playerHand = playerHands.getFirst();
        playerHand.setDebt(inheritDebt);
        
        contentPanel.remove(buttonArea);
        contentPanel.add(playerHand);
        contentPanel.add(buttonArea);
        
        playerHand.repaint();
        playerHand.revalidate();
        repaint();
        revalidate();
	}

	public EnemyHand getEnemyHand() {
		return enemyHand;
	}

	public ScoreArea getScoreArea() {
		return scoreArea;
	}
	
}
