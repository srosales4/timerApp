package edu.utep.cs.cs4330.timer;

import java.util.Calendar;

/**
 * Stores all the data needed for the timer and performs any calculations
 * needed.
 * 
 * @author Checo Ponce De Leon
 */
public class TimerModel {
	/** */
	protected boolean isRunning = false;
	
	/** */
	private long startTime;
	
	/** */
//	TimeClass tc = new TimeClass ();
	
	/** */
	public void start()  {
		
		isRunning = true;
		
		startTime = System.currentTimeMillis();
		
		//  DEBUGGING SYSO
		System.out.println("start clicked");
	}
	
	/** */
	public void stop()  {
		
		isRunning = false;
		
		//  *****  Stop the timer somehow  ******
		
		
		//  Cheon's code
		startTime = elapsedTime ();
		
		//  DEBUGGING SYSO
		System.out.println("stop clicked");
	}
	
	/** */
	public boolean isRunning ()  {
		return isRunning;
	}
	
	/** */
	public long elapsedTime ()  {
		return System.currentTimeMillis() - startTime;
	}
}
