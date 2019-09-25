package userLogic;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.DocumentAnalysis;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;

public class Comment {
	
	private String apiKey = "wbgiPCmqKEdP0E62x86JNO4cgy51VJDwMEAXZT0uOsc5";
	private String url = "https://gateway.watsonplatform.net/tone-analyzer/api";
	private String version = "2017-09-21";
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

	public ArrayList<Comment> analyzeComment(Comment comment) {
		ToneAnalyzer toneAnalyzer = authentication();
		ToneOptions toneOptions = new ToneOptions.Builder().text(comment.description).build();
		DocumentAnalysis toneAnalysis = toneAnalyzer.tone(toneOptions).execute().getDocumentTone();
		ArrayList<Comment> result = parseInfo(toneAnalysis, comment.description);
		return result;
	}
	
	private ToneAnalyzer authentication() {
		IamOptions options = new IamOptions.Builder().apiKey(apiKey).build();
		ToneAnalyzer toneAnalyzer = new ToneAnalyzer(version, options);
		toneAnalyzer.setEndPoint(url);
		return toneAnalyzer;
	}
	
	private ArrayList<Comment> parseInfo(DocumentAnalysis toneAnalysis, String text) {
		JSONObject obj = new JSONObject(toneAnalysis);
		ArrayList<Comment> result = new ArrayList<Comment>();

		JSONArray arr = obj.getJSONArray("tones");
		for (int i = 0; i < arr.length(); i++)
		{
			Comment comment = new Comment();
		    comment.setDescription(text);
		    comment.setScore(arr.getJSONObject(i).getFloat("score"));
		    comment.setToneName(arr.getJSONObject(i).getString("toneName"));
		    result.add(comment);
		}
		return result;
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
