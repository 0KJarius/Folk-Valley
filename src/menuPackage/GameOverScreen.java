package menuPackage;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import utilites.EventBus;
import utilites.GlobalConstants;
import utilites.SwingUtils;

public class GameOverScreen extends JPanel
{
	private EventBus eventBus;
	
	public GameOverScreen() 
	{
		this.setBounds(new Rectangle(GlobalConstants.SCREEN_SIZE));
		this.setBackground(new Color(0, 0, 0, 200));
		
		JLayeredPane basePane = new JLayeredPane();
		basePane.setPreferredSize(GlobalConstants.SCREEN_SIZE);
		this.add(basePane);
		
		basePane.add(SwingUtils.createImageAt("MenuStuff/gameOverLayout.png", new Point(0, 0)), JLayeredPane.DEFAULT_LAYER);
	}
	
	private void onRetry() 
	{
		eventBus.hardResetMatch();
		eventBus.removeLayerFromFrame(this);
	}
}
