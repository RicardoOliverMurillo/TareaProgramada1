package observerLogic;


public class CSV extends Record {

	public CSV() {}
	
	public CSV(Action action) {
		this.action = action;
		this.action.subscribe(this);
	}
	
	//Lógica para escribir en archivo
	@Override
	public void register() {
		System.out.println("CSV");
		System.out.println(action.getUserId());
		System.out.println(action.getAction());
		System.out.println(action.getDate());
		System.out.println(action.getTime());
	}
	
	public String[] read() {
		return null;
	}

}
