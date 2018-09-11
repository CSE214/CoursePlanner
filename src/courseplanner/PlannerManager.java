package courseplanner;

import java.util.Scanner;

/**
 * The <code>PlannerManager</code> class runs a menu driven
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
	private static Planner backupPlanner = new Planner(); // Stores the backup Planner
	private static Scanner in = new Scanner(System.in);

	/**
	 * Prints a list of commands for the user.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The list of commands has been printed.</dd>
	 * </dl>
	 * 
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
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The planner table has been printed.</dd>
	 * </dl>
	 */
	public static void printTableHeader() {
		System.out.println(String.format("\n%-10s%-30s%-15s%-10s%-10s%-15s", "No.", "Name", "Department", "Code",
				"Section", "Instructor")
				+ "\n---------------------------------------------------------------------------------------------------------\n");
	}

	/**
	 * Delegates which function to run depending on the command entered..
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The command has called the appropriate function.</dd>
	 * </dl>
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
		case "S": {
			size();
			break;
		}
		case "B": {
			backup();
			break;
		}
		case "PB": {
			printBackup();
			break;
		}
		case "RB": {
			revertToBackup();
			break;
		}
		case "Q": {
			System.out.println("Program terminating successfully...");
			System.exit(0);
			break;
		}
		default: {
			System.out.println("That command is not valid. Please try again.");
			commandManager();
		}
		}
	}

	/**
	 * Constructs the course object specified by the parameters and adds it to the
	 * planner.
	 * 
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The course with the given field values is now in the planner, and the
	 * user returns to the menu.</dd>
	 * </dl>
	 * 
	 */
	public static void addCourse() {
		if (planner.isFull()) {
			System.out.println("The planner is already full.");
			commandManager();
		}
		System.out.print("Enter course name: ");
		String name = in.nextLine();
		System.out.print("Enter department: ");
		String department = in.nextLine();
		System.out.print("Enter course code: ");
		int code = Integer.parseInt(in.nextLine());
		System.out.print("Enter course section: ");
		byte section = Byte.parseByte(in.nextLine());
		System.out.print("Enter instructor: ");
		String instructor = in.nextLine();
		System.out.print("Enter position: ");
		int position = Integer.parseInt(in.nextLine());

		Course course = null;
		try {
			course = new Course(name, department, code, section, instructor);
			planner.addCourse(course, position);
		} catch (IllegalArgumentException e) {
			
				System.out.println("The position is invalid, and must be between 1 and " + (planner.size() + 1)
						+ ". Please try again.");
			printMenu();
			commandManager();
		} catch (FullPlannerException e) {
			System.out.println("The planner is already full.");
			printMenu();
			commandManager();
		} catch (NegativeNumberException e) {
			System.out.println("Make sure both the code and section are non-negative.");
			printMenu();
			commandManager();
		}

		System.out.println("The course " + course.toShortString() + " has successfully been added.");
		printMenu();
		commandManager();
	}

	/**
	 * Retrieves the course from the specified position, and prints it to the user.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The desired course has been printed, and the user returns to the
	 * menu.</dd>
	 * </dl>
	 */
	public static void getCourse() {
		System.out.print("Enter position: ");
		int position = Integer.parseInt(in.nextLine());

		try {
			printTableHeader();
			System.out.println(planner.getCourse(position).toString(position));
			printMenu();
			commandManager();
		} catch (IllegalArgumentException e) {
			System.out.println(
					"The position is invalid, and must be between 1 and " + planner.size() + ". Please try again.\n");
			getCourse();
		}
	}

	/**
	 * Removes the course from the specified position, and prints out a confirmation
	 * message.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The desired course has been printed, and the user returns to the
	 * menu.</dd>
	 * </dl>
	 */
	public static void removeCourse() {
		System.out.print("Enter position: ");
		int position = Integer.parseInt(in.nextLine());

		try {
			Course course = planner.getCourse(position);
			System.out.println("The course " + course.toShortString() + " has been removed.");
			planner.removeCourse(position);
			printMenu();
			commandManager();
		} catch (IllegalArgumentException e) {
			System.out.println(
					"The position is invalid, and must be between 1 and " + planner.size() + ". Please try again.\n");
			removeCourse();
		}
	}

	/**
	 * Prints all the courses in the planner.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The planner has been printed, and the user returns to the menu.</dd>
	 * </dl>
	 */
	public static void printAllCourses() {
		System.out.print("\nPlanner:");
		printTableHeader();
		planner.printAllCourses();
		printMenu();
		commandManager();
	}

	/**
	 * Filters the courses based on a given department code, and prints the results
	 * to the user.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The filtered courses have been printed, and the user returns to the
	 * menu.</dd>
	 * </dl>
	 * 
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
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The program attempts to find the course, and prints the appropriate
	 * message. The user then returns to the menu.</dd>
	 * </dl>
	 * 
	 */
	public static void lookForCourse() {
		System.out.print("Enter course name: ");
		String name = in.nextLine();
		System.out.print("Enter department: ");
		String department = in.nextLine();
		System.out.print("Enter course code: ");
		int code = Integer.parseInt(in.nextLine());
		System.out.print("Enter course section: ");
		byte section = Byte.parseByte(in.nextLine());
		System.out.print("Enter instructor: ");
		String instructor = in.nextLine();

		for (int i = 0; i < planner.size(); i++) {
			Course course = planner.getCourse(i + 1);
			if (course.getName().equals(name) && course.getDepartment().equals(department) && course.getCode() == code
					&& course.getSection() == section && course.getInstructor().equals(instructor)) {
				System.out.println(
						"The course " + course.toShortString() + " has been found in position " + (i + 1) + ".");
				printMenu();
				commandManager();
			}
		}

		System.out.println("That course could not be found.");
		printMenu();
		commandManager();
	}

	/**
	 * Prints the number of courses in the planner to the user.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The number of courses has been printed, and the user returns to the
	 * menu.</dd>
	 * </dl>
	 */
	public static void size() {
		if (planner.size() == 1) {
			System.out.println("There is 1 course in the planner.");
		} else {
			System.out.println("There are " + planner.size() + " course(s) in the planner.");
		}
		printMenu();
		commandManager();
	}

	/**
	 * Saves a deep copy of the planner to backupPlanner.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The backupPlanner is a deep copy of the planner.</dd>
	 * </dl>
	 */
	public static void backup() {
		backupPlanner = (Planner) planner.clone();
		System.out.println("Created a backup of the current planner.");
		printMenu();
		commandManager();
	}

	/**
	 * Prints all the courses in the backup planner.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The backup planner has been printed, and the user returns to the
	 * menu.</dd>
	 * </dl>
	 * 
	 */
	public static void printBackup() {
		System.out.print("\nPlanner (Backup):");
		printTableHeader();
		backupPlanner.printAllCourses();
		printMenu();
		commandManager();
	}

	/**
	 * Changes the reference of the main planner to the backup planner.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The main planner has reverted to the backup.</dd>
	 * </dl>
	 * 
	 */
	public static void revertToBackup() {
		planner = (Planner) backupPlanner.clone();
		System.out.println("Planner successfully reverted to the backup copy.");
		printMenu();
		commandManager();
	}

	/**
	 * Starts the program, and sends the user to the main menu.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The user enters the main menu.</dd>
	 * </dl>
	 * 
	 */
	public static void main(String[] args) throws FullPlannerException, NegativeNumberException {
		planner.addCourse(new Course("Computational Geometry", "CSE", 355, (byte) 2, "Jie Gao"));
		printMenu();
		commandManager();
	}

}
