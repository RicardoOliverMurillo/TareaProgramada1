package businessLogic;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class RelevantInfo for the insertion and manipulation of the relevant information data. 
 */
public class RelevantInfo {
	private String type;
	private String description;
	private String career;
	
	/**
	 * The constructor of the class RelevantInfo with the type and description of the information
	 * @param pType type of information that is going to be added
	 * @param pDescription the description of the information that is going to be added
	 */
	public RelevantInfo(String pType, String pDescription) {
		type = pType;
		description = pDescription;
	}
	
	public RelevantInfo() {
	}
	
	/**
	 * the method return the type of the information added
	 * @return type the type of the information 
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * the method sets the type of information 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * the method return the decription of the information 
	 * @return description the description of the information 
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * the method sets the description of information 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * the method return the career of the information
	 * @return career the career of the information 
	 */
	public String getCareer() {
		return career;
	}
	
	/**
	 * the method sets the career of information 
	 * @param career
	 */
	public void setCareer(String career) {
		this.career = career;
	}
	
}
