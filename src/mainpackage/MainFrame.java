package mainpackage;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import savepackage.SaveManager;
import utilities.EventBus;
import utilities.GlobalConstants;
import utilities.Signal;

public class MainFrame extends JFrame{
	public static final MainFrame FRAME = new MainFrame();
	
	private JPanel currentPanel;
	JLayeredPane baseLayer;
	
	private MainFrame() {
		this.setTitle("Folk Valley 2: Reign Of The Monopoly Man");
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
        
        this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				SaveManager.saveGame(EventBus.player);
				super.windowClosing(e);
			}
        	
        	
        });
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
	
	public void layerOnScreen(JComponent component, Integer layer) 
	{
		baseLayer.add(component, layer);
		baseLayer.repaint();
		baseLayer.revalidate();
	}
	
	public void removeLayer(JComponent component) {
		baseLayer.remove(component);
		baseLayer.repaint();
		baseLayer.revalidate();
	}
	
	public void removeLayer(JComponent component, Signal removeTask) {
		removeLayer(component);
		removeTask.doTask();
	}
}
