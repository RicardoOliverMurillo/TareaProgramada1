package careerLogic;

import java.util.ArrayList;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class Career for the insertion and manipulation of the careers information. 
 */

public class Career implements CareerInterface{
	private String id;
	private String name;
	private ArrayList<RelevantInfo> infos;
	private ArrayList<Plan> plans;
	
	/**
	 * Constructor of the class career with the id of the course
	 * @param pId id of the course that is going to be created
	 */
	public Career(String pId) {
		this.setId(pId);
		this.infos = new ArrayList<RelevantInfo>();
		this.plans = new ArrayList<Plan>();
	}
	
	/**
	 * Constructor of the class carrer with the id and the name of the course
	 * @param pId id of the course that is going to be created
	 * @param pName name of the course that is going to be created
	 */
	public Career(String pId, String pName){
		this(pId);
		this.name = pName;
	}
	
	public Career() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * the method add the relevant information of the career
	 * @param pType the type of the information that is going to be added
	 * @param pDescription the description of the information that is going to be added
	 */
	public void addInfo(String pType, String pDescription) {
		RelevantInfo info = new RelevantInfo(pType,pDescription);
		infos.add(info);
	}
	
	/**
	 * the method add a plan to the array list of plans that belongs to the career
	 * @param plan the plan that is going to be added 
	 */
	public void addPlan(Plan plan) {
		plans.add(plan);
	}
	
	/**
	 * the method get the name of the career
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/** the method set the name of the career
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * The method return the list of the relevant information of the course
	 * @return infos the arraylist that has all the information of the career
	 */
	public ArrayList<RelevantInfo> getInfos(){
		return infos;
	}
	
	/**
	 * the method return the id of the career
	 * @return id the identifier of the career
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * the method sets the id of the career
	 * @param id that is going to be set
	 */
	public void setId(String id) {
		this.id = id;
	}
}

