
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import blackjackpackage.BlackJackScreen;
import entitypackage.Entity;
import entitypackage.Player;

public class Main
{

    public static void main(String[] args)
    {
    	Player player = new Player(100, "Alejandro");
        BlackJackScreen blackjack = new BlackJackScreen(player.playerDeck.cloneDeck(), player, new Entity(100, "Protosuer"));
        MainFrame frame = new MainFrame();
        
        
        frame.setScreen(blackjack);
    }
    


}