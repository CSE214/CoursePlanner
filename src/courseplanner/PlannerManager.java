package courseplanner;

import java.util.Scanner;

public class PlannerManager {
	private Planner planner = new Planner(); //Stores the main Planner
	private Planner backupPlanner; //Stores the backup Planner
	
	public static void commandManager(Scanner in) {
		System.out.println("Enter a selection: ");
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
	 * @param name
	 * 	The name of the course.
	 * @param department
	 * 	The department of the course.
	 * @param code
	 * 	The code of the course.
	 * @param section
	 * 	The section of the course.
	 * @param instructor
	 * 	The instructor of the course.
	 * 
	 * <dt>Preconditions</dt>
	 * 	<dd>Code and section are both positive numbers.</dd>
	 * 
	 * <dt>Postconditions</dt>
	 * 	<dd>The course with the given field values is now in the planner.</dd>
	 */
	public static void addCourse(Scanner in) {
		System.out.println("Inside add");
		commandManager(in);
	}
	
	public static void main(String[] args) throws FullPlannerException {
		Scanner in = new Scanner(System.in);
		commandManager(in);
	}
}
