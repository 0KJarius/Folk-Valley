package entitypackage.enemypackage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import cutscenepackage.CutsceneDialogue;
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
	private String battleTheme;
	private String dialogTheme;
	
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
	
	public Enemy(int money, String name, String battlePromptPortrait, String description, CutsceneLines[] lines, String dialougePortraitPath, String namePlate, String battleTheme, String dialogTheme) {
		this(money, name, battlePromptPortrait, description, lines, dialougePortraitPath, namePlate);
		this.battleTheme = battleTheme;
		this.dialogTheme = dialogTheme;
	}
	
	public CutsceneDialogue getEntryCutscene(JPanel nextScreen) {
		CutsceneDialogue dialog = new CutsceneDialogue(lines, dialougePortraitPath, namePlate, "placeValley.png", nextScreen, dialogTheme);
		return dialog;
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

	public String getBattleTheme() {
		return battleTheme;
	}
	
	
}
