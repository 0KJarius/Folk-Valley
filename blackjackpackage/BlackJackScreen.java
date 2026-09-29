package blackjackpackage;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlackJackScreen extends JFrame
{
    private Hand player;
    private LinkedList<Hand> playerHands;
    private EnemyHand enemy;
    private JButton hitButton;
    private JButton standButton;
    private JButton splitButton;
    private JLabel playerHpLabel;
    private JLabel enemyHpLabel;
    private JLabel playerPointLabel;
    private JLabel enemyPointLabel;
    private JLabel debtLabel;
    private BetTable betTable;
    int betPool;
    private JLabel betPoolLabel;
    private JPanel middleThing;
    private JPanel rootPanel;
    private Deck deck;

    public BlackJackScreen(Deck playerDeck)
    {

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        rootPanel = new JPanel();
        this.setContentPane(rootPanel);
        rootPanel.setLayout(new BorderLayout());
        
        this.deck = playerDeck;
        playerHands = new LinkedList<Hand>();
        playerHands.addLast(new Hand(this));
        player = playerHands.getFirst();
        enemy = new EnemyHand(this);
        playerHpLabel = new JLabel("PLAYER: " + player.getHealth());
        enemyHpLabel = new JLabel("ENEMY: " + player.getHealth());
        playerPointLabel = new JLabel("PLAYER P : " + player.getCards());
        enemyPointLabel = new JLabel("ENEMY P : " + enemy.getCards());
        debtLabel = new JLabel("DEBT: " + player.getDebt());
        betPoolLabel = new JLabel("BET POOL: " + betPool);
        hitButton = new JButton("HIT");
        standButton = new JButton("STAND");
        splitButton = new JButton("SPLIT");
        
        hitButton.setFocusPainted(false);
        standButton.setFocusPainted(false);
        splitButton.setFocusPainted(false);
        
        splitButton.setEnabled(false);
        
        betTable = new BetTable(this);
        initializeTable();
        rootPanel.add(betTable);
        reset();
        this.pack();
        this.setSize(new Dimension(1280, 720));
        this.setResizable(false);
    }

    public void initializeTable()
    {

    	rootPanel.add(enemy, BorderLayout.NORTH);

    	rootPanel.add(player,  BorderLayout.SOUTH);

        middleThing = new JPanel();
        rootPanel.add(middleThing);
        middleThing.add(playerHpLabel);
        middleThing.add(enemyHpLabel);
        middleThing.add(betPoolLabel);
        
        middleThing.add(hitButton);
        hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onHitButtonPressed();
            }
            
        });
        
        middleThing.add(standButton);
        standButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onStandButtonPressed();
            }
            
        });
        
        middleThing.add(splitButton);
        splitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		onSplitPressed();
            }
        });
        
        middleThing.add(playerPointLabel);
        middleThing.add(enemyPointLabel);
        middleThing.add(debtLabel);

    }
    
    private void setTable(){
        addCard(enemy);
        addCard(enemy);
        
        addCard(player);
        addCard(player);
        playerPointLabel.setText("PLAYER P : " + player.getScore());
        enemyPointLabel.setText("ENEMY P : " + enemy.getScore());
    }
    
    public void onHitButtonPressed()
    {
    	addCard(player);
        player.revalidate();

        if (player.getScore() == 21){
            updateHealth(enemy, enemyHpLabel, "ENEMY");
            splitCheck();
        } else if (player.getScore() > 21){
            updateHealth(player, playerHpLabel, "PLAYER");
            reset();
        }
    }
    
    public void addCard(Hand hand) {
    	Card addedCard = deck.getCard();
    	hand.addNewCard(addedCard);
    	deck.removeCard(addedCard);
    }
    
    public void onStandButtonPressed()
    {
    	player.applyDebt();
    	enemy.reveal();

        if (enemy.getScore() == 21){
            updateHealth(player, playerHpLabel, "PLAYER");
            
        } else if (enemy.getScore() > 21){
            updateHealth(enemy, enemyHpLabel, "ENEMY");
            
        } else if (enemy.getScore() > player.getScore()){
            updateHealth(player, playerHpLabel, "PLAYER");
            
        } else if (enemy.getScore() < player.getScore()){
            updateHealth(enemy, enemyHpLabel, "ENEMY");
            
        }
        splitCheck();
    }
    
    private void onSplitPressed() {
    	playerHands.addLast(new Hand(this));
    	playerHands.getLast().addNewCard(playerHands.getFirst().getCards().get(1));
    	playerHands.getFirst().removeCard(playerHands.getFirst().getCards().get(1));
    	player.revalidate();
    }
    
    public void splitCheck() 
    {
    	System.out.println(playerHands.size());
    	if(playerHands.size() == 1) {
    		reset();
    	} else {
    		enemy.resetHand();
    		addCard(enemy);
            addCard(enemy);
    		
    		rootPanel.remove(player);
    		playerHands.remove(player);
    		player = playerHands.getFirst();
    		rootPanel.add(player, BorderLayout.SOUTH);
    		
    		playerPointLabel.setText("PLAYER P : " + player.getScore());
    		rootPanel.repaint();
    		rootPanel.revalidate();
    		System.out.println("SIZE " + playerHands.size());
    	}
    	
    }
    

    public void hideBetTable()
    {
    	rootPanel.remove(betTable);
    	rootPanel.add(middleThing);
        middleThing.add(betPoolLabel);
        setTable();
        rootPanel.revalidate();
        rootPanel.repaint();
    }

    public void reset()
    {
    	System.out.println("RESET");
        betPool = 0;
        playerHands.clear();
		playerHands.addLast(player);

        enemy.resetHand();
        player.resetHand();
        betPoolLabel.setText("BET POOL: " + betPool);
        betTable.add(betPoolLabel);
        rootPanel.remove(middleThing);
        rootPanel.add(betTable);
        rootPanel.revalidate();
        rootPanel.repaint();
        
    }
    
    
    public void setSplitButton(Boolean isEnabled) 
    {
    	splitButton.setEnabled(isEnabled);
    }
    
    private void updateHealth(Hand hand, JLabel hpLabel, String text)
    {
        hand.subHealth(betPool);
        hpLabel.setText(text + ": " + hand.getHealth());

    }

    public void updatePlayerPointLabel(int points)
    {
        playerPointLabel.setText("PLAYER: " + points);
    }

    public void updateEnemyPointLabel(int points)
    {
        enemyPointLabel.setText("ENEMY: " + points);
    }

    public void updateDebtLabel(int debtAmount)
    {
        debtLabel.setText("DEBT: " + debtAmount);
    }

    public void addBetPool(int amount)
    {
        betPool += amount;
        betPoolLabel.setText("BET POOL: " + betPool);

    }
}