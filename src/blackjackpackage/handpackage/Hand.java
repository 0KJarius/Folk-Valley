package blackjackpackage.handpackage;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import blackjackpackage.cardpackage.Card;
import blackjackpackage.cardpackage.PokerCard;
import blackjackpackage.cardpackage.UnoCard;
import utilities.SwingUtils;

import java.util.ArrayList;

public abstract class Hand extends JPanel
{
    private ArrayList<Card> cards;
    private ArrayList<Card> selectedCards;
    private int score = 0;
    private int debt = 0;
    private ArrayList<UnoCard> unoCards;
    private Dimension cardPanelSize = new Dimension(804, 130);
    private JPanel cardPanel = SwingUtils.createJPanel(cardPanelSize, getBackground());
    private JScrollPane scroller = new JScrollPane(cardPanel);
    

    public Hand()
    {
        cards = new ArrayList<Card>();
        selectedCards= new ArrayList<Card>();
        unoCards = new ArrayList<UnoCard>();
        this.setPreferredSize(new Dimension(804,250));
        scroller.setPreferredSize(new Dimension(804,250));
        scroller.getHorizontalScrollBar().setUnitIncrement(18);
        
        cardPanel.setOpaque(false);
        scroller.setColumnHeaderView(scroller.getHorizontalScrollBar());
        scroller.setOpaque(false);
        scroller.getViewport().setOpaque(false);
        scroller.setBorder(null);
        
        scroller.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
        	@Override 
            protected void configureScrollBarColors(){
        		this.trackColor = new Color(215, 109, 46);
        		this.trackHighlightColor = new Color(192, 92, 0);
        		
        		
                this.thumbColor = new Color(253, 200, 53);
                this.thumbHighlightColor = new Color(255, 250, 77);
                this.trackHighlightColor = new Color(255, 250, 77);
            }
        	
        	@Override
        	protected JButton createDecreaseButton(int orientation) {
        		JButton arrowButton = super.createDecreaseButton(orientation);
        		arrowButton.setBackground(new Color(253, 200, 53));
        		arrowButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        		return arrowButton;
        	}
        	
        	@Override
        	protected JButton createIncreaseButton(int orientation) {
        		JButton arrowButton = super.createDecreaseButton(orientation);
        		arrowButton.setBackground(new Color(253, 200, 53));
        		arrowButton.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        		return arrowButton;
        	}
        });
        
        
        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        layout.setHgap(0);
        this.setOpaque(false);
        this.repaint();
        this.setLayout(layout);
        
        this.add(scroller);
    }
    
    public void addNewCard(Card card) 
    {
    	cards.add(card);
    	cardPanel.add(card);
        
        if (!(card instanceof UnoCard)) {
        	score += card.getCardScore();
        } else {
        	unoCards.add((UnoCard) card);
        }
        card.unflipCard();
        if (cards.size() > 4) {
        	cardPanel.setPreferredSize(new Dimension(cardPanel.getPreferredSize().width + 150,130));
        }
        
        cardPanel.revalidate();
        cardPanel.repaint();
    }
    
    public void removeCard(Card cardToRemove) 
    {
    	if (!(cardToRemove instanceof UnoCard)) {
    		score -= cardToRemove.getCardScore();
    	} else {
    		unoCards.remove(cardToRemove);
    	}
    	
    	if (cards.size() > 4) {
    		cardPanel.setPreferredSize(new Dimension(cardPanel.getPreferredSize().width - 150,130));
    	}
    	
    	cardToRemove.setSelected(false);
    	cards.remove(cardToRemove);
    	cardToRemove.removeThis();
    	cardPanel.remove(cardToRemove);
    	
    	cardPanel.repaint();
    	cardPanel.revalidate();
    }
    
    public void removeSelectedCards() 
    {
    	for (Card selected : selectedCards) 
    	{	
    		removeCard(selected);
    		debt += selected.getCardScore();
    	}
    	selectedCards.clear();
    	cardPanel.repaint();
    	cardPanel.revalidate();
    }

    public void resetHand()
    {
    	cardPanel.removeAll();
    	cardPanel.setPreferredSize(cardPanelSize);
        for (Card card : cards)
        	card.setSelected(false);
        selectedCards.clear();
        cards.clear();
        unoCards.clear();
        score = 0;
        debt = 0;
        cardPanel.revalidate();
        cardPanel.repaint();
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
    
    public void reverseCards() {
    	for (Card card : cards) {
    		if (card.isCardFlipped()) {
    			card.unflipCard();
    		} else {
    			card.flipCard();
    		}
    	}
    }
    
    public boolean deleteUnoCards() {
    	if (unoCards.isEmpty()) {
    		return false;
    	}
    	
    	for (int i = 0; i < unoCards.size(); i++) {
    		cards.remove(unoCards.get(i));
    		unoCards.get(i).removeThis();
    		cardPanel.remove(unoCards.get(i));
    		unoCards.remove(unoCards.get(i));
    	}
    	
    	cardPanel.repaint();
    	cardPanel.revalidate();
    	return true;
    }
    
    public boolean deleteUnoCards(UnoCard.CardColor color) {
    	if (unoCards.isEmpty()) {
    		return false;
    	}
    	
    	ArrayList<UnoCard> toDelete = new ArrayList<UnoCard>();	
    	for (int i = 0; i < unoCards.size(); i++) {
    		if (unoCards.get(i).getColor() == color) {
    			toDelete.add(unoCards.get(i));
    		}
    	}
    	System.out.println(toDelete.size());
    	for (int i = 0; i < toDelete.size(); i++) {
    		cards.remove(toDelete.get(i));
    		toDelete.get(i).removeThis();
    		cardPanel.remove(toDelete.get(i));
    		unoCards.remove(toDelete.get(i));
    	}
    	
    	
    	cardPanel.repaint();
    	cardPanel.revalidate();
    	if (toDelete.size() == 0) {
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
