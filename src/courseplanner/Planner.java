package courseplanner;

/**
* The <code>Planner</code> class implements an abstract data type for a list of courses and some common operations on such lists.
*    
*
* @author Sayan Sivakumaran
*    e-mail: sayan.sivakumaran@stonybrook.edu
*    Stony Brook ID: 110261379
*    
* @custom.mytag hey ho...
**/

public class Planner {
	private final int MAX_COURSES = 50; // Maximum amount of courses the planner can hold
	private Course[] courseList; // Array of courses
	private int courseCount;// Number of courses in courseList
	
	/**
	 * Constructs an instance of the Planner with no Course objects in it.
	 * 
	 * <dt>Postconditions:</dt>
	 * <dd>This Planner has been initialized to an empty list of Courses.</dd>
	 */
	public Planner() {
		courseList = new Course[MAX_COURSES];
		courseCount = 0;
	}

	/**
	 * Determines the number of courses currently in the list.
	 * 
	 * <dt>Preconditions:</dt>
	 * <dd>This planner has been instantiated.</dd>
	 * 
	 * @return 
	 * 	The number of courses in this planner.
	 * 
	 */
	public int size() {
		return courseCount;
	}
	
	/**
	 * Adds a new course into <code>courseList</code> at the computed index.
	 * 
	 * @param newCourse
	 * 	The new course to be added.
	 * @param position
	 * 	The position (preference) of this course on the list.
	 * 
	 *<dt>Preconditions:</dt>
     * <dd>
     * This Course object has been instantiated and 
     * 1 &#8804; <code>position</code> &#8804; (<code>courseCount</code> + 1). The number of Course objects in 
     * this Planner is less than <code>MAX_COURSES</code>.
     * </dd>
     * 
     * <dt>Postconditions:</dt>
     * <dd>
     * The new Course is now listed in the correct preference on the list. 
     * All Courses that originally had preference greater than or equal to 
     * <code>position</code> are moved back one position.
     * </dd>
     * 
     * @throws IllegalArgumentException
     * 	Indicates that position is not within the valid range.
     * @throws FullPlannerException
     * 	Indicates that there is no more room in the Planner for an additional course.
	 */
	public void addCourse(Course newCourse, int position) throws IllegalArgumentException, FullPlannerException{
		
	}
}
