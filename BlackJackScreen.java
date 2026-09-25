import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlackJackScreen extends JFrame
{
    private Hand player;
    private EnemyHand enemy;
    private JButton hitButton;
    private JButton standButton;
    private JLabel playerHpLabel;
    private JLabel enemyHpLabel;
    private JLabel playerPointLabel;
    private JLabel enemyPointLabel;
    private JLabel debtLabel;
    private BetTable betTable;
    int betPool;
    private JLabel betPoolLabel;
    private JPanel middleThing;

    public BlackJackScreen()
    {

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        player = new Hand(this);
        enemy = new EnemyHand(this);
        playerHpLabel = new JLabel("PLAYER: " + player.getHealth());
        enemyHpLabel = new JLabel("ENEMY: " + player.getHealth());
        playerPointLabel = new JLabel("PLAYER P : " + player.getCards());
        enemyPointLabel = new JLabel("ENEMY P : " + enemy.getCards());
        debtLabel = new JLabel("DEBT: " + player.getDebt());
        betPoolLabel = new JLabel("BET POOL: " + betPool);
        betTable = new BetTable(this);
        initializeTable();
        this.add(betTable);
        showBetTable();
        this.pack();
        this.setSize(new Dimension(1280, 720));
        this.setResizable(false);
    }

    public void initializeTable()
    {

        this.add(enemy, BorderLayout.NORTH);

        this.add(player,  BorderLayout.SOUTH);

        middleThing = new JPanel();
        this.add(middleThing);
        middleThing.add(playerHpLabel);
        middleThing.add(enemyHpLabel);
        middleThing.add(betPoolLabel);
        
        hitButton = new JButton("HIT");
        middleThing.add(hitButton);
        hitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onHitButtonPressed();
            }
            
        });
        standButton = new JButton("STAND");
        middleThing.add(standButton);
        standButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	onStandButtonPressed();
            }
            
        });
        middleThing.add(playerPointLabel);
        middleThing.add(enemyPointLabel);
        middleThing.add(debtLabel);

    }
    
    private void resetTable(){
        enemy.addNewCard();
        enemy.addNewCard();
        
        player.addNewCard();
        player.addNewCard();
        playerPointLabel.setText("PLAYER P : " + player.getScore());
        enemyPointLabel.setText("ENEMY P : " + enemy.getScore());
    }
    
    public void onHitButtonPressed()
    {
    	player.addNewCard();
        player.revalidate();

        if (player.getScore() == 21){
            updateHealth(enemy, enemyHpLabel, "ENEMY");
            

        } else if (player.getScore() > 21){
            updateHealth(player, playerHpLabel, "PLAYER");
            
        }
        showBetTable();
    }
    
    public void onStandButtonPressed()
    {
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
        showBetTable();
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

    public void hideBetTable()
    {
        this.remove(betTable);
        this.add(middleThing);
        middleThing.add(betPoolLabel);
        resetTable();
        System.out.println(betPool);
        this.revalidate();
        this.repaint();
    }

    public void showBetTable()
    {
        betPool = 0;

        enemy.resetHand();
        player.resetHand();
        betPoolLabel.setText("BET POOL: " + betPool);
        betTable.add(betPoolLabel);
        this.remove(middleThing);
        this.add(betTable);
        this.revalidate();
        this.repaint();
        
    }
}