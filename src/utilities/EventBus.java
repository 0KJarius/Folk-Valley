package utilities;

import java.util.Timer;
import java.util.TimerTask;

import blackjackpackage.BlackJackScreen;
import entitypackage.Entity;
import entitypackage.Player;
import mainpackage.MainFrame;

public abstract class EventBus {
	public static Player player;
	public static BlackJackScreen currentMatch;
	

	
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
