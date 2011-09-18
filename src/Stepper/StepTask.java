package Stepper;

import java.util.*;

public class StepTask extends TimerTask {
	Step stepped;
	
	public StepTask(Step a)
	{
		stepped = a;
	}
	
	public void run()
	{
		stepped.step();
	}
}
