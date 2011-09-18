package Stepper;

import java.util.*;

public class Stepper {
	Timer timer = new Timer();
	boolean b;
	
	public void addStep(Step s, long t)
	{
		timer = new Timer();
		b = true;
		timer.schedule(new StepTask(s), 0, t);
	}
	
	public void cancelStep()
	{
		b = false;
		timer.cancel();
	}

	public boolean isB() {
		return b;
	}

	public void setB(boolean b) {
		this.b = b;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	
	
}
