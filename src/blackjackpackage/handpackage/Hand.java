package blackjackpackage.handpackage;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

import blackjackpackage.cardpackage.Card;
import blackjackpackage.cardpackage.PokerCard;
import blackjackpackage.cardpackage.PokerCardSpecial;
import blackjackpackage.cardpackage.UnoCard;
import utilities.AnimationUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Hand extends JPanel
{
    private ArrayList<Card> cards;
    private ArrayList<Card> selectedCards;
    private int score = 0;
    private static int debt = 0;
    private ArrayList<UnoCard> unoCards;

    public Hand()
    {
        cards = new ArrayList<Card>();
        selectedCards= new ArrayList<Card>();
        unoCards = new ArrayList<UnoCard>();
        this.setPreferredSize(new Dimension(804,130));
        this.setOpaque(false);
        this.repaint();
    }
    
    public void addNewCard(Card card) 
    {
    	cards.add(card);
        this.add(card);
        
        if (!(card instanceof UnoCard)) {
        	score += card.getCardScore();
        } else {
        	unoCards.add((UnoCard) card);
        }
        
        this.revalidate();
        this.repaint();
    }
    
    public void removeCard(Card cardToRemove) 
    {
    	if (!(cardToRemove instanceof UnoCard)) {
    		score -= cardToRemove.getCardScore();
    	} else {
    		unoCards.remove(cardToRemove);
    	}
    	
    	cards.remove(cardToRemove);
    	cardToRemove.removeThis();
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
        selectedCards.removeAll(selectedCards);
        cards.removeAll(cards);
        unoCards.removeAll(unoCards);
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
    
    public boolean verifySplit() {
    	if (cards.size() < 2 || cards.size() > 2) {return false;}
    	if (cards.get(0) instanceof UnoCard || cards.get(1) instanceof UnoCard) {return false;}
    	
    	return true;
    }
    
    public void applyUnoCards() {
    	if (unoCards.isEmpty()) {
    		return;
    	}
    	
    	for (UnoCard uno : unoCards) {
    		applySingleUnoCard(uno);
    	}
    }
    
    public void applySingleUnoCard(UnoCard uno) {
    	for (Card card : cards) 
		{
			if (card instanceof PokerCard && ((PokerCard) card).getSuit() == uno.getColor().getEquivalent() ||
				card instanceof UnoCard && uno != card && ((UnoCard)card).getColor() == uno.getColor() || card.getCardScore() == uno.getCardScore() && uno != card) {
				score += uno.getCardScore();
				break;
			}
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
