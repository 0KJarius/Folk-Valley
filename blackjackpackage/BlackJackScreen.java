package blackjackpackage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;
import entitypackage.Entity;
import entitypackage.Player;
import utilites.SwingUtils;

public class BlackJackScreen extends JPanel
{
	private JPanel matchInfoArea;
	private JPanel blackJackArea;
	private JPanel rightArea;
	
	private Player player;
	private Entity enemy;
    private Hand playerHand;
    private LinkedList<Hand> playerHands;
    private EnemyHand enemyHand;
    private JButton hitButton;
    private JButton standButton;
    private JButton splitButton;
    private JProgressBar playerMoneyProgressBar;
    private JProgressBar enemyMoneyProgressBar;
    private JLabel playerPointLabel;
    private JLabel enemyPointLabel;
    private JLabel debtLabel;
    private BetTable betTable;
    int betPool;
    private JLabel betPoolLabel;
    private Deck deck;
    public Font mainFont = new Font("DejaVu Sans", Font.PLAIN, 10);

    public BlackJackScreen(Deck playerDeck, Player player, Entity enemy)
    {
    	
        this.setLayout(new BorderLayout());
        
        this.deck = playerDeck;
        this.player = player;
        this.enemy = enemy;
        matchInfoArea = SwingUtils.createJPanel(new Dimension(238, 0), Color.YELLOW);
        blackJackArea = SwingUtils.createJPanel(new Dimension(804, 0), Color.CYAN);
        rightArea = SwingUtils.createJPanel(new Dimension(238, 0), Color.WHITE);
        playerHands = new LinkedList<Hand>();
        playerHands.addLast(new Hand(this));
        playerHand = playerHands.getFirst();
        enemyHand = new EnemyHand(this);
        playerPointLabel = SwingUtils.createJLabel(""+playerHand.getScore(), mainFont, 30, Color.BLUE);
        enemyPointLabel = SwingUtils.createJLabel(""+enemyHand.getScore(), mainFont, 30, Color.RED);
        
        playerMoneyProgressBar = SwingUtils.createJProgressBar(0, player.getMoney(), true,player.getName()+" "+player.getMoney(), player.getMoney(), Color.BLACK, Color.white, new Dimension(50, 480));
        enemyMoneyProgressBar = SwingUtils.createJProgressBar(0, enemy.getMoney(), true,enemy.getName()+" "+enemy.getMoney(), player.getMoney(), Color.BLACK, Color.white, new Dimension(50, 480));
        
        debtLabel = SwingUtils.createJLabel(""+playerHand.getDebt(), mainFont, 30, Color.RED, Color.BLACK);
        betPoolLabel = SwingUtils.createJLabel("V " + betPool, mainFont, 30, Color.GREEN, Color.BLACK);
        hitButton = new JButton("HIT");
        standButton = new JButton("STAND");
        splitButton = new JButton("SPLIT");
        
        hitButton.setFocusPainted(false);
        standButton.setFocusPainted(false);
        splitButton.setFocusPainted(false);
        
        splitButton.setEnabled(false);
        
        betTable = new BetTable(this);
        initTable(); 

        reset();
        this.setSize(new Dimension(1280, 720));
    }
    
    private void initTable() 
    {
    	this.add(matchInfoArea, BorderLayout.WEST);
    	setUpMatchInfoArea();
    	this.add(blackJackArea, BorderLayout.CENTER);
    	setUpBlackJackArea();
    	this.add(rightArea, BorderLayout.EAST);
    }
    
    private void setUpMatchInfoArea() {
    	matchInfoArea.setLayout(new BoxLayout(matchInfoArea, BoxLayout.Y_AXIS));
    	matchInfoArea.add(SwingUtils.createJLabel("BET POOL", getFont(), 40, Color.black));
    	matchInfoArea.add(betPoolLabel);
    	matchInfoArea.add(SwingUtils.createJLabel("DEBT", getFont(), 40, Color.black));
    	matchInfoArea.add(debtLabel);
    	
    	JPanel progressBarArea = SwingUtils.createJPanel(new Dimension(0, 1003), Color.MAGENTA);
    	progressBarArea.add(playerMoneyProgressBar);
    	progressBarArea.add(enemyMoneyProgressBar);
    	matchInfoArea.add(progressBarArea);
    }
    
