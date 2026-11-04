package shoppackage;


import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;

import utilities.GlobalConstants;
import utilities.SwingUtils;
import java.awt.Dimension;
//import java.awt.BorderLayout;
//import javax.swing.BorderFactory;
//import javax.swing.JButton;

// valley size Dimension(1280, 720));
//frame.setScreen(new ShopTable());
public class ShopTable extends JPanel
{
	public ShopTable()
	{
		this.setBackground(Color.GRAY);
		this.setBounds(0, 0, 1280, 720);
		this.setLayout(null);
		
		JPanel sellPanel = SwingUtils.createJPanel(new Dimension(740, 720), Color.black);
		sellPanel.setBounds(540, 0, 740, 720);
		add(sellPanel);
		
	    JLabel storeTitle = SwingUtils.createJLabel("SHAP!!!", 32, Color.GREEN, GlobalConstants.VCR_MONO);
	    storeTitle.setBounds(50, 50, 450, 50);
	    add(storeTitle);
	    
		JLabel title = new JLabel("Shop");
		JLabel name = new JLabel("Shempai");
		
	}
}
