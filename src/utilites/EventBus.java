package utilites;

import javax.swing.JPanel;
import blackjackpackage.BlackJackScreen;
import entitypackage.Entity;
import entitypackage.Player;
import mainpackage.MainFrame;

public abstract class EventBus {
	public static Player player;
	public static Entity enemy;
	public static BlackJackScreen currentMatch;
	public static MainFrame frame;
	
	public void hardResetMatch() 
	{
		player.resetWallet();
		enemy.resetWallet();
		//currentMatch.reset();
	}
	
	public void removeLayerFromFrame(JPanel panel) 
	{
		frame.removeLayer(panel);	
	}
	
}
