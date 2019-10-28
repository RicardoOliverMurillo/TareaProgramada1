package observerLogic;

import java.text.SimpleDateFormat;
import java.util.*;

import businessLogic.Session;

public class Action {

	private String action;
	private String date;
	private String time;
	private String userId;
	private ArrayList<Record> records;
	
	public Action() {
		this.records = new ArrayList<Record>();
	}
	
	public Action(String userId, String action, String date, String time) {
		this.userId = userId;
		this.action = action;
		this.date = date;
		this.time = time;
		this.records = new ArrayList<Record>();
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setAction(String action) {
		this.action = action;
		setUserId(Session.getUser());
		setDate();
		setTime();
		registerOnRecord();
	}
	
	public String getAction() {
		return action;
	}
	
	public void setDate() {
		Calendar calendar;
		SimpleDateFormat format =new SimpleDateFormat("MM/dd/yy");
		calendar = Calendar.getInstance();
		date = format.format(calendar.getTime());
	}
	
	public String getDate() {
		return date; 
	}
	
	public void setTime() {
		Calendar calendar;
		SimpleDateFormat format =new SimpleDateFormat("HH:mm:ss");
		calendar = Calendar.getInstance();
		time = format.format(calendar.getTime());
	}
	
	public String getTime() {
		return time; 
	}
	
	
	public void subscribe(Record record) {
		records.add(record);
	}
	
	private void registerOnRecord() {
		for(Record record : records) {
			record.register();
		}
	}
}
