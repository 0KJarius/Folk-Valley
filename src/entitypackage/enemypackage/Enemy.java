package entitypackage.enemypackage;

import javax.swing.ImageIcon;

import cutscenepackage.CutsceneLines;
import entitypackage.Entity;
import utilities.SwingUtils;

public class Enemy extends Entity{
	protected static final String PORTRAIT_PATH = "battlePrompt/portrait/";
	protected static final String NAME_PLATE = "cutscene/namePlates/";
	protected static final String DIALOG_PORTRAIT = "cutscene/Portraits/";
	private String dialougePortraitPath;
	private String namePlate;
	private String description;
	private ImageIcon portrait;
	private CutsceneLines[] lines;
	
	public Enemy(int money, String name, String battlePromptPortrait, String description) {
		super(money, name);
		this.description = description;
		this.portrait = SwingUtils.getImage(PORTRAIT_PATH + battlePromptPortrait);
	}
	
	public Enemy(int money, String name, String battlePromptPortrait, String description, CutsceneLines[] lines, String dialougePortraitPath, String namePlate) {
		this(money, name, battlePromptPortrait, description);
		this.lines = lines;
		this.dialougePortraitPath = DIALOG_PORTRAIT + dialougePortraitPath + "/";
		this.namePlate = NAME_PLATE + namePlate;
	}
	

	public String getDescription() {
		return description;
	}

	public ImageIcon getPortrait() {
		return portrait;
	}

	public CutsceneLines[] getLines() {
		return lines;
	}

	public String getDialougePortraitPath() {
		return dialougePortraitPath;
	}

	public String getNamePlate() {
		return namePlate;
	}
}
