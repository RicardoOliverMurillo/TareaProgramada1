package creationalLogic;

import behaviorLogic.Record;

public class SimpleFactoryRecord {
	
	/**
	 * Constructor class
	 */
	public SimpleFactoryRecord() {}

	/**
	 * method that creates a Record object depending on the type that is requested
	 * 
	 * @param type of Record
	 * @return the specific Record object 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public Record createRecord(String type) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Record record = (Record) Class.forName("behaviorLogic."+type).newInstance();
		return record;
	}

}
