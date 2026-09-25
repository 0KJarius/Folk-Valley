import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Hand extends Panel
{
    private BlackJackScreen blackJackHandler;
    private ArrayList<Card> cards;
    private int score = 0;
    private int debt = 0;
    private Hand thisHand;
    private int health = 100;

    public Hand(BlackJackScreen handler)
    {
        blackJackHandler = handler;
        health = 100;
        thisHand = this;
        cards = new ArrayList<Card>();
        this.setPreferredSize(new Dimension(600,200));
        this.setBackground(Color.BLACK);
        
    }

    public void addNewCard()
    {
        Card addedCard = new Card();

        cards.add(addedCard);
        this.add(addedCard);
        score += addedCard.getCardScore() - debt;
        if (debt > 0){
            debt -= addedCard.getCardScore();
        }

        addedCard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cards.size() == 1){
                    return;
                }
                cards.remove(addedCard);
                addedCard.hideCard();
                thisHand.remove(addedCard);
                debt += addedCard.getCardScore();
                score -= debt;
                
                blackJackHandler.updateDebtLabel(debt);
            }
            
        });
        blackJackHandler.updatePlayerPointLabel(score);

    }

    public void resetHand()
    {
        this.removeAll();
        cards.removeAll(cards);
        score = 0;
        debt = 0;
        blackJackHandler.updatePlayerPointLabel(score);
        blackJackHandler.updateDebtLabel(debt);
        this.revalidate();
        this.repaint();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }

    public int getDebt() {
        return debt;
    }

    public Hand getThisHand() {
        return thisHand;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public void setThisHand(Hand thisHand) {
        this.thisHand = thisHand;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void subHealth(int amount){
        System.out.println(amount);
        health -= amount;
    }

    public BlackJackScreen getBlackJackHandler() {
        return blackJackHandler;
    }
    
}
