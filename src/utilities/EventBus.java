package utilities;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import blackjackpackage.BlackJackScreen;
import entitypackage.Player;
import mainpackage.MainFrame;
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
	
	public static Signal changeToScreenOnSignal(JPanel screen) {
		Signal task = new Signal() {
			@Override
			public boolean doTask() {
				MainFrame.getInstance().setScreen(screen);
				return false;
			}
		};
		return task;
	}
}
