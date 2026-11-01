package blackjackpackage.handpackage;
import java.awt.Dimension;
import javax.swing.JPanel;
import blackjackpackage.cardpackage.Card;
import blackjackpackage.cardpackage.PokerCard;
import blackjackpackage.cardpackage.UnoCard;
import java.util.ArrayList;

public abstract class Hand extends JPanel
{
    private ArrayList<Card> cards;
    private ArrayList<Card> selectedCards;
    private int score = 0;
    private int debt = 0;
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
        card.unflipCard();
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
    	
    	cardToRemove.setSelected(false);
    	cards.remove(cardToRemove);
    	cardToRemove.removeThis();
        this.remove(cardToRemove);
    }
    
    public void removeSelectedCards() 
    {
    	for (Card selected : selectedCards) 
    	{	
    		removeCard(selected);
    		debt += selected.getCardScore();
    	}
    	selectedCards.clear();
    	this.repaint();
    	this.revalidate();
    }

    public void resetHand()
    {
        this.removeAll();
        for (Card card : cards)
        	card.setSelected(false);
        selectedCards.clear();
        cards.clear();
        unoCards.clear();
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
    	if (cards.get(0).getCardScore() == cards.get(1).getCardScore()){return true;}
    	
    	return false;
    }
    
    public void applyUnoCards() {
    	if (unoCards.isEmpty()) {
    		return;
    	}
    	
    	for (UnoCard uno : unoCards) {
    		applySingleUnoCard(uno);
    	}
    }
    
    public boolean deleteUnoCards() {
    	if (unoCards.isEmpty()) {
    		return false;
    	}
    	
    	for (int i = 0; i < unoCards.size(); i++) {
    		cards.remove(unoCards.get(i));
    		unoCards.get(i).removeThis();
    		this.remove(unoCards.get(i));
    		unoCards.remove(unoCards.get(i));
    	}
    	
    	repaint();
    	revalidate();
    	return true;
    }
    
    public boolean deleteUnoCards(UnoCard.CardColor color) {
    	if (unoCards.isEmpty()) {
    		return false;
    	}
    	
    	
    	int deletedCards = 0;   	
    	for (int i = 0; i < unoCards.size(); i++) {
    		if (unoCards.get(i).getColor() == color) {
    			cards.remove(unoCards.get(i));
    			unoCards.get(i).removeThis();
    			this.remove(unoCards.get(i));
    			unoCards.remove(unoCards.get(i));
    			deletedCards++;
    		}
    	}
    	repaint();
    	revalidate();
    	if (deletedCards == 0) {
    		return true;
    	} else {
    		return false;
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
    
    public ArrayList<UnoCard> getUnoCards() {return unoCards;}
    
    public ArrayList<Card> getSelectedCards() {return selectedCards;}

    public int getScore() {return score;}

    public int getDebt() {return debt;}

    public void setCards(ArrayList<Card> cards) {this.cards = cards;}

    public void setScore(int score) {this.score = score;}

    public void setDebt(int debt) {this.debt = debt;}
    
    
}
