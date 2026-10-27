package blackjackpackage.cardpackage;

import java.awt.Color;

import javax.swing.ImageIcon;

import utilities.GlobalConstants;
import utilities.SwingUtils;

public class UnoCard extends Card{
	public enum CardColor{
		RED(Color.decode("#FF362F")),
		BLUE(Color.decode("#52D0EA")),
		YELLOW(Color.decode("#FFD72F")),
		GREEN(Color.decode("#45D34C"));
		
		private Color color;
		private CardColor(Color color) {this.color = color;}
		
		public Color getColor() {return color;}
	}
	
	private final String UNO_PATH = "Cards/Uno Cards/";
	private String currentSkin = UNO_PATH + "placeHolder/";
	private final String FRAME_PATH = currentSkin + "frame.png";
	
	public UnoCard(CardColor color, int score) {
		ImageIcon cardImg = SwingUtils.getImage(FRAME_PATH);
		//cardSuit = suit;
		this.setBackground(color.getColor());
		super.setCardScore(score);
		this.setIcon(cardImg);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
		//this.setContentAreaFilled(false);
		
		//cardSelectImg = createSelectImage(cardImg);
		//this.setSelectedIcon(cardSelectImg);
		
		//flipSelectImg = createSelectImage(FLIP_IMG);
	}
}
