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
    private static int debt = 0;
    private int health = 100;

    public Hand(BlackJackScreen handler)
    {
        blackJackHandler = handler;
        health = 100;
        cards = new ArrayList<Card>();
        this.setPreferredSize(new Dimension(600,200));
        this.setBackground(Color.BLACK);
        this.repaint();
    }
    
    public void addNewCard(Card card) 
    {
    	cards.add(card);
        this.add(card);
        score += card.getCardScore() - debt;
        
        if (debt > 0){
            debt -= card.getCardScore();
        }
        
        if(cards.size() == 2 && cards.get(0).getCardScore() == cards.get(1).getCardScore()) {
        	blackJackHandler.setSplitButton(true);
        } else {
        	blackJackHandler.setSplitButton(false);
        }

        card.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cards.size() == 1){
                    return;
                }
                removeCard(card);
                debt += card.getCardScore();
                score -= debt;
                
                blackJackHandler.updateDebtLabel(debt);
            }
            
        });
        blackJackHandler.updatePlayerPointLabel(score);
        this.revalidate();
        this.repaint();
    }
    
    //will probably be removed once deck system is added
    public void addNewCard()
    {
        Card addedCard = new Card();

        cards.add(addedCard);
        this.add(addedCard);
        score += addedCard.getCardScore();
        
        if(cards.size() == 2 && cards.get(0).getCardScore() == cards.get(1).getCardScore()) {
        	blackJackHandler.setSplitButton(true);
        } else {
        	blackJackHandler.setSplitButton(false);
        }

        addedCard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cards.size() == 1){
                    return;
                }
                removeCard(addedCard);
                debt += addedCard.getCardScore();
                
                blackJackHandler.updateDebtLabel(debt);
            }
            
        });
        blackJackHandler.updatePlayerPointLabel(score);
        this.revalidate();
        this.repaint();
    }
    
    public void removeCard(Card cardToRemove) 
    {
    	score -= cardToRemove.getCardScore();
    	cards.remove(cardToRemove);
    	cardToRemove.hideCard();
        this.remove(cardToRemove);
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
    
    public void applyDebt() 
    {
    	score -= debt;
    	debt -= score;
    	if (debt < 0) {
    		debt = 0;
    	}
    	blackJackHandler.updatePlayerPointLabel(score);
    	blackJackHandler.updateDebtLabel(debt);
    }

    public ArrayList<Card> getCards() {return cards;}

    public int getScore() {return score;}

    public int getDebt() {return debt;}

    public void setCards(ArrayList<Card> cards) {this.cards = cards;}

    public void setScore(int score) {this.score = score;}

    public void setDebt(int debt) {this.debt = debt;}

    public int getHealth() {return health;}

    public void setHealth(int health) {this.health = health;}

    public void subHealth(int amount){health -= amount;}

    public BlackJackScreen getBlackJackHandler() {return blackJackHandler;}
    
}
