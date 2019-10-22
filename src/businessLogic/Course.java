package businessLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoCourse;
import dao.DaoEquivalences;
import dao.DaoInterface;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class Course for the insertion and manipulation of the courses information. 
 */
public class Course implements CourseInterface, EquivalencesInterface, ApprovedInterface, ReportsInterface{
	
	private String idCourse;
	private String name;
	private int sumCredits;
	private int semester;
	private String knowledgeArea;
	private Plan plan;
	private ArrayList<String> corequisites;
	private ArrayList<String> requisites;
	private ArrayList<Course> equivalences;
	private DaoInterface dbCourse, dbEquivalences;
	
	
	/**
	 * Constructor of the class Course
	 */
	public Course() {
		dbCourse = new DaoCourse();
		dbEquivalences = new DaoEquivalences();
		this.equivalences = new ArrayList<Course>();
	}
	
	/**
	 * Constructor of the class Course
	 * 
	 * @param idCourse id of the new course 
	 * @param name name of the course
	 * @param sumCredits the number of credits of the course
	 * @param semester number of the semester that is located the course
	 * @param knowledgeArea the focus area of the course
	 * @param plan the plan where belongs the course
	 */
	public Course (String idCourse, String name, int sumCredits, int semester, String knowledgeArea, Plan plan) {
		this();
		this.idCourse = idCourse;
		this.name = name;
		this.sumCredits = sumCredits;
		this.semester = semester;
		this.knowledgeArea = knowledgeArea;
		this.setPlan(plan);
		this.corequisites = new ArrayList<String>();
		this.requisites = new ArrayList<String>();
		this.equivalences = new ArrayList<Course>();
	}
	