    private void setUpBlackJackArea() {
    	blackJackArea.setBorder(new EmptyBorder(10,10,10,10));
    	blackJackArea.setLayout(new BoxLayout(blackJackArea, BoxLayout.Y_AXIS));
    	playerHand.setAlignmentX(CENTER_ALIGNMENT);
    	enemyHand.setAlignmentX(CENTER_ALIGNMENT);
    	blackJackArea.add(enemyHand);
    	
    	JPanel scorePanel = SwingUtils.createJPanel(new Dimension(0, 70), Color.DARK_GRAY);
    	scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
    	JLabel score = SwingUtils.createJLabel("SCORE", mainFont, 50, Color.black);
    	score.setAlignmentX(CENTER_ALIGNMENT);
    	scorePanel.add(score);
    	JPanel subScorePanel = SwingUtils.createJPanel(new Dimension(0, 0), Color.DARK_GRAY);
    	subScorePanel.setAlignmentX(CENTER_ALIGNMENT);
    	subScorePanel.add(playerPointLabel);
    	subScorePanel.add(SwingUtils.createJLabel(":", mainFont, 20, Color.black));
    	subScorePanel.add(enemyPointLabel);
    	scorePanel.add(subScorePanel);
    	blackJackArea.add(scorePanel);
    	
    	blackJackArea.add(playerHand);
    	
    	JPanel buttonPanel = SwingUtils.createJPanel(new Dimension(0, 0), Color.red);
    	buttonPanel.add(hitButton);
    	buttonPanel.add(standButton);
    	buttonPanel.add(splitButton);
    	hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onHitButtonPressed();
            }
            
        });
        

        standButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onStandButtonPressed();
            }
            
        });
        

        splitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		onSplitPressed();
            }
        });
    	
    	blackJackArea.add(buttonPanel);
    }
    
    private void setUpRightArea() {
    	
    }
    
    private void setTable(){
        addCard(enemyHand);
        addCard(enemyHand);
        
        addCard(playerHand);
        addCard(playerHand);
        playerPointLabel.setText("" + playerHand.getScore());
        enemyPointLabel.setText("" + enemyHand.getScore());
    }
    
    public void onHitButtonPressed()
    {
    	addCard(playerHand);
        playerHand.revalidate();

        if (playerHand.getScore() == 21){
        	addMoney(player, playerMoneyProgressBar, betPool);
            splitCheck();
        } else if (playerHand.getScore() > 21){
        	addMoney(enemy, playerMoneyProgressBar, betPool);
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
        	addMoney(enemy, enemyMoneyProgressBar, betPool);
            
        } else if (enemyHand.getScore() > 21){
        	addMoney(player, playerMoneyProgressBar, betPool);
            
        } else if (enemyHand.getScore() > playerHand.getScore()){
        	addMoney(enemy, enemyMoneyProgressBar, betPool);
            
        } else if (enemyHand.getScore() < playerHand.getScore()){
        	addMoney(player, playerMoneyProgressBar, betPool);
            
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
    		
            this.remove(playerHand);
    		playerHands.remove(playerHand);
    		playerHand = playerHands.getFirst();
    		this.add(playerHand, BorderLayout.SOUTH);
    		
    		playerPointLabel.setText("PLAYER P : " + playerHand.getScore());
    		this.repaint();
    		this.revalidate();
    		System.out.println("SIZE " + playerHands.size());
    	}
    	
    }
    

    public void hideBetTable()
    {
    	this.remove(betTable);
    	this.add(blackJackArea, BorderLayout.CENTER);
        setTable();
        this.revalidate();
        this.repaint();
    }

    public void reset()
    {
    	System.out.println("RESET");
        betPool = 0;
        playerHands.clear();
		playerHands.addLast(playerHand);

        enemyHand.resetHand();
        playerHand.resetHand();
        betPoolLabel.setText("" + betPool);
        this.add(betTable);
        this.remove(blackJackArea);
        this.revalidate();
        this.repaint();
        
    }
    
    
    public void setSplitButton(Boolean isEnabled) 
    {
    	splitButton.setEnabled(isEnabled);
    }
    
    private void subMoney(Entity entity, JProgressBar progressBar, int amount)
    {
    	entity.subMoney(amount);
        progressBar.setValue(entity.getMoney());
        progressBar.setString(entity.getName()+" "+entity.getMoney());
    }
    
    private void addMoney(Entity entity, JProgressBar progressBar, int amount)
    {
    	entity.addMoney(amount);
    	progressBar.setValue(entity.getMoney());
    	progressBar.setString(entity.getName()+" "+entity.getMoney());
    }
    

    public void updatePlayerPointLabel(int points)
    {
        playerPointLabel.setText("" + points);
    }

    public void updateEnemyPointLabel(int points)
    {
        enemyPointLabel.setText("" + points);
    }

    public void updateDebtLabel(int debtAmount)
    {
        debtLabel.setText("" + debtAmount);
        updatePlayerPointLabel(playerHand.getScore());
    }

    public void addBetPool(int amount)
    {
        betPool += (amount * 2);
        betPoolLabel.setText("" + betPool);
        subMoney(enemy, enemyMoneyProgressBar, amount);
        subMoney(player, playerMoneyProgressBar, amount);
    }
}