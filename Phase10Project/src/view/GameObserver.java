package view;

import controller.*;
public interface GameObserver {
	void registerController(Controller controller);
	void createUI();
}
