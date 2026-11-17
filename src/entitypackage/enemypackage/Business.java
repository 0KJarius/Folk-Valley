package entitypackage.enemypackage;

import cutscenepackage.CutsceneLines;

public class Business extends Enemy{
	public Business() {
		super(
				1000, 
				"Business Man",
				"business.png",
				"Corporate slave, very agitated. Trying to catch up to deadlines",
				new CutsceneLines[] {
						new CutsceneLines("", "Im going to be late to my BUSINESS!", "normal"),
						new CutsceneLines("", "Its very corporate and BUSY!", "normal"),
						new CutsceneLines("", "MOVE IT.", "angry"),
						new CutsceneLines("", "YOU'VE MADE ME LATE FOR MY WORK!", "angry"),
						new CutsceneLines("", "YOU DONT WANT TO SEE ME GOING OVERTIME.", "angry")
				},
				new CutsceneLines[] {
						new CutsceneLines("", "Ouhhhgg...", "normal"),
						new CutsceneLines("", "Im so not getting overtime pay...", "sad"),
						new CutsceneLines("", "Its time to clock out.", "sad"),
				},
				"business",
				"business.png"
		);
	}

}
