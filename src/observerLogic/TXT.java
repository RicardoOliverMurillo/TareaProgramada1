package observerLogic;

import java.util.ArrayList;

public class TXT extends Record{
	
	public TXT(Action action) {
		this.action = action;
		this.action.subscribe(this);
	}

	//Lógica para escribir en archivo
	@Override
	public void register() {
		System.out.println("TXT");
		System.out.println(action.getUserId());
		System.out.println(action.getAction());
		System.out.println(action.getDate());
		System.out.println(action.getTime());
	}

	@Override
	public ArrayList<Action> read() {
		return null;
	}

}
