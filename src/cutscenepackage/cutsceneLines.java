package cutscenepackage;

public class cutsceneLines {
	private String characterName;
	private String dialogue;
	private String expression;
	public cutsceneLines(String characterName, String dialogue, String expression) {
		super();
		this.characterName = characterName;
		this.dialogue = dialogue;
		this.expression = expression;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public String getDialogue() {
		return dialogue;
	}
	public void setDialogue(String dialogue) {
		this.dialogue = dialogue;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}



}
