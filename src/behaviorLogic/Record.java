package behaviorLogic;

public abstract class Record {
	
	protected Action action;
	
	public abstract void register();
	public abstract String[] read();

}
