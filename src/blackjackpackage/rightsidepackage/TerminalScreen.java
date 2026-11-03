package blackjackpackage.rightsidepackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import utilities.EventBus;
import utilities.Signal;
import utilities.SwingUtils;

public class TerminalScreen extends JPanel{
	private JPanel currentScreen;
	private JLabel staticTransition = new JLabel(SwingUtils.getImage("BlackJack/terminal/terminalStatic.png"));
	
	public TerminalScreen() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(201, 150));
		this.setBackground(Color.magenta);
		this.setOpaque(false);
	}
	
	public void setTerminalScreen(JPanel screen) {
		TerminalScreen self = this;
		if (currentScreen != null) {
			this.remove(currentScreen);
		}
		this.add(staticTransition);
		
		EventBus.executeAfterTime(150, new Signal() {
			@Override
			public boolean doTask() {
				self.remove(staticTransition);
				self.add(screen);
				currentScreen = screen;
				self.repaint();
				self.revalidate();
				return true;
			}
		});
		
	}
	
}
