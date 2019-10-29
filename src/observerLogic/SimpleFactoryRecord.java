package observerLogic;

public class SimpleFactoryRecord {

	/**
	 * method that creates a Record object depending on the type that is requested
	 * 
	 * @param type of Record
	 * @return the specific Record object 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	public static Record createRecord(String type) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Record record = (Record) Class.forName("observerLogic."+type).newInstance();
		return record;
	}

}
