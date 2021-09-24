package presentacion.factoryGUI;

import presentacion.vista.IGUI;

public abstract class GUIFactory {
	private static GUIFactory factoryInstance;
	
	public static GUIFactory getInstance() {
		if (factoryInstance == null) factoryInstance = new GUIFactoryImp();
		return factoryInstance;
	}
	
	public abstract IGUI getFrame(int evento);
}
