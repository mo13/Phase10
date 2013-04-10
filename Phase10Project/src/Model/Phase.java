package Model;
import java.util.*;
public class Phase {
	
private int numSets;
private int colorSets;
private int setSize;
private int secondSetSize; //This is only used for phase 9 and 10
private int runSize;

// check phase is tested in Player.
public void checkPhase(int curPhase, Player player){
	if (curPhase == 1){
		// Phase 1 is 2 sets of 3
		player.numSets = 2;
		player.colorSets = 0;
		player.setSize = 3;
		player.runSize = 0;
	}else if (curPhase ==2){
		// Phase 2 is 1 set of 3 and 1 run of 4
		player.numSets = 1;
		player.colorSets = 0;
		player.setSize = 3;
		player.runSize = 4;
	}else if (curPhase == 3){
		// Phase 3 is 1 set of 4 and 1 run of 4 
		player.numSets = 1;
		player.colorSets = 0;
		player.setSize = 4;
		player.runSize = 4;
	}else if (curPhase == 4){
		// Phase 4 is 1 run of 7 
		player.numSets = 0;
		player.colorSets = 0;
		player.setSize = 0;
		player.runSize = 7;
	}else if (curPhase == 5){
		// Phase 5 is 1 run of 8
		player.numSets = 0;
		player.colorSets = 0;
		player.setSize = 0;
		player.runSize = 8;
	}else if (curPhase == 6){
		// Phase 6 is 1 run of 9
		player.numSets = 0;
		player.colorSets = 0;
		player.setSize = 0;
		player.runSize = 9;
	}else if (curPhase == 7){
		// Phase 7 is 2 sets of 4
		player.numSets = 2;
		player.colorSets = 0;
		player.setSize = 4;
		player.runSize = 0;
	}else if (curPhase == 8){
		// Phase 8 is 7 cards of one color
		player.numSets = 0;
		player.colorSets = 1;
		player.setSize = 7;
		player.runSize = 0;
	}else if (curPhase == 9){
		// Phase 9 is 1 set of 5 and 1 set of 2
		player.numSets = 2;
		player.colorSets = 0;
		player.setSize = 5;
		player.secondSetSize = 2;
		player.runSize = 0;
	}else if (curPhase == 10){
		// Phase 10 is 1 set of 5 and 1 set of 3
		player.numSets = 2;
		player.colorSets = 0;
		player.setSize = 5;
		player.secondSetSize = 3;
		player.runSize = 0;
	}
	
}

//for the checkSets and checkRuns make sure that the algorithms
//take into account that wild cards can stand for anything

public static Boolean checkNumSet(ArrayList<Card> numSet, int setSize){
	Boolean setStatus = true;
	int numCheck = numSet.get(0).getNumber();
	if (numSet.size() == setSize){
		for(int i = 0; i < setSize; i++){
			if(numSet.get(i).getType() == Card.type.Wild){
				setStatus = true;
			}
			else if (numCheck != numSet.get(i).getNumber()){
				setStatus = false;
				break;
			} 
		}
	} else {
		setStatus = false;
	}
	return setStatus;
}

public static Boolean checkColorSet(ArrayList<Card> colorSet, int setSize){
	Boolean setStatus = true;
	Card.cardColor colorCheck = colorSet.get(0).getColor();
	if (colorSet.size() == setSize){
		for(int i = 0; i < setSize; i++){
			if(colorSet.get(i).getType() == Card.type.Wild){
				setStatus = true;
			} else if (colorCheck != colorSet.get(i).getColor()){
				setStatus = false;
				break;
			}
		}
	} else {
		setStatus = false;
	}
	return setStatus;
}

// the say that I have checkRun set up we are going to have to ensure that when we pass the run
// into the method that it is in order from say 1,2,3,4,5
public static Boolean checkRun(ArrayList<Card> run, int runSize){
	Boolean runStatus = true;
	int runStart = run.get(0).getNumber();
	if (run.size() == runSize){
		for(int i = 1; i < runSize; i++){
			if(run.get(i).getType() == Card.type.Wild){
				runStatus = true;
			} else if(runStart != run.get(i).getNumber()-1){
				runStatus = false;
				break;
			}
			runStart++;
		}
	} else {
		return false;
	}
	return runStatus;
}

}
