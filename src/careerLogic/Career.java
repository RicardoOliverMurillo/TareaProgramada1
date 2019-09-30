package careerLogic;

import java.util.ArrayList;

public class Career {
	private String id;
	private String name;
	private ArrayList<RelevantInfo> infos;
	private ArrayList<Plan> plans;
	
	public Career(String pId) {
		this.setId(pId);
		this.infos = new ArrayList<RelevantInfo>();
		this.plans = new ArrayList<Plan>();
	}
	

	public Career(String pId, String pName){
		this(pId);
		this.name = pName;
	}
	
	public Career() {
		// TODO Auto-generated constructor stub
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
	
	public ArrayList<RelevantInfo> getInfos(){
		return infos;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
}
