package timerDialogMVC;

/**
 * A timer clock using the MVC Model.
 * 
 * @author Checo Ponce De Leon
 */
public class MVCTimer {
	
	/**
	 * The main method instantiates the objects representing each 
	 * component of the MVC model.
	 * 
	 * @param args
	 */
	public static void main (String[] args)  {
		
		/*
		 * The ClockView represents the graphical clock face, it 
		 * does not calculate the time. It only contains the GI
		 * and displays the time that it is given. 
		 */
		TimerView theView = new TimerView ();
		TimerModel theModel = new TimerModel ();
		TimerController theController = new TimerController (theView, theModel);
	}
}
