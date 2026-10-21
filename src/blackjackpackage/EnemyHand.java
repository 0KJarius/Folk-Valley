package blackjackpackage;

import blackjackpackage.cardpackage.Card;

public class EnemyHand extends Hand{


    public EnemyHand() {
        super();
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
            hitIt();
        }
    }
    
    public void hitIt(){
    	System.out.println(super.getScore());
        addNewCard(new Card());
        if (super.getScore() <= 16){
            hitIt();
        }
    }
}
