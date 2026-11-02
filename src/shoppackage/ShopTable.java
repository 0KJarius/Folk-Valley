package shoppackage;



import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import utilities.SwingUtils;
import javax.swing.*;
import stageselectpackage.*;
import mainpackage.*;




public class ShopTable extends JPanel
{
	public ShopTable()
	{
		this.setPreferredSize(new Dimension(1080, 720));
		this.setBounds(0,0, 1280, 720);
		this.setLayout(new BorderLayout(10,10));
		this.setBackground(Color.BLACK);
		
		JPanel shopKeeperPanel = SwingUtils.createJPanel(new Dimension(300, 450), Color.GRAY);
		
		ShopInventory inventoryPanel = new ShopInventory();	
		
		JPanel dialoguePanel =	SwingUtils.createJPanel(new Dimension(900, 130), Color.DARK_GRAY);		
		
		
		this.add(shopKeeperPanel, BorderLayout.WEST);
		this.add(inventoryPanel, BorderLayout.CENTER);
		this.add(dialoguePanel, BorderLayout.SOUTH);
		
		JButton returnButton = SwingUtils.createJButton(new Dimension(100, 40), "Back", Color.PINK);
		
		returnButton.addActionListener(e -> {
			MainFrame.getInstance().setScreen(StageSelectScreen.getInstance());
		});
		
		dialoguePanel.setLayout(new BorderLayout());
		dialoguePanel.add(returnButton, BorderLayout.EAST);
		
		
		
	}
}
