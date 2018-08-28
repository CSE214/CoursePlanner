package courseplanner;

/**
* The <code>Planner</code> class implements an abstract data type for a list of courses and some common operations on such lists.
*    
*
* @author Sayan Sivakumaran
*    e-mail: sayan.sivakumaran@stonybrook.edu
*    Stony Brook ID: 110261379
**/

public class Planner {
	private final int MAX_COURSES = 50; // Maximum amount of courses the planner can hold
	private Course[] courseList; // Array of courses
	private int courseCount;// Number of courses in courseList
	
	/**
	 * Constructs an instance of the Planner with no Course objects in it.
	 *
	 * <strong>Postcondition:</strong>
	 */
	public Planner() {
		courseList = new Course[MAX_COURSES];
		courseCount = 0;
	}

	public int size() {
		return courseCount;
	}
}
