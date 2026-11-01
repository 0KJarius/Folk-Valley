package utilities;

import java.util.Timer;
import java.util.TimerTask;

import blackjackpackage.BlackJackScreen;
import entitypackage.Player;
import stageselectpackage.Stage;

public abstract class EventBus {
	public static Player player;
	public static BlackJackScreen currentMatch;
	public static Stage selectedStage;
	
	public static void hardResetMatch() 
	{
		player.resetWallet();
		currentMatch.resetDeck();
		currentMatch.reset();
		currentMatch.hardResetStats();
	}
	
	public static void executeAfterTime(int miliSeconds, Signal task) {
		Timer waitTimer = new Timer();
		TimerTask timeTask = new TimerTask() {
			@Override
			public void run() {
				waitTimer.cancel();
				task.doTask();
			}
		};
		
		waitTimer.scheduleAtFixedRate(timeTask, miliSeconds, 1);
	}
}
