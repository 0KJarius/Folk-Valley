package mainpackage;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import utilities.EventBus;
import utilities.GlobalConstants;

public class MainFrame extends JFrame{
	public static final MainFrame FRAME = new MainFrame();
	
	private JPanel currentPanel;
	JLayeredPane baseLayer;
	
	private MainFrame() {
		this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        
        baseLayer = new JLayeredPane();
        baseLayer.setPreferredSize(GlobalConstants.SCREEN_SIZE);
        currentPanel = new JPanel();
        this.add(baseLayer, BorderLayout.CENTER);
        this.pack();
	}
	
	public static MainFrame getInstance() {
		return FRAME;
	}
	
	public void setScreen(JPanel panel) 
	{
		if (currentPanel != null) {
			baseLayer.remove(currentPanel);
		}
		
		currentPanel = panel;
		baseLayer.add(currentPanel, JLayeredPane.DEFAULT_LAYER);
		this.pack();
		baseLayer.repaint();
		baseLayer.revalidate();
		
	}
	
	public void layerOnScreen(JPanel panel, Integer layer) 
	{
		baseLayer.add(panel, layer);
		baseLayer.repaint();
		baseLayer.revalidate();
	}
	
	public void removeLayer(JPanel panel) {
		baseLayer.remove(panel);
		baseLayer.repaint();
		baseLayer.revalidate();
	}
}
