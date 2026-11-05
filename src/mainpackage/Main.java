package mainpackage;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import blackjackpackage.BlackJackScreen;
import blackjackpackage.custom_matchpackage.tutorial.TutorialMatch;
import entitypackage.Entity;
import entitypackage.Player;
import menuPackage.ViewDeckScreen;
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
    		MainFrame frame = MainFrame.getInstance();
    		frame.setScreen(new StartMenu());
            //frame.setScreen(new ViewDeckScreen());
    	});
        
    }
}