package careerLogic;

public class RelevantInfo {
	private String type;
	private String description;
	private String career;
	
	public RelevantInfo(String pType, String pDescription) {
		type = pType;
		description = pDescription;
	}
	
	public RelevantInfo() {
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	
}
