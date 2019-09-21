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
	private static ArrayList<Comment> commentArray = new ArrayList<Comment>();
	
	public Comment() {}
	
	public Comment(String pDescription) {
		analyzeComment(pDescription);
	}

	public Comment(Float pScore, String pToneName ,String pDescription) {
		description = pDescription;
		score = pScore;
		toneName = pToneName;
	}

	private ToneAnalyzer authentication() {
		IamOptions options = new IamOptions.Builder().apiKey(apiKey).build();
		ToneAnalyzer toneAnalyzer = new ToneAnalyzer(version, options);
		toneAnalyzer.setEndPoint(url);
		return toneAnalyzer;
	}
	
	private void analyzeComment(String comment) {
		ToneAnalyzer toneAnalyzer = authentication();
		ToneOptions toneOptions = new ToneOptions.Builder().text(comment).build();
		DocumentAnalysis toneAnalysis = toneAnalyzer.tone(toneOptions).execute().getDocumentTone();
		parseInfo(toneAnalysis, comment);	
	}
	
	private void parseInfo(DocumentAnalysis toneAnalysis, String text) {
		JSONObject obj = new JSONObject(toneAnalysis);

		JSONArray arr = obj.getJSONArray("tones");
		for (int i = 0; i < arr.length(); i++)
		{
		    Float score = arr.getJSONObject(i).getFloat("score");
		    String toneName = arr.getJSONObject(i).getString("toneName");
		    Comment newComment = new Comment(score, toneName, text);
		    commentArray.add(newComment);
		}
	}
	
	public String toString() {
		String msg = "";
		msg+= "Description: " + description + "\n";
		msg+= "Tone: " + toneName  + "\n";
		msg+= "Score: " + String.valueOf(score)  + "\n";
		return msg;
	}
	
	public ArrayList<Comment> getCommentArray(String toneName){
		ArrayList<Comment> list = new ArrayList<Comment>();
		for(int i = 0; i < commentArray.size(); i++) {
			System.out.print(commentArray.get(i));
			if(commentArray.get(i).toneName.equals(toneName)) {
				System.out.println(commentArray.get(i));
				list.add(commentArray.get(i));
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
}
