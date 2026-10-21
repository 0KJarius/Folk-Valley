package mainpackage;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import utilites.EventBus;
import utilites.GlobalConstants;

public class MainFrame extends JFrame{
	private JPanel currentPanel;
	JLayeredPane baseLayer;
	
	public MainFrame() {
		EventBus.frame = this;
		this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(1280, 720));
        this.setResizable(false);
        this.setLayout(null);
        
        baseLayer = new JLayeredPane();
        baseLayer.setSize(GlobalConstants.SCREEN_SIZE);
        currentPanel = new JPanel();
        this.add(baseLayer);
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
