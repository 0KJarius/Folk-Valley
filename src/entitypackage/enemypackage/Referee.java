package entitypackage.enemypackage;

import cutscenepackage.CutsceneLines;

public class Referee extends Enemy{

	public Referee() {
		super(
				800, 
				"Referee",
				"referee.png",
				"What a nice and kind looking person!",
				new CutsceneLines[] {
						new CutsceneLines("", "WHAT ARE YOU DOINNNNGGGGGGG", "normal"),
						new CutsceneLines("", "GET OFF THE GRASSSSSSSSS", "point"),
						new CutsceneLines("", "RED CARD", "redCard"),
						new CutsceneLines("", "FOUL PLAY", "redCard"),
						new CutsceneLines("", "DEATH PENALTYYYYY", "normal")
				},
				new CutsceneLines[] {
						new CutsceneLines("", "...", "normal"),
				},
				"referee",
				"referee.png"
		);
	}

}
