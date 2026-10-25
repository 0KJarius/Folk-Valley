package mainpackage;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import blackjackpackage.BlackJackScreen;
import entitypackage.Entity;
import entitypackage.Player;
import menuPackage.GameOverScreen;
import menuPackage.startmenu.StartMenu;
import shoppackage.ShopTable;

public class Main
{

    public static void main(String[] args)
    {
    	Player player = new Player(100, "Alejandro");
    	
    	//test pubert for getResource
    	ImageIcon testPubert = new ImageIcon(Main.class.getResource("../Assets/kid-named-pubert.png"));
    	SwingUtilities.invokeLater(()->{
    		System.setProperty("awt.useSystemAAFontSettings","off"); 
            System.setProperty("swing.aatext", "false");
    		MainFrame frame = new MainFrame();
    		//frame.setScreen(new StartMenu());
    		//frame.setScreen(new ShopTable());
            frame.setScreen(new BlackJackScreen(new Entity(100, "Protosuer")));
    		frame.setScreen(new GameOverScreen());
            //frame.setScreen(new BlackJackScreen(new Entity(100, "Protosuer")));
    	});
        
    }
}