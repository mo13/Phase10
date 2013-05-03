import controller.*;
import java.util.*;
import model.*;
import strategy.*;
import view.*;

public class GameSimulation {

		
	public static void main(String[] args) {
		GameModel model = new GameModel();
		GameObserver gui = new Gui(model);
		Controller controller = new Controller(model, gui );

	}

}