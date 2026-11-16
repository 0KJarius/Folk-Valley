package entitypackage.enemypackage;

import blackjackpackage.BlackJackScreen;
import blackjackpackage.custom_matchpackage.tutorial.OnoTutorialDeck;
import blackjackpackage.custom_matchpackage.tutorial.OnoTutorialMatch;
import cutscenepackage.CutsceneDialogue;
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
