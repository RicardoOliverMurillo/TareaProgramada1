package userLogic;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneOptions;

public class Comment{

	private Float score;
	private String toneName;
	private String description;
	private static int count = 0;
	private int id;
	private String idOwner;
	
	public Comment() {}
	
	public Comment(String pDescription) {
		description = pDescription;
		setId(count++);
	}
	
	public String toString() {
		String msg = "";
		msg+= "Description: " + description + "\n";
		msg+= "Tone: " + toneName  + "\n";
		msg+= "Score: " + String.valueOf(score)  + "\n";
		return msg;
	}
	
	public ArrayList<Comment> getCommentArray(String toneName, ArrayList<Comment> result){
		ArrayList<Comment> list = new ArrayList<Comment>();
		for(int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i));
			if(result.get(i).toneName.equals(toneName)) {
				System.out.println(result.get(i));
				list.add(result.get(i));
			}
		}
		return list;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getToneName() {
		return toneName;
	}

	public void setToneName(String toneName) {
		this.toneName = toneName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(String idOwner) {
		this.idOwner = idOwner;
	}
}
