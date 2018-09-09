package courseplanner;

/**
 * The <code>Planner</code> class implements an abstract data type for a list of
 * courses and some common operations on such lists.
 * 
 *
 * @author Sayan Sivakumaran e-mail: sayan.sivakumaran@stonybrook.edu Stony
 *         Brook ID: 110261379
 * 
 **/

public class Planner {
	private final int MAX_COURSES = 50; // Maximum amount of courses the planner can hold
	private Course[] courseList; // Array of courses
	private int courseCount;// Number of courses in courseList

	/**
	 * Constructs an instance of the Planner with no Course objects in it.
	 * 
	 * <dl>
	 * <dt>Postconditions:</dt>
	 * <dd>This Planner has been initialized to an empty list of Courses.</dd>
	 * </dl>
	 * 
	 */
	public Planner() {
		courseList = new Course[MAX_COURSES];
		courseCount = 0;
	}

	/**
	 * Determines the number of courses currently in the list.
	 * 
	 * <dl>
	 * <dt>Preconditions:</dt>
	 * <dd>This planner has been instantiated.</dd>
	 * </dl>
	 * 
	 * @return The number of courses in this planner.
	 * 
	 */
	public int size() {
		return courseCount;
	}

	/**
	 * Adds a new course at the specified position.
	 * 
	 * @param course   The new course to be added.
	 * @param position The position (preference) of this course on the list.
	 * 
	 *                 <dl>
	 *                 <dt>Preconditions:</dt>
	 *                 <dd>This Course object has been instantiated and 1 &#8804;
	 *                 <code>position</code> &#8804; (<code>courseCount</code> + 1).
	 *                 The number of Course objects in this Planner is less than
	 *                 <code>MAX_COURSES</code>.</dd>
	 * 
	 *                 <dt>Postconditions:</dt>
	 *                 <dd>The new Course is now listed in the correct preference on
	 *                 the list. All Courses that originally had position greater
	 *                 than or equal to <code>position</code> are moved back one
	 *                 position.</dd>
	 *                 </dl>
	 * 
	 * @throws IllegalArgumentException Indicates that position is not within the
	 *                                  valid range.
	 * @throws FullPlannerException     Indicates that there is no more room in the
	 *                                  Planner for an additional course.
	 */
	public void addCourse(Course course, int position) throws IllegalArgumentException, FullPlannerException {
		if (position < 1 || position > courseCount + 1) {
			throw new IllegalArgumentException(position + " is out of range.");
		} else if (courseCount == MAX_COURSES) {
			throw new FullPlannerException();
		}
		// Position shifted down by 1 to account for index starting at 0
		position -= 1;
		// Shift courses after position up by 1
		for (int i = courseCount - 1; i >= position; i--) {
			courseList[i + 1] = courseList[i];
			courseList[i] = null;
		}
		courseList[position] = course;
		courseCount += 1;
	}

	/**
	 * Adds a new course at the end of the list.
	 * 
	 * @param course The new course to be added.
	 * 
	 *               <dl>
	 *               <dt>Preconditions:</dt>
	 *               <dd>The Course object has been instantiated and the number of
	 *               Course objects in this Planner is less than
	 *               <code>MAX_COURSES</code>.</dd>
	 * 
	 *               <dt>Postconditions:</dt>
	 *               <dd>The new Course is now listed at the end of the list.</dd>
	 *               </dl>
	 * 
	 * @throws FullPlannerException Indicates that there is no more room in the
	 *                              Planner for an additional course.
	 */
	public void addCourse(Course course) throws FullPlannerException {
		addCourse(course, courseCount + 1);
	}

	/**
	 * Removes a course at the specified position.
	 * 
	 * @param position the position in the Planner where the Course will be removed
	 *                 from
	 * 
	 *                 <dl>
	 *                 <dt>Preconditions:</dt>
	 *                 <dd>This Planner has been instantiated and 1 &#8804;
	 *                 <code>position</code> &#8804; (<code>courseCount</code>).
	 *                 </dd>
	 * 
	 *                 <dt>Postconditions:</dt>
	 *                 <dd>The Course at the desired position has been removed. All
	 *                 Courses that originally had position greater than or equal to
	 *                 <code>position</code> are moved backward one position.</dd>
	 *                 </dl>
	 * 
	 * @throws IllegalArgumentException Indicates that <code>position</code> is not
	 *                                  within the valid range.
	 */
	public void removeCourse(int position) throws IllegalArgumentException {
		if (position < 1 || position > courseCount) {
			throw new IllegalArgumentException(position + " is out of range.");
		}
		// Position shifted down by 1 to account for index starting at 0
		position -= 1;
		courseList[position] = null;
		// Move courses backward
		for (int i = position; i < courseCount; i++) {
			courseList[i] = courseList[i + 1];
			courseList[i + 1] = null;
		}
		courseCount -= 1;
	}

