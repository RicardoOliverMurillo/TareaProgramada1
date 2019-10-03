package services;

import java.util.ArrayList;

import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;

import userLogic.Comment;

public interface SentimentAnalyzerInterface {
	
	public abstract ArrayList<Comment> analyzeComment(Comment comment);
	public abstract ArrayList<Comment> parseInfo(ToneAnalysis toneAnalysis, String text);

}
