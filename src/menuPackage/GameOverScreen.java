package menuPackage;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import blackjackpackage.BlackJackScreen;
import entitypackage.Entity;
import menuPackage.startmenu.StartMenu;
import utilites.EventBus;
import utilites.GlobalConstants;
import utilites.Signal;
import utilites.SwingUtils;

public class GameOverScreen extends JPanel
{
	private JPanel statsArea = SwingUtils.createJPanel(new Rectangle(), getBackground());
	private JPanel gameOverArea ;
	
	public GameOverScreen() 
	{
		this.setBounds(new Rectangle(GlobalConstants.SCREEN_SIZE));
		this.setBackground(new Color(0, 0, 0, 200));
		
		JLayeredPane basePane = new JLayeredPane();
		basePane.setPreferredSize(GlobalConstants.SCREEN_SIZE);
		this.add(basePane);
		
		Signal retryTask = new Signal() {
			@Override
			public void doTask() {
				EventBus.frame.setScreen(new BlackJackScreen(new Entity(100, "Protosuer")));
				
			}
		};
		
		basePane.add(SwingUtils.createImageAt("MenuStuff/gameOverLayout.png", new Point(0, 0)), JLayeredPane.DEFAULT_LAYER);
		basePane.add(new JarButton(new Point(100,100), false, "RETRY", 60, retryTask), JLayeredPane.PALETTE_LAYER);
	}
}
