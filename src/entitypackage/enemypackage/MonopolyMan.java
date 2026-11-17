package entitypackage.enemypackage;

import cutscenepackage.CutsceneLines;

public class MonopolyMan extends Enemy{
	public MonopolyMan() {
		super(
				2500, 
				"Monopolee Man",
				"monopolee.png",
				"It's him... John Monopolee... The magical manifestation of all your problems. Surely, beating them will bring peace !!!!",
				new CutsceneLines[] {
						new CutsceneLines("", "Whats this?", "normal"),
						new CutsceneLines("", "You're here to stop me?", "normal"),
						new CutsceneLines("", "Dont make me laugh, peasant.", "angry"),
						new CutsceneLines("", "You think you can beat me at my own game of cash?", "angry"),
						new CutsceneLines("", "How might you achieve that, when you cant possibly hope to match my BAG", "angry")
				},
				new CutsceneLines[] {
						new CutsceneLines("", "WHATTT", "normal"),
						new CutsceneLines("", "IMPOSSIBLE", "sad"),
						new CutsceneLines("", "I HAD ALL THE MONEY", "sad"),
						new CutsceneLines("", "ALL THE STOCKS", "sad"),
						new CutsceneLines("", "ALL THE PUBERTS", "sad"),
						new CutsceneLines("", "HOW COULD YOU POSSIBLY HAVE BEAT ME", "sad"),
						new CutsceneLines("", "YOU'RE JUST SOME FOLK FROM A VALLEY.", "sad"),
						new CutsceneLines("", "NOOOOOOOOOOOOOOOOOOOO", "sad")
				},
				"monopolee",
				"monopoly.png"
		);
	}
}
