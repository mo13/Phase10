public class Account {
	
	// funds in the account
	private int total;
	
	// Constructor
	public Account(int initialDeposit){
		total = initialDeposit;

	}
	
	// Getters & Setters
	
	public synchronized boolean deduct(int withdrawlAmount){		
		if (total >= withdrawlAmount){
	
			// simulate delay in the system
			try{Thread.sleep(1);}
			catch (InterruptedException e){}
			
			total -= withdrawlAmount;
			return true;
		} else
			return false;	
	}	
	
	public synchronized void add(int depositAmount){
		total += depositAmount;		
	}

	public synchronized int getBalance(){
		return total;
	}
}
