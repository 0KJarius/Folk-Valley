package menuPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import utilites.EventBus;
import utilites.GlobalConstants;
import utilites.SwingUtils;

public class GameOverScreen extends JPanel
{
	private EventBus eventBus;
	
	public GameOverScreen() 
	{
		this.setSize(GlobalConstants.SCREEN_SIZE);
		this.setLocation(0, 1);
		this.setBackground(new Color(0,0,255, 200));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel text = SwingUtils.createJLabel("GAME OVER", GlobalConstants.MAINFONT, 50, Color.RED);
		text.setAlignmentY(CENTER_ALIGNMENT);
		text.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton retryButton = SwingUtils.createJButton(new Dimension(300, 60), "RETRY", Color.CYAN);
		retryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onRetry();
			}
			
		});
		this.add(text);
		this.add(retryButton);
	}
	
	private void onRetry() 
	{
		eventBus.hardResetMatch();
		eventBus.removeLayerFromFrame(this);
	}
}
