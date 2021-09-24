package presentacion.controller;

public abstract class Controller {
	private static Controller instance;
	
	public static Controller getInstance() {
		if (instance == null) instance = new ControllerImp();
		return instance;
	}
	
	public abstract void action(int evento, Object transfer);
}
