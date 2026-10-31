package itempackage;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import utilities.Signal;

public abstract class Item extends JLabel implements Signal{
	private ImageIcon itemImg;
	private String itemName;
	private String itemDescription;
	
	public Item(ImageIcon itemImg, String itemName, String itemDescription) {
		this.setPreferredSize(new Dimension(itemImg.getIconWidth(), itemImg.getIconHeight()));
		this.setSize(itemImg.getIconWidth(), itemImg.getIconHeight());
		this.setIcon(itemImg);
		this.itemImg = itemImg;
		this.itemName = itemName;
		this.itemDescription = itemDescription;
	}
	
	public ImageIcon getItemImg() {return itemImg;}
	public String getName() {return itemName;}
	public String getDescription() {return itemDescription;}
}
