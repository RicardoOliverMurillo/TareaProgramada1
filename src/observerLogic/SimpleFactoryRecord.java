package observerLogic;

public class SimpleFactoryRecord {

	public static Record createRecord(String type) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		Record record = (Record) Class.forName("observerLogic."+type).newInstance();
		return record;
	}

}
