package blackjackpackage.custom_matchpackage.tutorial;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLayeredPane;

import blackjackpackage.BlackJackScreen;
import blackjackpackage.Deck;
import entitypackage.Entity;
import entitypackage.enemypackage.EnemyCollection;
import mainpackage.MainFrame;
import menuPackage.TutorialPrompt;
import utilities.Signal;

public class OnoTutorialMatch extends BlackJackScreen{
	private TutorialPrompt prompt = new TutorialPrompt();
	
	public OnoTutorialMatch(Deck deck) {
		super(EnemyCollection.NUMEROUNO.getEnemy(), deck);
		setBetTable(betTable);
		
	}
	
	public OnoTutorialMatch(int natural, Deck deck) {
		super(EnemyCollection.NUMEROUNO.getEnemy(), natural, deck);
	}
	
	private Signal onoPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("In any case, I guess now's a good time to show you how Ono cards work in Blackjack!");
			prompt.setTask(onoPromptTwo);
			return false;
		}
	};
	
	private Signal onoPromptTwo = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("Ono cards can't be discarded, split, and they don't directly add to your score when added to your hand.");
			prompt.setTask(onoPromptThree);
			return false;
		}
	};
	
	private Signal onoPromptThree = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("Ono cards are only scored when you stand. Additionally, the criteria for them to be counted are the following: ");
			prompt.setTask(onoPromptFour);
			return false;
		}
	};
	
	private Signal onoPromptFour = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("a)There is another card of the same color. Doesn't matter if it's a Poker or Ono card, as long as it's the same color.");
			prompt.setTask(onoPromptFive);
			return false;
		}
	};
	
	private Signal onoPromptFive = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("b)There is another card of the same score.");
			prompt.setTask(onoPromptSix);
			return false;
		}
	};
	
	private Signal onoPromptSix = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("The Ono cards being added after standing intorduces an interesting situation");
			prompt.setTask(onoPromptSeven);
			return false;
		}
	};
	
	private Signal onoPromptSeven = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("If both players scores are over The Natural, or both players bust. All Blackjack rules are thrown out the window!");
			prompt.setTask(onoPromptEight);
			return false;
		}
	};
	
	private Signal onoPromptEight = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("When this happens, highest score wins!");
			prompt.setTask(onoPromptLast);
			return false;
		}
	};
	
	private Signal onoPromptLast = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("Anyways, that's all I've got to say! All that explaining was soooooooo boring");
			prompt.setTask(onoPromptLastLast);
			return false;
		}
	};
	
	private Signal onoPromptLastLast = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("So let's play !!!!!!!!!!!!!");
			prompt.setTask(removePrompt);
			return false;
		}
	};
	
	private Signal removePrompt = new Signal() {
		@Override
		public boolean doTask() {
			MainFrame.getInstance().removeLayer(prompt);
			return false;
		}
	};
	
	
	
	@Override
	public void addNotify() {
		super.addNotify();
		MainFrame.getInstance().layerOnScreen(prompt, JLayeredPane.DRAG_LAYER);
		prompt.setBlockers(0, 0, 0, 0);
		prompt.activeBlock.setOpaque(true);
		prompt.setTutorialText("Oops! Sorry, I got some Ono cards mixed up in your deck!", new Dimension(800, 200), new Point(265, 500));
		prompt.setTask(onoPrompt);
	}

	@Override
	public void removeNotify() {
		super.removeNotify();
	}
}
