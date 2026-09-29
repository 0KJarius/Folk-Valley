package blackjackpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyHand extends Hand{


    public EnemyHand(BlackJackScreen handler) {
        super(handler);
    }

    public void addNewCard(Card addedCard)
    {

        super.getCards().add(addedCard);
        this.add(addedCard);

        if (super.getCards().indexOf(addedCard) == 1){
            addedCard.hideCardScore();
        } else{
            super.setScore(super.getScore() + (addedCard.getCardScore() - super.getDebt()));
        }
        
        if (super.getDebt() > 0){
            super.setDebt(super.getDebt() - addedCard.getCardScore());
        }
        super.getBlackJackHandler().updatePlayerPointLabel(super.getScore());
    }
    

    public void reveal(){
        super.getCards().get(1).showCardScore();
        super.setScore(super.getScore() + (super.getCards().get(1).getCardScore() - super.getDebt()));
        super.getBlackJackHandler().updatePlayerPointLabel(super.getScore());
        if (super.getScore() <= 16){
            hitIt();
        }
    }
    
    public void hitIt(){
        super.getBlackJackHandler().addCard(this);
        if (super.getScore() <= 16){
            hitIt();
        }
    }
}
