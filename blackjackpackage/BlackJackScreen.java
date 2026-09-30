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

import entitypackage.Entity;
import entitypackage.Player;

public class BlackJackScreen extends JFrame
{
	private Player player;
	private Entity enemy;
    private Hand playerHand;
    private LinkedList<Hand> playerHands;
    private EnemyHand enemyHand;
    private JButton hitButton;
    private JButton standButton;
    private JButton splitButton;
    private JLabel playerMoneyLabel;
    private JLabel enemyMoneyLabel;
    private JLabel playerPointLabel;
    private JLabel enemyPointLabel;
    private JLabel debtLabel;
    private BetTable betTable;
    int betPool;
    private JLabel betPoolLabel;
    private JPanel middleThing;
    private JPanel rootPanel;
    private Deck deck;

    public BlackJackScreen(Deck playerDeck, Player player, Entity enemy)
    {
    	
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        rootPanel = new JPanel();
        this.setContentPane(rootPanel);
        rootPanel.setLayout(new BorderLayout());
        
        this.deck = playerDeck;
        this.player = player;
        this.enemy = enemy;
        playerHands = new LinkedList<Hand>();
        playerHands.addLast(new Hand(this));
        playerHand = playerHands.getFirst();
        enemyHand = new EnemyHand(this);
        playerMoneyLabel = new JLabel("PLAYER: " + player.getMoney());
        enemyMoneyLabel = new JLabel("ENEMY: " + enemy.getMoney());
        playerPointLabel = new JLabel("PLAYER P : " + playerHand.getCards());
        enemyPointLabel = new JLabel("ENEMY P : " + enemyHand.getCards());
        debtLabel = new JLabel("DEBT: " + playerHand.getDebt());
        betPoolLabel = new JLabel("BET POOL: " + betPool);
        hitButton = new JButton("HIT");
        standButton = new JButton("STAND");
        splitButton = new JButton("SPLIT");
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

    	rootPanel.add(enemyHand, BorderLayout.NORTH);

    	rootPanel.add(playerHand,  BorderLayout.SOUTH);

        middleThing = new JPanel();
        rootPanel.add(middleThing);
        middleThing.add(playerMoneyLabel);
        middleThing.add(enemyMoneyLabel);
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
        addCard(enemyHand);
        addCard(enemyHand);
        
        addCard(playerHand);
        addCard(playerHand);
        playerPointLabel.setText("PLAYER P : " + playerHand.getScore());
        enemyPointLabel.setText("ENEMY P : " + enemyHand.getScore());
    }
    
    public void onHitButtonPressed()
    {
    	addCard(playerHand);
        playerHand.revalidate();

        if (playerHand.getScore() == 21){
        	addMoney(player, playerMoneyLabel, betPool);
            splitCheck();
        } else if (playerHand.getScore() > 21){
        	addMoney(enemy, enemyMoneyLabel, betPool);
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
    	playerHand.applyDebt();
    	enemyHand.reveal();

        if (enemyHand.getScore() == 21){
        	addMoney(enemy, enemyMoneyLabel, betPool);
            
        } else if (enemyHand.getScore() > 21){
        	addMoney(player, playerMoneyLabel, betPool);
            
        } else if (enemyHand.getScore() > playerHand.getScore()){
        	addMoney(enemy, enemyMoneyLabel, betPool);
            
        } else if (enemyHand.getScore() < playerHand.getScore()){
        	addMoney(player, playerMoneyLabel, betPool);
            
        }
        splitCheck();
    }
    
    private void onSplitPressed() {
    	playerHands.addLast(new Hand(this));
    	playerHands.getLast().addNewCard(playerHands.getFirst().getCards().get(1));
    	playerHands.getFirst().removeCard(playerHands.getFirst().getCards().get(1));
    	playerHand.revalidate();
    }
    
    public void splitCheck() 
    {
    	System.out.println(playerHands.size());
    	if(playerHands.size() == 1) {
    		reset();
    	} else {
    		enemyHand.resetHand();
    		addCard(enemyHand);
            addCard(enemyHand);
    		
    		rootPanel.remove(playerHand);
    		playerHands.remove(playerHand);
    		playerHand = playerHands.getFirst();
    		rootPanel.add(playerHand, BorderLayout.SOUTH);
    		
    		playerPointLabel.setText("PLAYER P : " + playerHand.getScore());
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
		playerHands.addLast(playerHand);

        enemyHand.resetHand();
        playerHand.resetHand();
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
    
    private void subMoney(Entity entity, JLabel hpLabel, int amount)
    {
    	entity.subMoney(amount);
        hpLabel.setText(entity.getName() + ": " + entity.getMoney());
        
    }
    
    private void addMoney(Entity entity, JLabel hpLabel, int amount)
    {
    	entity.addMoney(amount);
        hpLabel.setText(entity.getName() + ": " + entity.getMoney());
        
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
        updatePlayerPointLabel(playerHand.getScore());
    }

    public void addBetPool(int amount)
    {
        betPool += (amount * 2);
        betPoolLabel.setText("BET POOL: " + betPool);
        subMoney(enemy, enemyMoneyLabel, amount);
        subMoney(player, playerMoneyLabel, amount);
    }
}