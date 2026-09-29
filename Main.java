import blackjackpackage.BlackJackScreen;

public class Main
{

    public static void main(String[] args)
    {
    	Player player = new Player();
        BlackJackScreen blackjack = new BlackJackScreen(player.playerDeck.cloneDeck());
    }


}