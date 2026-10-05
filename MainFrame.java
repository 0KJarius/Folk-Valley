import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	JPanel currentPanel;
	JLayeredPane baseLayer;
	
	public MainFrame() {
		this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(1280, 720));
        this.setResizable(false);
        
        baseLayer = new JLayeredPane();
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
	
	public void layerOnScreen(JPanel panel, int layer) 
	{
		baseLayer.add(panel, layer);
		
	}
	
	public void removeLayer(JPanel panel) {
		baseLayer.remove(panel);
	}
}
