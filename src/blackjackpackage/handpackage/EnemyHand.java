package blackjackpackage.handpackage;


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

        if (getCards().indexOf(addedCard) == 1){
            addedCard.flipCard();
            if (!(addedCard instanceof UnoCard)) {setScore(getScore() - addedCard.getCardScore());}
        }
        
        if (isHitting && addedCard instanceof UnoCard) {
        	applySingleUnoCard((UnoCard)addedCard);
        }
    }
    

    public void reveal(){
        getCards().get(1).unflipCard();
        if (getCards().get(1) instanceof UnoCard) {
        	applyUnoCards();
        } else {
        	setScore(getScore() + (getCards().get(1).getCardScore() - getDebt()));
        }
        
        if (getScore() <= 16){
        	isHitting = true;
            task.doTask();
        }
    }
}
