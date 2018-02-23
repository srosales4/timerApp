package timerDialogMVC;
//
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.event.ActionListener;
//
//import javax.swing.JButton;
//import javax.swing.JDialog;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//
///** */
//public class TimerView extends JDialog {
//	
//	/** TODO need to look up why we serialize JDialogs */
//	private static final long serialVersionUID = 1L;
//	
//	/** GUI Component to show the elapsed time. */
//	private static JLabel timeDisplay = new JLabel("00:00:00");
//	
//	/** GUI Component to start timer. */
//	private static JButton startButton = new JButton("START");
//	
//	/** GUI Component to stop timer. */
//	private static JButton stopButton = new JButton("STOP");
//	
//	/** */
//	private TimerModel timerModel = new TimerModel();
//	
//	/** Constructor to set up the UI*/
//	public TimerView ()  {
//		super ((JFrame) null, "Timer");
//		
//		JPanel timerFace = new JPanel();
//		
//		/*  TODO Find a font that resemble a big digital clock
//		timerFace.setFont(new Font(, , 24));
//		*/
//		
//		JPanel timerButtons = new JPanel();
//		
//		//  Banas has "JFrame." but Cheon doesn't include it.
//		//  Is there a difference
//		setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
//		
//		setSize (new Dimension (600, 200));
//		setVisible (true);
//		setResizable (false);
//		
//		//  *****  DON'T KNOW WHY WE'RE USING THESE CHEON FUNCTIONS  **********
//		configureUI();
//		setLocationRelativeTo (null);
//		
//		timerFace.add(getTimeDisplay());
//		timerButtons.add(getStartButton());
//		timerButtons.add(getStopButton());
//		
//		this.add(timerFace, BorderLayout.NORTH);
//		this.add(timerButtons);
//	}
//	
//	/** Method to get the time in the timerDisplay*/
//	public String getTimeInDisplay ()  {
//		return getTimeDisplay().getText ();
//	}
//	
//	/** Method to change the time in the timerDisplay*/
//	public void setTimeInDisplay (String newTime)  {
//		TimerView.getTimeDisplay().setText(newTime);
//	}
//	
//	/** If the startButton is clicked execute a method
//	 *  in the TimerController named startPerformed.*/
//	void addStartButtonListener (ActionListener listenForStart)  {
//		startButton.addActionListener(listenForStart);
//	}
//	
//	/** If the stopButton is clicked execute a method
//	 *  in the TimerController named stopPerformed.*/
//	void addStopButtonListener (ActionListener listenForStop)  {
//		startButton.addActionListener(listenForStop);
//	}
//	
//	/** */
//	private void configureUI()  {
//		/* configure UI. */
//		startButton.addActionListener(e -> {
//			tm.start();
//			timeDisplay.setText("timeStarted");
//			timeDisplay.setText(Long.toString(tm.elapsedTime() ) );
//		});
//		
//		stopButton.addActionListener(e -> {
//			tm.stop();
//			timeDisplay.setText("timeStopped");
//			timeDisplay.setText(Long.toString(tm.elapsedTime() ) );
//		});
//	}
//	
//	public static JButton getStartButton() {
//		return startButton;
//	}
//
//	public static JButton getStopButton() {
//		return stopButton;
//	}
//
//	public static JLabel getTimeDisplay() {
//		return timeDisplay;
//	}
//
//	/** Used to run the timer JDialog*/
//	public static void main (String[] args)  {
//		new TimerView();
//	}
//}


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class TimerView extends JDialog {
	private JButton startButton = new JButton("Start");
	private JButton stopButton = new JButton("Stop");
	public static JLabel timerDisplay = new JLabel("00:00:00");
	TimerModel timer = new TimerModel();

	public TimerView() {
		super((JFrame) null, "Timer");
		setSize(new Dimension(300, 175));
		configureUI();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	private void configureUI() {
		JPanel content = new JPanel();
		content.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
		timerDisplay.setFont(new Font("Tahoma", Font.PLAIN, 35));
		content.add(timerDisplay, BorderLayout.NORTH);
		
		JPanel buttons = new JPanel();
		buttons.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		buttons.add(startButton);
		buttons.add(stopButton);
		stopButton.setEnabled(false);
		stopButton.setFocusable(false);
		startButton.setFocusable(false);
		
		add(content, BorderLayout.NORTH);
		add(buttons);

		startButton.addActionListener(event -> {
			timer.start();
		});
		stopButton.addActionListener(event -> {
			timer.stop();
		});
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						if (timer.isRunning() ) {
							startButton.setEnabled(false);
							stopButton.setEnabled(true);
							long currentTime = timer.elapsedTime();
							long sec = (currentTime / 1000) % 60;
							long min = (currentTime / 60000) % 60;
							long hrs = (currentTime / 3600000) % 24;
							timerDisplay.setText(String.format("%02d", hrs) + ":" + String.format("%02d", min) + ":"
									+ String.format("%02d", sec));
						} else {
							startButton.setEnabled(true);
							stopButton.setEnabled(false);
						}
						Thread.sleep(10);
					}
				} catch (InterruptedException e) {
					System.out.println(e.getStackTrace());
				}
			}
		}).start();
	}

	public static void main(String[] args) {
		new TimerView();
	}
}
