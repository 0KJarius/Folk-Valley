package blackjackpackage.handpackage;

import java.util.Timer;
import java.util.TimerTask;

import blackjackpackage.cardpackage.Card;
import utilities.Signal;

public class EnemyHand extends Hand{
	private Signal task;

    public EnemyHand(Signal task) {
        super();
        this.task = task;
    }
    
    @Override
    public void addNewCard(Card addedCard)
    {

        super.getCards().add(addedCard);
        this.add(addedCard);

        if (super.getCards().indexOf(addedCard) == 1){
            addedCard.flipCard();
        } else{
            super.setScore(super.getScore() + (addedCard.getCardScore() - super.getDebt()));
        }
        
        if (super.getDebt() > 0){
            super.setDebt(super.getDebt() - addedCard.getCardScore());
        }
    }
    

    public void reveal(){
        super.getCards().get(1).unflipCard();
        super.setScore(super.getScore() + (super.getCards().get(1).getCardScore() - super.getDebt()));
        if (super.getScore() <= 16){
            task.doTask();
        }
    }
    
    public void hitIt(){
        if (super.getScore() <= 16){
            hitIt();
            
        }
    }
}
