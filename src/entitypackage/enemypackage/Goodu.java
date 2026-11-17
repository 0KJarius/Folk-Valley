package entitypackage.enemypackage;

import cutscenepackage.CutsceneLines;

public class Goodu extends Enemy{
	public Goodu() {
		super(
				400, 
				"Sir Goodu", 
				"goodu.png", 
				"The game engine or someone else??", 
				new CutsceneLines[] {
						new CutsceneLines("", "Wow! Amazing! this Valley is FULL of so much nice FOLKS!", "happy"),
						new CutsceneLines("", "I'd give this place a PERFECT MARK!", "happy")
				},
				new CutsceneLines[] {
						new CutsceneLines("", "Thanks for playing!", "happy"),
						new CutsceneLines("", "But I cant seem to shake off that theres a chance of Reign... of the Monopoly man...", "normal"),
						new CutsceneLines("", "Dont you, 2?", "normal")
				}, 
				"goodu", 
				"goodu.png"
		);
	}

}
