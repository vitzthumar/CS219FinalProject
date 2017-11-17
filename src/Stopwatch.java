import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch {

	static int interval;
	static Timer timer;
	
	// sets a non-temporary stopwatch to run the duration of the entered time
	public static void setMyStopwatch() {
		TemporaryStopwatch.timerIsTemporary = false;
		// set the delay and period of the timer to 1000 milliseconds (1 second)
		int delay = 1000;
		int period = 1000;
		timer = new Timer();	
		interval = Logic.myTime;
		timer.scheduleAtFixedRate(new TimerTask() {

			public void run() {
				try {
					setInterval();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}, delay, period);
	}

	// decrease the interval by 1, and call methods depending on which player's turn it is
	private static final int setInterval() throws IOException {

		if (interval <= -1) {
			timer.cancel();
			
			// call the logic function to change the player number and round #
			if (!Logic.updateNumberAndRound()) {

				// call the temporary stopwatch
				TemporaryStopwatch.setMyStopwatch();
			}
			
		}
		// update the timer's graphic
		Panel.updateTimerGraphic();
		return --interval;
	}
	
	// get the time at any specific point of the timer's life
	public static int getTime() {
		return interval;
	}
}
