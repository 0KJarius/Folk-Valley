package blackjackpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import utilities.EventBus;
import utilities.SwingUtils;

public class RightInfoArea extends JPanel{
	private JLabel naturalLabel;
	
	public RightInfoArea() 
	{
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(210, 720));
		this.setOpaque(false);
		JLayeredPane basePane = new JLayeredPane();
		basePane.setPreferredSize(new Dimension(210, 720));
		this.add(basePane);
		
		JLabel bgGif = SwingUtils.createImageAt("BlackJack/rightSide.gif", new Point(0, 0));
		JPanel naturalPanel = SwingUtils.createJPanel(new Rectangle(5, 5, 199, 297), Color.BLUE); 
		setUpNaturalPanel(naturalPanel);
		basePane.add(bgGif, JLayeredPane.DEFAULT_LAYER);
		basePane.add(naturalPanel, JLayeredPane.PALETTE_LAYER);
	}
	
	private void setUpNaturalPanel(JPanel panel) 
	{
		panel.setOpaque(false);
		naturalLabel = SwingUtils.createJLabel("" + EventBus.currentMatch.natural, 200, new Color(255, 214, 55), SwingUtils.M6XPLUS);
		naturalLabel.setBorder(BorderFactory.createEmptyBorder(60, 20, 0, 0));
		
		panel.add(naturalLabel);
	}
	
	public void updateNaturalLabel() {
		naturalLabel.setText("" + EventBus.currentMatch.natural);
	}
}