	/**
	 * Retrieves a course from the specified position.
	 * 
	 * @param position the position of the Course to retrieve.
	 * 
	 *                 <dl>
	 *                 <dt>Preconditions:</dt>
	 *                 <dd>This Planner has been instantiated and 1 &#8804;
	 *                 <code>position</code> &#8804; (<code>courseCount</code>).
	 *                 </dd>
	 *                 </dl>
	 * 
	 * @return The course at the specific position in this Planner object.
	 * @throws IllegalArgumentException Indicates that <code>position</code> is not
	 *                                  within the valid range.
	 */
	public Course getCourse(int position) throws IllegalArgumentException {
		return courseList[position - 1];
	}

	/**
	 * Prints all courses that are in the specified department.
	 * 
	 * @param planner    The list of courses to search in
	 * @param department The 3 letter department code for a Course
	 * 
	 *                   <dl>
	 *                   <dt>Preconditions:</dt>
	 *                   <dd>This Planner has been instantiated.</dd>
	 * 
	 *                   <dt>Postconditions:</dt>
	 *                   <dd>Displays a neatly formatted table of each course
	 *                   filtered from the Planner.</dd>
	 *                   </dl>
	 * 
	 */
	public static void filter(Planner planner, String department) {
		for (int i = 0; i < planner.courseCount; i++) {
			Course course = planner.courseList[i];
			if (course.getDepartment().equals(department)) {
				System.out.println(course.toString(i + 1));
			}
		}
	}

	/**
	 * Checks whether a certain Course is already in the list.
	 * 
	 * @param course The course we are looking for.
	 * 
	 *               <dl>
	 *               <dt>Preconditions:</dt>
	 *               <dd>This Planner and Course have both been instantiated.</dd>
	 *               </dl>
	 * 
	 * @return True if the Planner contains the course, false otherwise.
	 */
	public boolean exists(Course course) {
		for (Course courseObject : courseList) {
			if (course.equals(courseObject)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Checks whether this planner is full.
	 * 
	 * <dl>
	 * <dt>Preconditions:</dt>
	 * <dd>This planner object has been instantiated.</dd>
	 * </dl>
	 * 
	 * @return True if the planner is full, false otherwise.
	 */
	public boolean isFull() {
		return courseCount == MAX_COURSES;
	}

	/**
	 * Creates a deep copy of this Planner.
	 *
	 * <dl>
	 * <dt>Preconditions:</dt>
	 * <dd>This Planner object has been instantiated.</dd>
	 * </dl>
	 * 
	 * <p>
	 * <strong>Note:</strong> Make sure to typecast the return value to <code>Course</code>.
	 * </p>
	 * 
	 * @return A clone (backup) of this Planner Object.
	 */
	public Object clone() {
		Object clonedPlanner = new Planner();
		((Planner) clonedPlanner).courseList = (Course[]) courseList.clone();
		((Planner) clonedPlanner).courseCount = courseCount;
		
		return clonedPlanner;
	}

	/**
	 * Gets the String representation of this Planner object.
	 * 
	 * <dl>
	 * <dt>Preconditions:</dt>
	 * <dd>This Planner object has been instantiated.</dd>
	 *
	 * @return The string representation of this planner object.
	 */
	@Override
	public String toString() {
		String plannerString = "";
		for (int i = 0; i < courseCount; i++) {
			plannerString += courseList[i].toString(i + 1);
		}

		return plannerString;
	}

	/**
	 * Prints a neatly formatted table representation of this planner.
	 *
	 * <dl>
	 * <dt>Preconditions:</dt>
	 * <dd>This Planner object has been instantiated.</dd>
	 * 
	 * <dt>Postconditions:</dt>
	 * <dd>Displays a neatly formatted table of each course from the Planner.</dd>
	 * </dl>
	 * 
	 */
	public void printAllCourses() {
		System.out.println(toString());
	}
}
