
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

import blackjackpackage.BlackJackScreen;
import entitypackage.Entity;
import entitypackage.Player;
import menuPackage.GameOverScreen;
import utilites.EventBus;
import utilites.MainFrame;

public class Main
{

    public static void main(String[] args)
    {
    	Player player = new Player(100, "Alejandro");
    	
    	SwingUtilities.invokeLater(()->{
    		MainFrame frame = new MainFrame();
            BlackJackScreen blackjack = new BlackJackScreen(player.playerDeck.cloneDeck(), player, new Entity(100, "Protosuer"));
            frame.setScreen(blackjack);
    	});
        
    }
}