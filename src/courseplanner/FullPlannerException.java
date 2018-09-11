package courseplanner;

/**
* <code>FullPlannerException</code> is thrown when the Planner has no more room for
* more courses.
*    
*
* @author Sayan Sivakumaran
*    e-mail: sayan.sivakumaran@stonybrook.edu
*    Stony Brook ID: 110261379
**/
public class FullPlannerException extends Exception {
	
	/**
	 * Returns an instance of <code>FullPlannerException</code>.
	 */
	public FullPlannerException() {
		super();
	}
	
	/**
	 * Returns an instance of <code>FullPlannerException</code> along with the specified message.
	 * 
	 * @param message 
	 * 	The message that accompanies the error.
	 */
	public FullPlannerException(String message) {
		super(message);
	}

	private static final long serialVersionUID = -1050567991674332447L;
}
