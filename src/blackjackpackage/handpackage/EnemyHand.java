package blackjackpackage.handpackage;

import java.util.Timer;
import java.util.TimerTask;

import blackjackpackage.cardpackage.Card;
import blackjackpackage.cardpackage.UnoCard;
import utilities.Signal;

public class EnemyHand extends Hand{
	private Signal task;
	private boolean isHitting = false;

    public EnemyHand(Signal task) {
        super();
        this.task = task;
    }
    
    @Override
    public void addNewCard(Card addedCard)
    {

        super.addNewCard(addedCard);

        if (super.getCards().indexOf(addedCard) == 1){
            addedCard.flipCard();
            if (!(addedCard instanceof UnoCard)) {super.setScore(super.getScore() - addedCard.getCardScore());}
        }
        
        if (isHitting && addedCard instanceof UnoCard) {
        	super.applySingleUnoCard((UnoCard)addedCard);
        }
    }
    

    public void reveal(){
        super.getCards().get(1).unflipCard();
        if (super.getCards().get(1) instanceof UnoCard) {
        	applyUnoCards();
        } else {
        	super.setScore(super.getScore() + (super.getCards().get(1).getCardScore() - super.getDebt()));
        }
        
        if (super.getScore() <= 16){
        	isHitting = true;
            task.doTask();
        }
    }
}
