package entitypackage.enemypackage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import blackjackpackage.BlackJackScreen;
import cutscenepackage.CutsceneDialogue;
import cutscenepackage.CutsceneLines;
import entitypackage.Entity;
import stageselectpackage.StageSelectScreen;
import utilities.EventBus;
import utilities.SwingUtils;

public abstract class Enemy extends Entity{
	protected static final String PORTRAIT_PATH = "battlePrompt/portrait/";
	protected static final String NAME_PLATE = "cutscene/namePlates/";
	protected static final String DIALOG_PORTRAIT = "cutscene/Portraits/";
	private String dialougePortraitPath;
	private String namePlate;
	private String description;
	private ImageIcon portrait;
	private CutsceneLines[] startLines;
	private CutsceneLines[] endLines;
	private String battleTheme;
	private String dialogTheme;
	
	public Enemy(int money, String name, String battlePromptPortrait, String description) {
		super(money, name);
		this.description = description;
		this.portrait = SwingUtils.getImage(PORTRAIT_PATH + battlePromptPortrait);
	}
	
	public Enemy(int money, String name, String battlePromptPortrait, String description, CutsceneLines[] startLines, CutsceneLines[] endLines, String dialougePortraitPath, String namePlate) {
		this(money, name, battlePromptPortrait, description);
		this.startLines = startLines;
		this.endLines = endLines;
		this.dialougePortraitPath = DIALOG_PORTRAIT + dialougePortraitPath + "/";
		this.namePlate = NAME_PLATE + namePlate;
	}
	
	
	public Enemy(int money, String name, String battlePromptPortrait, String description, CutsceneLines[] startLines, CutsceneLines[] endLines, String dialougePortraitPath, String namePlate, String battleTheme, String dialogTheme) {
		this(money, name, battlePromptPortrait, description, startLines, endLines, dialougePortraitPath, namePlate);
		this.battleTheme = battleTheme;
		this.dialogTheme = dialogTheme;
	}
	
	public  BlackJackScreen getMatch() {
		return new BlackJackScreen(this, EventBus.player.playerDeck);
	}
	
	public  BlackJackScreen getMatch(int natural) {
		return new BlackJackScreen(this, natural, EventBus.player.playerDeck);
	}
	
	public CutsceneDialogue getEntryCutscene(JPanel nextScreen) {
		CutsceneDialogue dialog = new CutsceneDialogue(startLines, dialougePortraitPath, namePlate, "placeValley.png", nextScreen, dialogTheme);
		System.out.println(dialogTheme);
		return dialog;
	}
	
	public CutsceneDialogue getEndCutscene() {
		CutsceneDialogue dialog = new CutsceneDialogue(endLines, dialougePortraitPath, namePlate, "placeValley.png", StageSelectScreen.getInstance(), dialogTheme);
		return dialog;
	}
	

	public String getDescription() {
		return description;
	}

	public ImageIcon getPortrait() {
		return portrait;
	}

	public CutsceneLines[] getLines() {
		return startLines;
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
