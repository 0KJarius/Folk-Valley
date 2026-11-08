package entitypackage.enemypackage;

import cutscenepackage.CutsceneLines;

public class Protosuer extends Enemy{
	public Protosuer() {
			super(
					300, 
					"Protosuer", 
					"Protosuer.png", 
					"METAL", 
					new CutsceneLines[] {
							new CutsceneLines("", "Hey", "normal"),
							new CutsceneLines("", "It's me", "normal"),
							new CutsceneLines("", "It's Very Tee", "angry")
					}, 
					"Protosuer", 
					"protosuer.png"
			);
	}
}
