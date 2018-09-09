package courseplanner;

import java.util.Scanner;

/**
 * The <code>InputHandler</code> class provides automatic input validation.
 * 
 *
 * @author Sayan Sivakumaran e-mail: sayan.sivakumaran@stonybrook.edu Stony
 *         Brook ID: 110261379
 * 
 **/

public class InputHandler {
	private Scanner in = new Scanner(System.in);

	/**
	 * Equivalent to Scanner's nextLine().
	 * 
	 * @return The Scanner's nextLine() value.
	 */
	public String nextLine() {
		return in.nextLine();
	}

	/**
	 * Receives an integer from user input.
	 * 
	 * @return An integer.
	 */
	public int nextInt() {
		while (!in.hasNextInt()) {
			System.err.print("Please enter a number: ");
			in.nextLine();
		}
		return Integer.parseInt(in.nextLine());
	}

	/**
	 * Receives a non-negative integer from user input.
	 * 
	 * @return A non-negative integer.
	 */
	public int nextNonNegativeInt() {
		int number;
		do {
			number = nextInt();
			if (number < 0) {
				System.err.print("Please make sure to give a non-negative number: ");
			}
		} while (number <= 0);
		return number;
	}

	/**
	 * Receives a positive integer from user input.
	 * 
	 * @return A positive integer.
	 */
	public int nextPositiveInt() {
		int number;
		do {
			number = nextInt();
			if (number <= 0) {
				System.err.print("Please make sure to give a positive number: ");
			}
		} while (number < 0);
		return number;
	}
}
