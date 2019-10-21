package businessLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoCareer;
import dao.DaoInterface;
import dao.DaoRelevantInfo;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class Career for the insertion and manipulation of the careers information. 
 */

public class Career implements CareerInterface, CareerInfoInterface{
	private String id;
	private String name;
	private ArrayList<RelevantInfo> infos;
	private ArrayList<Plan> plans;
	private DaoInterface dbCareer, dbInformation;
	
	/**
	 * Constructor of the class Career
	 */
	public Career() {
		dbCareer = new DaoCareer();
		dbInformation = new DaoRelevantInfo();
	}
	
	/**
	 * Constructor of the class career with the id of the course
	 * @param pId id of the course that is going to be created
	 */
	public Career(String id) {
		this();
		this.setId(id);
		this.infos = new ArrayList<RelevantInfo>();
		this.plans = new ArrayList<Plan>();
	}
	
	/**
	 * Constructor of the class carrer with the id and the name of the course
	 * @param pId id of the course that is going to be created
	 * @param pName name of the course that is going to be created
	 */
	public Career(String id, String pName){
		this(id);
		this.name = pName;
	}

	/**
	 * the method add the relevant information of the career
	 * @param pType the type of the information that is going to be added
	 * @param pDescription the description of the information that is going to be added
	 */
	public void addInfo(String type, String description) {
		RelevantInfo info = new RelevantInfo(type,description);
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
	 * the method inserts the information of the career in the database 
	 * @param career the career that it's going to be stored
	 * @throws SQLException
	 */
	public void addCareer(Career career) throws SQLException {
		dbCareer.manipulationQuery("INSERT INTO CAREERS(IDCAREER,NAME) VALUES "
				+ "('"+career.getId()+"','"+career.getName()+"')");
	}
	
	/**
	 * the method return all the careers stored in the database 
	 * @return result ArrayList with the careers stored in the database
	 * @throws SQLException
	 */
	public ArrayList<Career> getAllCareer() throws SQLException {
		ArrayList<Career> result = new ArrayList<Career>();
		result = (ArrayList<Career>) dbCareer.selectQuery("SELECT * FROM CAREERS");
		return result;
	}
	
	/**
	 * the method inserts the relevant information of the career in the database 
	 * @param info the career that has the information that wants to be stored
	 * @throws SQLException
	 */
	public void insertInformation(Career info) throws SQLException {
		ArrayList<RelevantInfo> data = info.getInfos(); 
		for(int i = 0; i < data.size(); i++) {
			dbInformation.manipulationQuery("INSERT INTO INFORMATIONS(TYPE,DESCRIPTION,IDCAREER) VALUES "
					+ "('"+data.get(i).getType()+"','"+data.get(i).getDescription()+"','"+info.getId()+"')");
		}
	}
	
	/**
	 * the method retrieves the relevant information of an specific career 
	 * @param type the type of information that is requested
	 * @param career the career that has the information
	 * @return text description of the relevantInfo of the career
	 * @throws SQLException
	 */
	public String getInformation(String type, String career) throws SQLException {
		String text = new String();
		ArrayList<RelevantInfo> data = (ArrayList<RelevantInfo>) dbInformation.selectQuery("SELECT * FROM INFORMATIONS WHERE TYPE = '"+type+
				"' AND IDCAREER = '"+ career+"'");
		for(int i = 0; i < data.size(); i++) {
			text = data.get(i).getDescription();
		}
		return text;
	}
	
	/**
	 * the method updates the relevant information of the career 
	 * @param info the career with the info that wants to be updated
	 * @throws SQLException
	 */
	public void updateInformation(Career info) throws SQLException {
		ArrayList<RelevantInfo> data = info.getInfos(); 
		for(int i = 0; i < data.size(); i++) {
			dbInformation.manipulationQuery("UPDATE INFORMATIONS SET TYPE = '"+data.get(i).getType()+"', "
				+ "DESCRIPTION = '"+data.get(i).getDescription()+"', "
				+ "IDCAREER = '"+info.getId()+"' WHERE "
				+ "TYPE = '"+data.get(i).getType()+"' AND "
				+ "IDCAREER = '"+info.getId()+"'");
		}
	}
	
	/**
	 * the method return the relevant information of an specific career
	 * @param career that has the relevant information requested 
	 * @return result with the relevant information 
	 * @throws SQLException
	 */
	public ArrayList<RelevantInfo> getInformation(String career) throws SQLException{
		ArrayList<RelevantInfo> result = new ArrayList<RelevantInfo>();
		result = (ArrayList<RelevantInfo>) dbInformation.selectQuery("SELECT * FROM INFORMATIONS WHERE IDCAREER = '"+ career+"'");
		return result;
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

