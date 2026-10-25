package shoppackage;


import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;


public class ShopTable extends JPanel
{
	public ShopTable()
	{
		this.setPreferredSize(new Dimension(300, 300));
		this.setLayout(new BorderLayout(10,10));
		this.setBackground(Color.BLACK);
		
		JPanel shopKeeperPanel = new JPanel();
		JPanel inventoryPanel = new JPanel();		
		JPanel dialoguePanel = new JPanel();		
		
		shopKeeperPanel.setBackground(Color.GRAY);
		inventoryPanel.setBackground(Color.BLUE);
		dialoguePanel.setBackground(Color.DARK_GRAY);

		shopKeeperPanel.setPreferredSize(new Dimension(300, 450));
		dialoguePanel.setPreferredSize(new Dimension(900, 130));
		
		this.add(shopKeeperPanel, BorderLayout.WEST);
		this.add(inventoryPanel, BorderLayout.CENTER);
		this.add(dialoguePanel, BorderLayout.SOUTH);
		
		
	}
}
