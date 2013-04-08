import java.util.*;
public class Phase {
	
	
Player player;
private int numSets;
private int setSize;
//This is only used for phase 9 and 10
private int secondSetSize;
private int runSize;

// i have to finish the checkPhase
public void checkPhase(int curPhase, ArrayList<Card> phaseCards){
	if (curPhase == 1){
		// Phase 1 is 2 sets of 3
		numSets = 2;
		setSize = 3;
		runSize = 0;
	}else if (curPhase ==2){
		// Phase 2 is 1 set of 3 and 1 run of 4
		numSets = 1;
		setSize = 3;
		runSize = 4;
	}else if (curPhase == 3){
		// Phase 3 is 1 set of 4 and 1 run of 4 
		numSets = 1;
		setSize = 4;
		runSize = 4;
	}else if (curPhase == 4){
		// Phase 4 is 1 run of 7 
		numSets = 0;
		setSize = 0;
		runSize = 7;
	}else if (curPhase == 5){
		// Phase 5 is 1 run of 8
		numSets = 0;
		setSize = 0;
		runSize = 8;
	}else if (curPhase == 6){
		// Phase 6 is 1 run of 9
		numSets = 0;
		setSize = 0;
		runSize = 9;
	}else if (curPhase == 7){
		// Phase 7 is 2 sets of 4
		numSets = 2;
		setSize = 4;
		runSize = 0;
	}else if (curPhase == 8){
		// Phase 8 is 7 cards of one color
		numSets = 1;
		setSize = 7;
		runSize = 0;
	}else if (curPhase == 9){
		// Phase 9 is 1 set of 5 and 1 set of 2
		numSets = 2;
		setSize = 3;
		secondSetSize = 2;
		runSize = 0;
	}else if (curPhase == 10){
		// Phase 10 is 1 set of 5 and 1 set of 3
		numSets = 2;
		setSize = 5;
		secondSetSize = 3;
		runSize = 0;
	}
	
}

//for the checkSets and checkRuns make sure that the algorithms
//take into account that wild cards can stand for anything

public static Boolean checkNumSet(ArrayList<Card> numSet, int setSize){
	Boolean setStatus = true;
	int numCheck = numSet.get(0).getNumber();
	if (numSet.size() == setSize){
		for(int i = 0; i < setSize; i++){
			if (numCheck != numSet.get(i).getNumber()){
				setStatus = false;
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
			if (colorCheck != colorSet.get(i).getColor()){
				setStatus = false;
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
			if(runStart != run.get(i).getNumber()-1){
				runStatus = false;
			}
			runStart++;
		}
	} else {
		return false;
	}
	return runStatus;
}

}
