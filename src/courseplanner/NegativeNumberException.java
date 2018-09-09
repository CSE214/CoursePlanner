package courseplanner;

/**
* <code>NegativeNumberException</code> is thrown when a negative number is 
* entered as input for a function that takes non-negative values.
*    
*
* @author Sayan Sivakumaran
*    e-mail: sayan.sivakumaran@stonybrook.edu
*    Stony Brook ID: 110261379
**/

public class NegativeNumberException extends Exception {
	
	/**
	 * Returns an instance of <code>NegativeNumberException</code>.
	 */
	public NegativeNumberException() {
		super();
	}
	
	/**
	 * Returns an instance of <code>NegativeNumberException</code> along with the specified message.
	 * 
	 * @param message 
	 * 	The message that accompanies the error.
	 */
	public NegativeNumberException(String message) {
		super(message);
	}

	private static final long serialVersionUID = -1006030493743069075L;

}
