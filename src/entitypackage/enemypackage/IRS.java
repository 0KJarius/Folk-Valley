package entitypackage.enemypackage;

import cutscenepackage.CutsceneLines;

public class IRS extends Enemy{
	public IRS() {
		super(
				1500, 
				"IRS", 
				"irs.png", 
				"Have you paid your taxes yet?", 
				new CutsceneLines[] {
						new CutsceneLines("", "HEY, YOU", "normal"),
						new CutsceneLines("", "DID YOU PAY FOR THAT MONEY?", "angry"),
						new CutsceneLines("", "DONT RUN AWAY FROM ME, YOU'RE GETTING AUDITTED!", "angry")
				},
				new CutsceneLines[] {
						new CutsceneLines("", "It would seem...", "defeat"),
						new CutsceneLines("", "That your tax returns are... acceptable...", "defeat"),
						new CutsceneLines("", "Lets have it your way...", "normal")
				}, 
				"irs", 
				"irs.png"
		);
	}
}
