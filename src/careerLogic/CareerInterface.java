package careerLogic;

public interface CareerInterface {
	public void addInfo(String pType, String pDescription);
	
	public void addPlan(Plan plan);
	
	public String getName();

	public void setName(String name);

	public String getId();

	public void setId(String id);
}
