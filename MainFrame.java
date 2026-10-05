import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	JPanel currentPanel;
	
	public MainFrame() {
		this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(1280, 720));
        this.setResizable(false);
        
        currentPanel = new JPanel();
	}
	
	public void setScreen(JPanel panel) 
	{
		if (currentPanel != null) {
			this.remove(currentPanel);
		}
		
		currentPanel = panel;
		this.add(currentPanel);
		this.pack();
		this.repaint();
		this.revalidate();
	}
}
