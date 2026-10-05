package menuPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import blackjackpackage.BlackJackScreen;
import utilites.GlobalConstants;
import utilites.MainFrame;
import utilites.SwingUtils;

public class GameOverScreen extends JPanel
{
	private BlackJackScreen blackJackHandler;
	private MainFrame mainFrame;
	
	public GameOverScreen(BlackJackScreen blackJackHandler, MainFrame mainFrame) 
	{
		this.setSize(GlobalConstants.SCREEN_SIZE);
		this.setLocation(0, 1);
		this.setBackground(new Color(0,0,255, 200));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.blackJackHandler = blackJackHandler;
		this.mainFrame = mainFrame;
		
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
		blackJackHandler.player.resetWallet();
		blackJackHandler.enemy.resetWallet();
		blackJackHandler.reset();
		mainFrame.removeLayer(this);
	}
}