	/**
	 * the method check if the course is approved by the student
	 * @param courses of the student
	 * @param id of the student 
	 * @return boolean true if the course is approved or false if is not approved
	 */
	public boolean isAprove(ArrayList<String> courses, String id) {
		for(int i = 0; i < courses.size(); i++) {
			if(id.equals(courses.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method that insert the approved course of the student in the database
	 * @param data the courses that the student approved
	 * @throws Exception
	 */
	public void insertApprovedFilter(ArrayList<Course> data) throws Exception {
		for(int i = 0; i < data.size(); i++) {
			insertStudentCourse(Session.getUser(), data.get(i).getId());
		}
	}
	
	/**
	 * the method delete the approved course of the student from the database
	 * @param data the courses that the student wants to delete 
	 * @throws Exception
	 */
	public void deleteApprovedFilter(ArrayList<Course> data) throws Exception {
		for(int i = 0; i < data.size(); i++) {
			deleteStudentCourse(Session.getUser(), data.get(i).getId());
		}
	}
	
	/**
	 * the method get the courses of an specific plan 
	 * @param plan that has the courses requested
	 * @return result with the array of courses
	 * @throws SQLException
	 */
	public ArrayList<Course> getCoursesPlan(String plan) throws SQLException{
		ArrayList<Course> data = (ArrayList<Course>) dbCourse.selectQuery("SELECT * FROM COURSES WHERE IDPLAN = "+plan+"");
		return data;
	}
	
	/**
	 * Method that filter the courses by semester 
	 * @param semester the specific semester that retrieves the information
	 * @return data with the courses of an specific semester 
	 * @throws SQLException
	 */
	public ArrayList<Course> getCoursesSemester(String semester) throws SQLException{
		ArrayList<Course> data = (ArrayList<Course>) dbCourse.selectQuery("SELECT * FROM COURSES WHERE SEMESTER = '"+semester+"' AND IDPLAN = 2050");
		return data;
	}
	
	/**
	 * Method that filter the courses by knowledge Area
	 * @param area the specific knowledge Area that retrieves the information
	 * @return data with the courses of an specific knowledge Area
	 * @throws SQLException
	 */
	public ArrayList<Course> getCoursesArea(String area) throws SQLException{
		ArrayList<Course> data = (ArrayList<Course>) dbCourse.selectQuery("SELECT * FROM COURSES WHERE KNOWLEDGEAREA  = '"+area+"' AND IDPLAN = 2050");
		return data;
	}
	
	/**
	 * Method that stores the information of the courses approved by an specific student in the database
	 * @param idStudent of the student that approve the course
	 * @param idCourse the course approved by the student
	 * @throws Exception
	 */
	public void insertStudentCourse(String idStudent, String idCourse) throws Exception {
		dbCourse.manipulationQuery("INSERT INTO STUDENTCOURSE (IDSTUDENT, IDCOURSE) VALUES ('"+idStudent+"','"+idCourse+"')");
	}
	
	/**
	 * Method that deletes an specific course approved by a student
	 * @param idStudent the student that wants to delete the course
	 * @param idCourse the course that is going to be deleted
	 * @throws Exception
	 */
	public void deleteStudentCourse(String idStudent, String idCourse) throws Exception {
		dbCourse.manipulationQuery("DELETE FROM STUDENTCOURSE WHERE IDCOURSE = '"+idCourse+"' AND IDSTUDENT = '"+idStudent+"'");
	}
	
	/**
	 * the method return an specific course from an specific plan
	 * @param idCourse of the course requested
	 * @param idPlan of the plan where the course is located
	 * @return result ArrayList with the course in the database 
	 * @throws SQLException
	 */
	public Course getCourse(String idCourse, String idPlan) throws SQLException {
		ArrayList<Course> data = (ArrayList<Course>) dbCourse.selectQuery("SELECT * FROM COURSES WHERE IDCOURSE = '"+idCourse+"' AND IDPLAN = '"+idPlan+"'");
		Course course = null;
		for(int i = 0; i < data.size(); i++) {
			course = data.get(i);
		}
		return course;
	}
	
	/**
	 * the method return the pass credits of the plan requested
	 * @param courses id of the courses approved
	 * @param idPlan id of the plan where the courses are located
	 * @return the value of the pass credits of the specific plan
	 * @throws SQLException
	 */
	public String getPassCredits(ArrayList<String> courses, String idPlan) throws SQLException {
		int total = 0;
		for(int i = 0; i < courses.size(); i++) {
			Course course = getCourse(courses.get(i), idPlan);
			total = total + course.getSumCredits();
		}
		return String.valueOf(total);
	}
	
	/**
	 * the method returns the pass credits in other plan, using the equivalences
	 * @param courses Array list with the courses approved
	 * @param idPlan of the plan where the course is located
	 * @return return the number of credits approved in a different plan
	 * @throws SQLException
	 */
	public String getPassCreditsEquivalences(ArrayList<String> courses, String idPlan) throws SQLException {
		int total = 0;
		for(int i = 0; i < courses.size(); i++) {
			Course course = getCourse(courses.get(i), idPlan);
			total = total + course.getSumCredits();
		}
		return String.valueOf(total);
	}
	
	/**
	 * The method get the pass courses of the student
	 * @param idStudent id of the student that request the information
	 * @return result ArrayList with the approved courses
	 * @throws SQLException
	 */
	public ArrayList<String> getPassCourses(String idStudent) throws SQLException {
		ArrayList<String> result = (ArrayList<String>) dbEquivalences.selectQuery("SELECT IDCOURSE FROM STUDENTCOURSE WHERE IDSTUDENT = '"+idStudent+"'");
		return result;
	}
	
	/**
	 * the method get the courses approved in a different plan 
	 * @param idStudent the student that request the information
	 * @return result Array List with the pass courses in a different plan
	 * @throws SQLException
	 */
	public ArrayList<String> getPassCoursesEquivalences(String idStudent) throws SQLException{
		ArrayList<String> data = getPassCourses(idStudent);
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < data.size(); i++) {
			String equivalence = getEquivalences(data.get(i));
			result.add(equivalence);
		}
		return result;
	}
	
	/**
	 * the method get the equivalences from an specific course	
	 * @param idCourse the course requested
	 * @return result with the id of the course equivalence 
	 * @throws SQLException
	 */
	public String getEquivalences(String idCourse) throws SQLException{
			ArrayList<String> result = (ArrayList<String>) dbEquivalences.selectQuery("SELECT IDCOURSE2 FROM EQUIVALENCES WHERE IDCOURSE1 = '"+idCourse+"'");
			if(result.size()>0) {
				return result.get(0);
			}
			return null;
	}
	
	/**
	 * Method that register a new equivalence in the database
	 * @param idCourse1 id of the first course
	 * @param idPlan1 the id of the plan where the first course belongs
	 * @param idCourse2 id of the second course  
	 * @param idPlan2 the id of the plan where the first course belongs
	 * @throws SQLException
	 */
	public void addEquivalence(String idCourse1,String idPlan1,String idCourse2, String idPlan2) throws SQLException {
		dbCourse.manipulationQuery("INSERT INTO EQUIVALENCES(IDCOURSE1,IDPLAN1,IDCOURSE2,IDPLAN2) VALUES "
				+ "('"+idCourse1+"','"+idPlan1+"','"+idCourse2+"','"+idPlan2+"')");
	}
	
	/**
	 * the method returns the sum of the courses credits in an specific plan
	 * @param plan plan where the courses are located 
	 * @return the value of the total credits in the plan 
	 * @throws SQLException
	 */
	public String getTotalCredits(String plan) throws SQLException {
		ArrayList<Course> data = getCoursesPlan(plan);
		int total = 0;
		for(int i = 0; i < data.size(); i++) {
			total= total + data.get(i).getSumCredits();
		}
		data.clear();
		return String.valueOf(total);
	}
	
	/**
	 * the method retrieve the requirements of an specific course 
	 * @param idCourse id of the course requested 
	 * @param idPlan id of the plan where the course is located
	 * @return result Arraylist with the course requirements 
	 * @throws SQLException
	 */
	public ArrayList<String> getRequirements(String idCourse, String idPlan) throws SQLException{
		ArrayList<String> result = (ArrayList<String>) dbEquivalences.selectQuery("SELECT IDREQUIREMENT FROM REQUIREMENTS WHERE IDCOURSE = '"+idCourse+"' AND IDPLAN = '"+idPlan+"'");
		return result;
	}
	
	/**
	 * the method retrieve the corequirements of an specific course 
	 * @param idCourse id of the course requested 
	 * @param idPlan id of the plan where the course is located
	 * @return result Arraylist with the course corequirements 
	 * @throws SQLException
	 */
	public ArrayList<String> getCorequirements(String idCourse, String idPlan) throws SQLException{
		ArrayList<String> result = (ArrayList<String>) dbEquivalences.selectQuery("SELECT IDCOREQUIREMENT FROM COREQUIREMENTS WHERE IDCOURSE = '"+idCourse+"' AND IDPLAN = '"+idPlan+"'");
		return result;
	}
	
	/**
	 * the method register a new course in the database
	 * @param course the course that is going to be stored
	 * @throws Exception
	 */
	public void registerCourse(Course course) throws Exception {
		dbCourse.manipulationQuery("INSERT INTO COURSES (IDCOURSE,NAME,SUMCREDITS, SEMESTER, KNOWLEDGEAREA, IDPLAN) VALUES ('"+course.getId()+"','"+
				course.getName()+"','"+course.getSumCredits()+"','"+course.getSemester()+"','"+course.getKnowledgeArea()+"','"+course.getPlan().getId()+"')");
	}
	
	/**
	 * the method register the requirements of an specific course
	 * @param idCourse 
	 * @param idRequirement
	 * @param idPlan
	 * @throws Exception
	 */
	public void registerRequirements(String idCourse, String idRequirement, String idPlan) throws Exception {
		dbCourse.manipulationQuery("INSERT INTO REQUIREMENTS (IDCOURSE,IDREQUIREMENT,IDPLAN) VALUES ('"+idCourse+"','"+idRequirement+"','"+idPlan+"')");
	}
	
	/**
	 * the method register the corequirements of an specific course
	 * @param idCourse
	 * @param idCorequirement
	 * @param idPlan
	 * @throws Exception
	 */
	public void registerCorequirements(String idCourse, String idCorequirement,String idPlan) throws Exception {
		dbCourse.manipulationQuery("INSERT INTO COREQUIREMENTS (IDCOURSE,IDCOREQUIREMENT,IDPLAN) VALUES ('"+idCourse+"','"+idCorequirement+"','"+idPlan+"')");
	}
	
	/**
	 * method that gets the courses of an specific plan and build a matrix	
	 * @param plan the plan that has the courses
	 * @return result ArrayList with the courses requested 
	 * @throws SQLException
	 */
	public String[][] getCourses(String plan) throws SQLException{
		ArrayList<Course> data = getCoursesPlan(plan);
		String[][] result = new String[10][11];
		System.out.println(data.size());
		for(int i = 0; i < data.size(); i++) {
			int sem = data.get(i).getSemester();
			for(int row = 0; row < 10; row++) {
				if(result[row][sem] == null) {
					Course newCourse = data.get(i);
					result[row][sem] = newCourse.getId();
					break;
				}
			}
		}
		data.clear();
		return result;
	}
	
	/**
	 * the method returns the number of course registers in the database of an specific plan 
	 * @param plan the plan that has the courses requested
	 * @return integer with the number of registers
	 * @throws SQLException
	 */
	public int getCoursesLength(String plan) throws SQLException{
		ArrayList<Course> data = getCoursesPlan(plan);
		return data.size();
	}
	
	
	
	/**
	 * the method returns the number of course approved by the student in a different plan
	 * @param idStudent id of the student that request the information
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<String> getPassCoursesEquivalencesReport(String idStudent) throws SQLException{
		ArrayList<String> data = getPassCourses(idStudent);
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < data.size(); i++) {
			String equivalence = getEquivalences(data.get(i));
			if(equivalence != null)
				result.add(equivalence);
		}
		return result;
	}
	
	/**
	 * the method get the identifier of the course
	 * @return the idCourse
	 */
	public String getId() {
		return idCourse;
	}

	/**
	 * the method set the value of the course id
	 * @param idCourse the idCourse to set
	 */
	public void setId(String idCourse) {
		this.idCourse = idCourse;
	}

	/**
	 * the method get the course name 
	 * @return name of the course
	 */
	public String getName() {
		return name;
	}

	/**
	 * the method sets the name of the course
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * the method get the number of credits of the course
	 * @return the sumCredits 
	 */
	public int getSumCredits() {
		return sumCredits;
	}

	/**
	 * the method sets the number of credits of the course
	 * @param sumCredits the sumCredits to set
	 */
	public void setSumCredits(int sumCredits) {
		this.sumCredits = sumCredits;
	}

	/**
	 * the method get the semester that the course is located
	 * @return the semester
	 */
	public int getSemester() {
		return semester;
	}

	/**
	 * the method sets the semester where the course is located
	 * @param semester the semester to set
	 */
	public void setSemester(int semester) {
		this.semester = semester;
	}

	/**
	 * the method get the name of the knowledge area that the course belongs
	 * @return the knowledgeArea
	 */
	public String getKnowledgeArea() {
		return knowledgeArea;
	}

	/**
	 * the method sets the knowledge area that the course belongs
	 * @param knowledgeArea the knowledgeArea to set
	 */
	public void setKnowledgeArea(String knowledgeArea) {
		this.knowledgeArea = knowledgeArea;
	}
	
	/**
	 * the method return an array list with the course Corequisites 
	 * @return ArrayList with the corequisites of the course
	 */
	public ArrayList<String> getCorequisites() {
		return corequisites;
	}
	/**
	 * the method return an array list with the course requisites 
	 * @return ArrayList with the requisites of the course
	 */
	public ArrayList<String> getRequisites() {
		return requisites;
	}
	
	/**
	 * the method return an array list with the course equivalences 
	 * @return ArrayList with the equivalences of the course
	 */
	public ArrayList<Course> getEquivalences() {
		return equivalences;
	}

	/**
	 * the method add to the list of equivalences of the course 
	 * @param course
	 */
	public void addEquivalences(Course course) {
		this.equivalences.add(course);
	}
	
	/**
	 * the method add to the list of requisites of the course 
	 * @param course
	 */
	public void addRequisites(String course) {
		this.requisites.add(course);
	}
	
	/**
	 * the method add to the list of corequisites of the course 
	 * @param course
	 */
	public void addCorequisites(String course) {
		this.corequisites.add(course);
	}

	/**
	 * the method get the plan where the course belongs
	 * @return plan  
	 */
	public Plan getPlan() {
		return plan;
	}

	/**
	 * the method sets the plan where the course belongs
	 * @param plan that is going to be assigned 
	 */
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	
}
