package courseplanner;

import java.util.Scanner;

public class PlannerManager {
	static Scanner input = new Scanner(System.in);
	
	public static void commandManager() {
		System.out.println("Enter a selection: ");
		String command = input.nextLine();
		System.out.println(command);
	}
	
	public static void addCourse(String name, ) {
		
	}
	
	public static void main(String[] args) throws FullPlannerException {
		Planner planner = new Planner();
		commandManager();
	}
}
