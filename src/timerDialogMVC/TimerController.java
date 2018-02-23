package timerDialogMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import digitalClockMVC.ClockController.ClockListener;

/**
 * Coordinates the actions between the TimerView and TimerModel. 
 * A Timer is used to generate an action every second. Every second 
 * the TimerController asks the TimerModel to update the time and 
 * then will send the updated time to the timerDisplay in the 
 * TimerView. 
 * 
 * @author Checo Ponce De Leon
 */
public class TimerController {
	
	private TimerView timerView;
	private TimerModel timerModel;
	Timer timer;
	
	/**
	 * The constructor starts a timer to drive the interactions
	 * between the ClockView and the ClockModel.
	 * 
	 * @param timerView
	 * @param timerModel
	 */
	public TimerController (TimerView timerView, TimerModel timerModel)  {
		this.timerView = timerView;
		this.timerModel = timerModel;
		
		// Start the Timer; timer will send an action every second, and
		// implement an object of ClockListener
		timer = new Timer (1000, new ClockListener() );
		timer.start();
		
		/* Tell the TimerView that whenever either the startButton or 
		 * the stopButton is clicked to execute the startPerformed and 
		 * stopPerformed method in the TimerListener inner class.
		 */
		
		ActionListener listenForStart = null;
		ActionListener listenForStop = null;
		
		this.timerView.addStartButtonListener(listenForStart);
		this.timerView.addStopButtonListener(listenForStop);
		
		this.timerView.addStartButtonListener(new StartButtonListener() );
		this.timerView.addStopButtonListener(new StopButtonListener() );
		
		TimerView.getStartButton().addActionListener(event -> 
		TimerView.getTimeDisplay().setText ("StartButton") );
		TimerView.getStopButton().addActionListener(event -> 
		TimerView.getTimeDisplay().setText ("StopButton") );
	}
	
	/** */
	class StartButtonListener implements ActionListener  {
		
		/** */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == TimerView.getStartButton() ) {
			
				long startTime = System.currentTimeMillis();
				long currentTime = 0;
				
				// Surround interactions with the view with a try-
				// block in case of incorrect input
				try  {
					// timerView.setTimeInDisplay("00:00:00");
					timerView.setTimeInDisplay("startListenerWorks");
					
					//  TODO Need to write a method that will increment
					//  the timerDisplay.
				}
				catch (Exception ex)  {
					System.out.println(ex);
				}
			}
		}
	}
	
	/** */
	class StopButtonListener implements ActionListener  {
		
		/** */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == TimerView.getStopButton()) {
				// Surround interactions with the view with a try-
				// block in case of incorrect input
				try {
					// TODO Need to stop the incrementing method
					// and show the last time.
					timerView.setTimeInDisplay("stopListenerWorks");
					System.out.println("the stop listener works");
				} catch (Exception ex) {
					System.out.println();
				} 
			}
		}
	}
	
	/**
	 * This method responds to Timer events.
	 * 
	 * @author Sergio Ponce De Leon
	 */
	class ClockListener implements ActionListener  {
		/* 
		 * Upon construction, ask theModel to updateTime and theView to 
		 * updateDisplay: */
		ClockListener ()  {
			timerModel.updateTime();
			timerView.updateDisplay (timerModel.getHour(), 
								   timerModel.getMinute(),
								   timerModel.getSecond() );
		}
		
		/*
		 * Every Timer Event asks timerModel to updateTime and theView to 
		 * updateDisplay.
		 */
		@Override
		public void actionPerformed (ActionEvent e)  {
			timerModel.updateTime ();
			timerView.updateDisplay (timerModel.getHour(),
								   timerModel.getMinute(),
								   timerModel.getSecond() );
			System.out.println(timerModel.getHour() + ":" +
							   timerModel.getMinute() + ":" +
							   timerModel.getSecond() );
		}
	}

}
