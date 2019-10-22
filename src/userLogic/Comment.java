package userLogic;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneOptions;

/**
 * 
 * @author Marco Gómez, Ricardo Oliver, Anjelica Tristani 
 *
 * Class comment for the insertion and manipulation of the comments of the students. 
 */
public class Comment{

	private Float score;
	private String toneName;
	private String description;
	private static int count = 0;
	private int id;
	private String idOwner;
	
	public Comment() {}
	
	/**
	 * Constructor of the class Comment 
	 * @param pDescription of the comment
	 */
	public Comment(String description) {
		this.description = description;
		setId(count++);
	}
	
	/**
	 * Method that prettify the comment analysis
	 * @return msg with the information formatted
	 */
	public String toString() {
		String msg = "";
		msg+= "Description: " + description + "\n";
		msg+= "Tone: " + toneName  + "\n";
		msg+= "Score: " + String.valueOf(score)  + "\n";
		return msg;
	}
	
	/**
	 * method that get the comments array
	 * @param toneName tone that want to be analyzed
	 * @param result array with the comments with the tone requested
	 * @return
	 */
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

	/**
	 * method that get the score of the tone analysis 
	 * @return score float with the score value
	 */
	public Float getScore() {
		return score;
	}

	/**
	 * the method that sets the score of the tone analysis in the comment 
	 * @param score that is going to be assigned 
	 */
	public void setScore(Float score) {
		this.score = score;
	}

	/**
	 * method that get the tone analyzed
	 * @return toneName the tone of the comment 
	 */
	public String getToneName() {
		return toneName;
	}

	/**
	 * method that sets the tone of the comment 
	 * @param toneName tone name of the comment 
	 */
	public void setToneName(String toneName) {
		this.toneName = toneName;
	}

	/**
	 * method that get the description of the comment 
	 * @return description of the comment 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * method that sets the description of the comment 
	 * @param description that is going to be set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * method that get the id of the comment 
	 * @return id of the comment 
	 */
	public int getId() {
		return id;
	}

	/**
	 * method that sets the id of the comment 
	 * @param id that is going to be set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * method that get the owner of the comment 
	 * @return idOwner of the comment 
	 */
	public String getIdOwner() {
		return idOwner;
	}

	/**
	 * method that sets the owner of the comment 
	 * @param idOwner that is going to be set
	 */
	public void setIdOwner(String idOwner) {
		this.idOwner = idOwner;
	}
}
