package careerLogic;

import java.util.ArrayList;

public class Career {
	private String id;
	private String name;
	private ArrayList<RelevantInfo> infos;
	private ArrayList<Plan> plans;
	
	public Career() {
	}
	

	public Career(String pId, String pName){
		this.id = pId;
		this.name = pName;
		this.infos = new ArrayList<RelevantInfo>();
		this.plans = new ArrayList<Plan>();
	}
	
	public void addInfo(String pType, String pDescription) {
		RelevantInfo info = new RelevantInfo(pType,pDescription);
		infos.add(info);
	}
	
	public void addPlan(Plan plan) {
		plans.add(plan);
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
}
