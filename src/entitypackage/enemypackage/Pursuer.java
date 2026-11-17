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
						new CutsceneLines("", "Wakey, Wakey!", "normal"),
						new CutsceneLines("", "Oh Great! I thought you were PURSUED and DIED in your sleep! I got anxious, if you couldnt tell", "sad"),
						new CutsceneLines("", "I hope you're hungry for some FRESH MEAT for breakfast!", "normal"),
						new CutsceneLines("", "or else.", "angry"),
						new CutsceneLines("", "But first! Its never a bad Idea to start the day with our very own game in this FOLK VALLEY.", "normal"),
						new CutsceneLines("", "Hm?", "normal"),
						new CutsceneLines("", "Dont Tell me you forgot...", "sad"),
						new CutsceneLines("", "After all those times, you always won against me.", "angry"),
						new CutsceneLines("", "I guess we cannot help it, allow me to reFRESH your recollection", "normal")
				},
				new CutsceneLines[] {
						new CutsceneLines("", "GAHH", "angry"),
						new CutsceneLines("", "Beat me again, are you sure you werent just joking when you said you forgot?", "sad"),
						new CutsceneLines("", "anyways, all this LOSING has be hungry for some FRESH MEAT.", "normal"),
						new CutsceneLines("", "Its time to PURSUE the day ahead of us.", "normal")
				}, 
				"Pursuer", 
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
