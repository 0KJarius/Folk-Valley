package blackjackpackage;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Hand extends JPanel
{
    private ArrayList<Card> cards;
    private ArrayList<Card> selectedCards;
    private int score = 0;
    private static int debt = 0;

    public Hand()
    {
        cards = new ArrayList<Card>();
        selectedCards= new ArrayList<Card>();
        this.setPreferredSize(new Dimension(804,130));
        this.setBackground(Color.BLACK);
        this.repaint();
    }
    
    public void addNewCard(Card card) 
    {
    	cards.add(card);
        this.add(card);
        score += card.getCardScore();
        
        card.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (card.isSelected()) {
                	selectedCards.add(card);
                } else {
                	selectedCards.remove(card);
                }
            }
            
        });
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
    
    public void removeSelectedCards() 
    {
    	for (Card selected : selectedCards) 
    	{
    		this.remove(selected);
    		cards.remove(selected);
    		score -= selected.getCardScore();
    		debt += selected.getCardScore();
    	}
    	selectedCards.clear();
    	this.repaint();
    	this.revalidate();
    }

    public void resetHand()
    {
        this.removeAll();
        cards.removeAll(cards);
        score = 0;
        debt = 0;
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
    }

    public ArrayList<Card> getCards() {return cards;}
    
    public ArrayList<Card> getSelectedCards() {return selectedCards;}

    public int getScore() {return score;}

    public int getDebt() {return debt;}

    public void setCards(ArrayList<Card> cards) {this.cards = cards;}

    public void setScore(int score) {this.score = score;}

    public void setDebt(int debt) {this.debt = debt;}
    
}
