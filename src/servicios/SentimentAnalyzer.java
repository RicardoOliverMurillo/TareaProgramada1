package servicios;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ibm.cloud.sdk.core.service.security.IamOptions;
import com.ibm.watson.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneOptions;

import userLogic.Comment;

public class SentimentAnalyzer {
	
	private String apiKey = "wbgiPCmqKEdP0E62x86JNO4cgy51VJDwMEAXZT0uOsc5";
	private String url = "https://gateway.watsonplatform.net/tone-analyzer/api";
	private String version = "2017-09-21";
	
	public SentimentAnalyzer() {}
	
	public ArrayList<Comment> analyzeComment(Comment comment) {
		System.out.println("Entro a analyzer");
		ToneAnalyzer toneAnalyzer = authentication();
		ToneOptions toneOptions = new ToneOptions.Builder().text(comment.getDescription()).build();
		ToneAnalysis  toneAnalysis = toneAnalyzer.tone(toneOptions).execute().getResult();
		ArrayList<Comment> result = parseInfo(toneAnalysis, comment.getDescription());
		return result;
	}
	
	private ToneAnalyzer authentication() {
		IamOptions options = new IamOptions.Builder().apiKey(apiKey).build();
		ToneAnalyzer toneAnalyzer = new ToneAnalyzer(version, options);
		toneAnalyzer.setEndPoint(url);
		return toneAnalyzer;
	}
	
	private ArrayList<Comment> parseInfo(ToneAnalysis toneAnalysis, String text) {
		JSONObject obj = new JSONObject(toneAnalysis);
		ArrayList<Comment> result = new ArrayList<Comment>();

		JSONObject document = obj.getJSONObject("documentTone");
		JSONArray arr = document.getJSONArray("tones");
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

}
