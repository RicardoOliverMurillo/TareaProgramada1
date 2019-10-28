package observerLogic;

import java.util.ArrayList;

public abstract class Record {
	
	protected Action action;
	
	public abstract void register();
	public abstract ArrayList<Action> read();

}
