package courseplanner;

import java.util.Scanner;

/**
 * The <code>PlannerManager</code> class The main method runs a menu driven
 * application which first creates an empty Planner object. The program prompts
 * the user for a command to execute an operation. Once a command has been
 * chosen, the program may ask the user for additional information if necessary,
 * and performs the operation.
 * 
 *
 * @author Sayan Sivakumaran e-mail: sayan.sivakumaran@stonybrook.edu Stony
 *         Brook ID: 110261379
 * 
 **/

public class PlannerManager {
	private static Planner planner = new Planner(); // Stores the main Planner
	private static Planner backupPlanner; // Stores the backup Planner
	private static InputHandler in = new InputHandler();

	/**
	 * Prints a list of commands for the user.
	 * 
	 * <dt>Postconditions</dt>
	 * <dd>The list of commands has been printed.</dd>
	 */
	public static void printMenu() {
		System.out.println("\n(A) Add Course\r\n" + "(G) Get Course\r\n" + "(R) Remove Course\r\n"
				+ "(P) Print Courses in Planner\r\n" + "(F) Filter by Department Code\r\n" + "(L) Look For Course\r\n"
				+ "(S) Size\r\n" + "(B) Backup\r\n" + "(PB) Print Courses in Backup\r\n" + "(RB) Revert to Backup\r\n"
				+ "(Q) Quit" + "\n");
	}

	/**
	 * Prints the header for the planner table.
	 * 
	 * <dt>Postconditions</dt>
	 * <dd>The planner table has been printed.</dd>
	 */
	public static void printTableHeader() {
		System.out.println(String.format("\n%-10s%-30s%-15s%-10s%2s%15s", "No.", "Name", "Department", "Code", "Section",
				"Instructor")
				+ "\n---------------------------------------------------------------------------------------------------------\n");
	}

	/**
	 * Delegates which function to run depending on the command entered.
	 * 
	 * @param in The scanner being used to handle input.
	 * 
	 *           <dt>Postconditions</dt>
	 *           <dd>The command has called the appropriate function.</dd>
	 */
	public static void commandManager() {
		System.out.print("Enter a selection: ");
		String command = in.nextLine();
		switch (command.toUpperCase()) {
		case "A": {
			addCourse();
			break;
		}
		case "G": {
			getCourse();
			break;
		}
		case "R": {
			removeCourse();
			break;
		}
		case "P": {
			printAllCourses();
			break;
		}
		case "F": {
			filterCourses();
			break;
		}
		case "L": {
			lookForCourse();
			break;
		}
		case "Q": {
			System.out.println("Program terminating successfully...");
			System.exit(0);
			break;
		}
		default: {
			System.err.println("That command is not valid. Please try again.");
			commandManager();
		}
		}
	}

	/**
	 * Constructs the course object specified by the parameters and adds it to the
	 * planner.
	 * 
	 * @param in The scanner being used to handle input.
	 * 
	 * <dt>Postconditions</dt>
	 * <dd>The course with the given field values is now in the
	 * planner, and the user returns to the menu.</dd>
	 * 
	 */
	public static void addCourse() {
		if (planner.isFull()) {
			System.err.println("The planner is already full.");
			commandManager();
		}
		System.out.print("Enter course name: ");
		String name = in.nextLine();
		System.out.print("Enter department: ");
		String department = in.nextLine();
		System.out.print("Enter course code: ");
		int code = in.nextNonNegativeInt();
		System.out.print("Enter course section: ");
		byte section = (byte) in.nextNonNegativeInt();
		System.out.print("Enter instructor: ");
		String instructor = in.nextLine();
		System.out.print("Enter position: ");
		int position = in.nextPositiveInt();

		try {
			planner.addCourse(new Course(name, department, code, section, instructor), position);
		} catch (IllegalArgumentException e) {
			System.err.println("The position is invalid, and must be between 1 and " + (planner.size() + 1) + ". Please try again.");
			addCourse();
		} catch (FullPlannerException e) {
			System.err.println("The planner is already full.");
			commandManager();
		}

		System.out.println("The course " + name + " has successfully been added.");
		printMenu();
		commandManager();
	}
	
	/**
	 * Retrieves the course from the specified position, and prints it to the user.
	 * 
	 * <dt>Postconditions</dt>
	 * <dd>The desired course has been printed, and the user returns to the menu.</dd>
	 */
	public static void getCourse() {
		System.out.print("Enter position: ");
		int position = in.nextPositiveInt();
		
		try {
			printTableHeader();
			System.out.println(planner.getCourse(position).toString(position));
			printMenu();
			commandManager();
		} catch (IllegalArgumentException e) {
			System.err.println("The position is invalid, and must be between 1 and " + planner.size() + ". Please try again.");
			getCourse();
		}
	}
	
	/**
	 * Removes the course from the specified position, and prints out a confirmation message.
	 * 
	 * <dt>Postconditions</dt>
	 * <dd>The desired course has been printed, and the user returns to the menu.</dd>
	 */
	public static void removeCourse() {
		System.out.print("Enter position: ");
		int position = in.nextPositiveInt();
		
		try {
			Course course = planner.getCourse(position);
			System.out.println("The course" + course.getDepartment() + " " + course.getCode() + "." + course.getSection() + " has been removed.");
			planner.removeCourse(position);
			printMenu();
			commandManager();
			
		} catch (IllegalArgumentException e) {
			System.err.println("The position is invalid, and must be between 1 and " + planner.size() + ". Please try again.");
			removeCourse();
		}
	}
	
	/**
	 * Prints all the courses in the planner.
	 * 
	 * <dt>Postconditions</dt>
	 * <dd>The planner has been printed, and the user returns to the menu.</dd>
	 */
	public static void printAllCourses() {
		System.out.println("Planner:");
		printTableHeader();
		planner.printAllCourses();
		printMenu();
		commandManager();
	}
	
	/**
	 * Filters the courses based on a given department code, and prints the
	 * results to the user.
	 * 
	 * <dt>Postconditions</dt>
	 * <dd>The filtered courses have been printed, and the user returns to the menu.</dd>
	 */
	public static void filterCourses() {
		System.out.print("Enter department code: ");
		String department = in.nextLine();
		printTableHeader();
		Planner.filter(planner, department);
		printMenu();
		commandManager();
	}
	
	/**
	 * Looks for a course with the given fields in the planner.
	 * 
	 * <dt>Postconditions</dt>
	 * <dd>The program attempts to find the course, and prints the appropriate
	 * message. The user then returns to the menu.</dd>
	 */
	public static void lookForCourse() {
		System.out.print("Enter course name: ");
		String name = in.nextLine();
		System.out.print("Enter department: ");
		String department = in.nextLine();
		System.out.print("Enter course code: ");
		int code = in.nextNonNegativeInt();
		System.out.print("Enter course section: ");
		byte section = (byte) in.nextNonNegativeInt();
		System.out.print("Enter instructor: ");
		String instructor = in.nextLine();
		
		for (int i = 0; i < planner.size(); i++) {
			Course course = planner.getCourse(i + 1);
			if (course.getName() == name && course.getDepartment() == department && course.getCode() == code && course.getSection() == section && course.getInstructor() == instructor) {
				System.out.println("The course" + course.getDepartment() + " " + course.getCode() + "." + course.getSection() + " has been found in position " + (i + 1) + ".");
				printMenu();
				commandManager();
			}
		}
		
		System.err.println("That course could not be found.");
		printMenu();
		commandManager();
	}

	public static void main(String[] args) throws FullPlannerException {
		printMenu();
		commandManager();
	}
}
