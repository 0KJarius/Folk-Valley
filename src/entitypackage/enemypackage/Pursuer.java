package entitypackage.enemypackage;

import blackjackpackage.BlackJackScreen;
import blackjackpackage.custom_matchpackage.tutorial.OnoTutorialDeck;
import blackjackpackage.custom_matchpackage.tutorial.OnoTutorialMatch;
import blackjackpackage.custom_matchpackage.tutorial.TutorialMatch;
import cutscenepackage.CutsceneLines;

public class Pursuer extends Enemy{

	public Pursuer() {
		super(
				200, 
				"Pursuer", 
				"persuer.png", 
				"Your loving partner, but they won't go easy on you!", 
				new CutsceneLines[] {
						new CutsceneLines("", "Oh, good morning honey!", "normal"),
						new CutsceneLines("", "Up so early today I see.", "normal"),
						new CutsceneLines("", "What's for breakfast, you ask?", "normal"),
						new CutsceneLines("", "Why, FRESH MEAT, of course!", "normal"),
						new CutsceneLines("", "It's still cooking though... So you're gonna have to wait for a bit", "normal"),
						new CutsceneLines("", "Say! Why don't we play some good old Folk Valley style Blackjack while we wait.", "normal"),
						new CutsceneLines("", "Just like the old times!", "normal"),
						new CutsceneLines("", "What?", "normal"),
						new CutsceneLines("", "You forgot?", "normal"),
						new CutsceneLines("", "Oh dear, honey did you hit your head when waking?", "normal"),
						new CutsceneLines("", "Well... in any case, I'll just have to give you a refresher!", "normal")
				},
				new CutsceneLines[] {
						new CutsceneLines("", "Hey", "normal"),
						new CutsceneLines("", "It's me", "normal"),
						new CutsceneLines("", "It's Very Tee", "normal")
				}, 
				"placeHolder", 
				"pursuer.png"
		);
	}

	@Override
	public BlackJackScreen getMatch() {
		return new TutorialMatch();
	}
	
	@Override
	public BlackJackScreen getMatch(int natural) {
		return new TutorialMatch(natural);
	}

}
