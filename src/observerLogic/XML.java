package observerLogic;

import java.util.ArrayList;

public class XML extends Record{

	public XML() {}
	
	public XML(Action action) {
		this.action = action;
		this.action.subscribe(this);
	}
	
	//Lógica para escribir en archivo
	@Override
	public void register() {
		System.out.println("XML");
		System.out.println(action.getUserId());
		System.out.println(action.getAction());
		System.out.println(action.getDate());
		System.out.println(action.getTime());
	}

	@Override
	public String[] read() {
		return null;
	}
	

}
