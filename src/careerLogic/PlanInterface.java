package careerLogic;

public interface PlanInterface {
	public void addCourse(Course course);
	
	public void generateNetView();
	
	public void addKnowledgeArea(String description);
	
	public String getId();

	public void setId(String id);

	public Career getCareer();
	
	public void setCareer(Career career);
}
