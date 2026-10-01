import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	public MainFrame() {
		this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(1280, 720));
        this.setResizable(false);
	}
	
	public void setRootPanel(JPanel panel) 
	{
		this.setContentPane(panel);
		this.pack();
		this.repaint();
		this.revalidate();
	}
}
