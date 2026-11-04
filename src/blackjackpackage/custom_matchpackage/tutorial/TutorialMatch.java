package blackjackpackage.custom_matchpackage.tutorial;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLayeredPane;

import blackjackpackage.BetTable;
import blackjackpackage.BlackJackScreen;
import entitypackage.Entity;
import mainpackage.MainFrame;
import menuPackage.TutorialPrompt;
import utilities.Signal;

public class TutorialMatch extends BlackJackScreen{
	private int roundCount = 1;
	private TutorialPrompt prompt = new TutorialPrompt();
	private TutorialMatch self = this;
	BetTable betTable = new BetTable() {

		@Override
		public void removeNotify() {
			super.removeNotify();
			
			if (roundCount == 1) {
				MainFrame.getInstance().layerOnScreen(prompt, JLayeredPane.DRAG_LAYER);
				prompt.setBlockers(653, 14, 277, 242);
				prompt.activeBlock.setOpaque(false);
				prompt.setTutorialText("These are the Blackjack buttons, they control the flow of the game.", new Point(265, 400));
				prompt.setTask(hitPrompt);
			}
			
			this.setVisible(false);
			this.repaint();
			this.revalidate();
		}
	};

	
	
	public TutorialMatch(Entity enemy) {
		super(enemy, new TutorialDeck());
		setBetTable(betTable);
		
	}
	
	public TutorialMatch(Entity enemy, int natural) {
		super(enemy, natural, new TutorialDeck());
	}


	@Override
	public void checkResults() {
		roundCount ++;
		super.checkResults();
	}
	
	private Signal betTablePrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setBlockers(0, 0, 255, 210);
			prompt.activeBlock.setOpaque(false);
			prompt.setTutorialText("Every round starts by making a bet on the Bet Table.");
			prompt.setTask(betLabelPrompt);
			return false;
		}
		
	};
	
	private Signal betLabelPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setBlockers(0, 620, 0, 1026);
			prompt.setTutorialText("The bet is reflected in the Bet Pool.");
			prompt.setTask(hpPrompt);
			return false;
		}
	};
	
	private Signal hpPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setBlockers(201, 0, 0, 1026);
			prompt.setTutorialText("The bet will be taken out of your Wallets. \n(bets have to be matched by the opponent)");
			prompt.setTask(naturalPrompt);
			return false;
		}
	};
	
	private Signal naturalPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setBlockers(0, 420, 1072, 0);
			prompt.setTutorialText("The Natural dictates what score is considered a \"Blackjack\" and how high a score can get before a Bust.");
			prompt.setTask(cardCountPrompt);
			return false;
		}
	};
	
	private Signal cardCountPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setBlockers(310, 349, 1072, 0);
			prompt.setTutorialText("This is the Card Counter. If this hits 0, you lose no matter what.");
			prompt.setTask(terminalPrompt);
			return false;
		}
	};
	
	private Signal terminalPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setBlockers(374, 10, 1072, 0);
			prompt.setTutorialText(
					"This is the Item Terminal. Here, you can switch through your items and use them. "
					+ "If you forgot how a certain item works, click the yellow \"?\" button.");
			prompt.setTask(finalIntroduction);
			return false;
		}
	};
	
	private Signal finalIntroduction = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setBlockers(0, 0, 0, 0);
			prompt.activeBlock.setOpaque(true);
			prompt.setTutorialText("Now, let's see how a round is actually played! Start by placing a bet.");
			prompt.setTask(terminalPrompt);
			prompt.setTask(removePrompt);
			return false;
		}
	};
	
	private Signal hitPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setBlockers(653, 14, 290, 885);
			prompt.setTutorialText("The HIT button allows you to add cards to your hand, thereby increasing the score.");
			prompt.setTask(hitTwoPrompt);
			return false;
		}
	};
	
	private Signal hitTwoPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("HIT wisely. Too much and you Bust over the Natural. However, there is a chance to instantly win if your score qualifies for Blackjack");
			prompt.setTask(standPrompt);
			return false;
		}
	};
	
	private Signal standPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setBlockers(653, 14, 395, 690);
			prompt.setTutorialText("The STAND button essentially ends your turn. When you stand, your score is locked in and the Dealer, your oppent, gets to HIT.");
			prompt.setTask(discardPrompt);
			return false;
		}
	};
	
	private Signal discardPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setBlockers(653, 14, 774, 242);
			prompt.setTutorialText("The DISCARD button allows you to discard selected cards from your hand. When you discard you gain DEBT, and the total scores of the cards you discarded are subtracted from your score.");
			prompt.setTask(splitPrompt);
			return false;
		}
	};
	
	private Signal splitPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setBlockers(653, 14, 595, 500);
			prompt.setTutorialText("The SPLIT button is special. You're only able to press it when there are 2 cards in your hand, and if those cards share the SAME score.");
			prompt.setTask(splitTwoPrompt);
			return false;
		}
	};
	
	private Signal splitTwoPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("When you split, it creates 2 hands, giving you 2 chances to win! However, if you lose, both hands are lost.");
			prompt.setTask(splitThreePrompt);
			return false;
		}
	};
	
	private Signal splitThreePrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("Additionally, if you have any DEBT, it gets halved, and if there's any remainder, it gets added to The Natural!");
			prompt.setTask(scoreAreaPrompt);
			return false;
		}
	};
	
	private Signal scoreAreaPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setBlockers(229, 308, 289, 262);
			prompt.setTutorialText("This is the Score Area, where score is reflected. Everytime you or the Dealer HITs, its refelected here", new Point(265, 500));
			prompt.setTask(scoreAreaTwoPrompt);
			return false;
		}
	};
	
	private Signal scoreAreaTwoPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("Scores are compared after STANDing. If your score is lower than the Dealer, you lose, otherwise, you win! On Ties, no money is taken and the round is restart.");
			prompt.setTask(scoreAreaThreePrompt);
			return false;
		}
	};
	
	private Signal scoreAreaThreePrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setBlockers(104, 525, 0, 1026);
			prompt.setTutorialText("Additionally, when you STAND, your current DEBT is subtracted from your score.", new Point(265, 100));
			prompt.setTask(debtPrompt);
			return false;
		}
	};
	
	private Signal debtPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setTutorialText("When that happens, your DEBT is also \"paid off\", subracting your score from your debt");
			prompt.setTask(bustPrompt);
			return false;
		}
	};
	
	private Signal bustPrompt = new Signal() {
		@Override
		public boolean doTask() {
			prompt.setBlockers(0, 0, 0, 0);
			prompt.activeBlock.setOpaque(true);
			prompt.setTutorialText("That's all there is to it. Now, let's play!", new Point(265, 500));
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
		prompt.setTutorialText("I'll show you the ropes of a Folk Valley Blackjack game!", new Dimension(800, 200), new Point(265, 500));
		prompt.setTask(betTablePrompt);
	}

	@Override
	public void removeNotify() {
		super.removeNotify();
	}
	
	
	
}
