package courseplanner;

import java.util.Scanner;

/**
* The <code>InputHandler</code> class provides automatic input validation.
*    
*
* @author Sayan Sivakumaran
*    e-mail: sayan.sivakumaran@stonybrook.edu
*    Stony Brook ID: 110261379
*    
**/

public class InputHandler {
	private Scanner in = new Scanner(System.in);
	
	/**
	 * Equivalent to Scanner's nextLine().
	 * 
	 * @return
	 * 	The Scanner's nextLine() value.
	 */
	public String nextLine() {
		return in.nextLine();
	}
	
	/**
	 * Equivalent to Scanner's nextInt().
	 * 
	 * @return
	 * 	The Scanner's nextInt() value.
	 */
	public int nextInt() {
		return in.nextInt();
	}
	
	/**
	 * Receives a positive number from user input.
	 * 
	 * @return
	 * 	A positive number.
	 */
	public int nextPositiveInt() {
		int number;
		do {
		    while (!in.hasNextInt()) {
		        System.out.print("Please enter a number: ");
		        in.next();
		    }
		    number = in.nextInt();
		    if (number < 0) {
		    	System.out.print("Please make sure to give a non-negative number: ");
		    }
		} while (number < 0);
		return number;
	}
}
