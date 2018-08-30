package courseplanner;

import java.util.Scanner;

/**
* The <code>PlannerManager</code> class The main method runs a menu driven 
* application which first creates an empty Planner object. The program prompts 
* the user for a command to execute an operation. Once a command has been 
* chosen, the program may ask the user for additional information if 
* necessary, and performs the operation.
*    
*
* @author Sayan Sivakumaran
*    e-mail: sayan.sivakumaran@stonybrook.edu
*    Stony Brook ID: 110261379
*    
**/

public class PlannerManager {
	private Planner planner = new Planner(); //Stores the main Planner
	private Planner backupPlanner; //Stores the backup Planner
	
	/**
	 * Delegates which function to run depending on the command entered.
	 * 
	 * @param in
	 * 	The scanner being used to handle input.
	 * 
	 * <dt>Postconditions</dt>
	 * 	<dd>The command has called the appropriate function.</dd>
	 */
	public static void commandManager(InputHandler in) {
		System.out.print("Enter a selection: ");
		char command = in.nextLine().charAt(0);
		switch(Character.toUpperCase(command)) {
			case 'A': {
				addCourse(in);
				break;
			}
			case 'Q': {
				System.out.println("Program terminating successfully...");
				System.exit(0);
				break;
			}
			default: {
				System.out.println("That command is not valid. Please try again.");
				commandManager(in);
			}
		}
	}
	
	/**
	 * Constructs the course object specified by the parameters and adds it to
	 * the planner.
	 * 
	 * @param in
	 * 	The scanner being used to handle input.
	 * 
	 * <dt>Postconditions</dt>
	 * 	<dd>The course with the given field values is now in the planner.</dd>
	 */
	public static void addCourse(InputHandler in) {
		System.out.print("Enter coursename: ");
		String name = in.nextLine();
		System.out.print("Enter department: ");
		String department = in.nextLine();
		System.out.print("Enter course code: ");
		int code = in.nextPositiveInt();
		System.out.print("Enter course section: ");
		int section = in.nextPositiveInt();
		System.out.print("Enter position: ");
		int position = in.nextInt();
		commandManager(in);
	}
	
	public static void main(String[] args) throws FullPlannerException {
		InputHandler in = new InputHandler();
		commandManager(in);
	}
}
