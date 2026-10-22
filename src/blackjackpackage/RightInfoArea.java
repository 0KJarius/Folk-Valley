package blackjackpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import utilites.SwingUtils;

public class RightInfoArea extends JPanel{
	public RightInfoArea() 
	{
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(210, 720));
		this.setOpaque(false);
		JLayeredPane basePane = new JLayeredPane();
		basePane.setPreferredSize(new Dimension(210, 720));
		this.add(basePane);
		
		JLabel bgGif = SwingUtils.createImageAt("BlackJack/rightSide.gif", new Point(0, 0));	
		basePane.add(bgGif, JLayeredPane.DEFAULT_LAYER);
	}
}
