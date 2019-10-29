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
	
	/**
	 * Constructor method
	 */
	public Action() {
		this.records = new ArrayList<Record>();
	}
	
	/**
	 * method that assigns the value of userId 
	 * 
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * method that gets the actual value of userId
	 * 
	 * @return actual value of userId
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * method that assigns the value of action
	 * 
	 * @param action name of the action
	 */
	public void setAction(String action) {
		this.action = action;
		setUserId(Session.getUser());
		setDate();
		setTime();
		registerOnRecord();
	}
	
	/**
	 * method that gets the actual value of action
	 * 
	 * @return actual value of action
	 */
	public String getAction() {
		return action;
	}
	
	/**
	 * method that assigns the value of date
	 */
	public void setDate() {
		Calendar calendar;
		SimpleDateFormat format =new SimpleDateFormat("MM/dd/yy");
		calendar = Calendar.getInstance();
		date = format.format(calendar.getTime());
	}
	
	/**
	 * method that gets the actual value of date
	 * 
	 * @return actual value of date
	 */
	public String getDate() {
		return date; 
	}
	
	/**
	 * method that assigns the value of time
	 */
	public void setTime() {
		Calendar calendar;
		SimpleDateFormat format =new SimpleDateFormat("HH:mm:ss");
		calendar = Calendar.getInstance();
		time = format.format(calendar.getTime());
	}
	
	/**
	 * method that gets the actual value of time
	 * 
	 * @return actual value of time
	 */
	public String getTime() {
		return time; 
	}
	
	/**
	 * method that adds observers
	 * 
	 * @param record observer
	 */
	public void subscribe(Record record) {
		records.add(record);
	}
	
	/**
	 * method that updates observers
	 */
	private void registerOnRecord() {
		for(Record record : records) {
			record.register();
		}
	}
}
