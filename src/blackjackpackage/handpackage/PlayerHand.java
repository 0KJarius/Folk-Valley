package blackjackpackage.handpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import blackjackpackage.cardpackage.Card;
import blackjackpackage.cardpackage.UnoCard;

public class PlayerHand extends Hand{
	public PlayerHand(){
		super();
	}
	
	@Override
	public void addNewCard(Card card) 
    {
    	super.addNewCard(card);
    	
        card.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (card.isSelected()) {
                	getSelectedCards().add(card);
                } else {
                	getSelectedCards().remove(card);
                }
            }
            
        });
        this.revalidate();
        this.repaint();
    }
}
