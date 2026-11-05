package entitypackage;

import javax.swing.ImageIcon;

import blackjackpackage.BlackJackScreen;
import blackjackpackage.custom_matchpackage.tutorial.TutorialMatch;
import utilities.SwingUtils;

public class Enemy extends Entity{
	private final String PORTRAIT_PATH = "battlePrompt/portrait/";
	private String description;
	private ImageIcon portrait;
	
	public Enemy(int money, String name, String portraitPath, String description) {
		super(money, name);
		this.description = description;
		this.portrait = SwingUtils.getImage(PORTRAIT_PATH + portraitPath);
	}
	
	

	public String getDescription() {
		return description;
	}

	public ImageIcon getPortrait() {
		return portrait;
	}


	
}
