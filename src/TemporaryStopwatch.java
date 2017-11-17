import java.util.Timer;
import java.util.TimerTask;

public class TemporaryStopwatch {

	static boolean timerIsTemporary = true;
	static int interval;
	static Timer timer;

	// sets a stopwatch to run for 5 seconds before a player's turn.
	public static void setMyStopwatch() {
		timerIsTemporary = true;
		interval = 5;
		
		// clear and update graphics for player name, round number, and points
		Logic.updateRoundGraphics();
		// set the delay and period of the timer to 1000 milliseconds (1 second)
		int delay = 1000;
		int period = 1000;
		timer = new Timer();	
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				setInterval();

			}
		}, delay, period);
	}

	// decrease the interval by 1, and call methods depending on the player
	private static final int setInterval() {
		
		if (interval <= -1) {
			timer.cancel();
			
			// update the char graphics for that player's round
			Logic.updateCharGraphics(true);
			
			// run the given round's stopwatch
			Stopwatch.setMyStopwatch();
		}
		// update the timer's graphic
		Panel.updateTimerGraphic();
		return --interval;
	}
	
	// get the time at any specific point of the temporary timer's life
	public static int getTime() {
		return interval;
	}
}