package entitypackage.enemypackage;

import blackjackpackage.BlackJackScreen;
import blackjackpackage.custom_matchpackage.tutorial.OnoTutorialDeck;
import blackjackpackage.custom_matchpackage.tutorial.OnoTutorialMatch;
import cutscenepackage.CutsceneLines;

public class NumeroUno extends Enemy{

	public NumeroUno() {
		super(
				200, 
				"Numer O. Uno", 
				"NumeroUno.png", 
				"Shop keeper of that one shop that isn't owned by the Monopoly Man.\n The \"O\" Stands for \"Owesome\" or so he says..", 
				new CutsceneLines[] {
						new CutsceneLines("", "Oh hi!", "normal"),
						new CutsceneLines("", "Who might you be?", "question"),
						new CutsceneLines("", "Well I'm Numer O. Uno !!", "normal"),
						new CutsceneLines("", "I'm the shop keep of that shop over there.", "normal"),
						new CutsceneLines("", "Hmmmmm....", "question"),
						new CutsceneLines("", "My mind is telling me to battle you for plot convenience reasons.", "question"),
						new CutsceneLines("", "Oh well!", "normal"),
						new CutsceneLines("", "Get ready!", "mog"),
						new CutsceneLines("", "For a figh- OH whoops is slipped !", "slip"),
						new CutsceneLines("", "Oh no! I'm so sorry. I accidentally dropped all my Ono cards onto your deck!!", "slip"),
						new CutsceneLines("", "What a terrible turn of events", "normal"),
						new CutsceneLines("", "Well.. I guess we have no choice now. I'll have to teach you..", "question"),
						new CutsceneLines("", "The Owesome tech frfr !!!", "mog"),
				},
				new CutsceneLines[] {
						new CutsceneLines("", "Hey, that was really fun!", "normal"),
						new CutsceneLines("", "We should play again soon !!", "normal"),
						new CutsceneLines("", "Anyways, you can keep the cards that I dropped.", "normal"),
						new CutsceneLines("", "It was my fault anyways, also I have more at home", "question"),
						new CutsceneLines("", "If you need more cards or items you can buy some at my store!", "normal"),
						new CutsceneLines("", "Store is only one time use though for some game mechanic reason.", "question"),
						new CutsceneLines("", "So make sure you get everything you need in one visit!", "normal"),
						new CutsceneLines("", "Don't worry too much though, if you ever pass by another branch you can buy there too!", "normal"),
						new CutsceneLines("", "In any case, you should get going now. See around!", "normal"),
				}, 
				"numero", 
				"numeroUno.png"
		);
	}

	@Override
	public BlackJackScreen getMatch() {
		return new OnoTutorialMatch(new OnoTutorialDeck());
	}

	@Override
	public BlackJackScreen getMatch(int natural) {
		return new OnoTutorialMatch(natural, new OnoTutorialDeck());
	}
	
	
}
