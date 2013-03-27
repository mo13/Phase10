
public class ATMTest {

	public static void main(String[] args) {
		
		// create a checking account with $1000
		final Account checking = new Account(1000);
		
		// create two ATMs (automated teller machines)
		final ATM atm1 = new ATM();		
		final ATM atm2 = new ATM();
		
		// create a task, in which ATM1 is used to access the 
		// checking account, display balance and withdrawl all funds
		 Runnable r1 = new Runnable(){
			public void run(){
				atm1.useAccount(checking, "John");
				atm1.getBalance();
				atm1.withdrawl(1000);
				atm1.getBalance();
			}
		};

		// create another task doing the same things to the same 
		// account, but from ATM2
		Runnable r2 = new Runnable(){
			public void run(){
				atm2.useAccount(checking, "Mary");
				atm2.getBalance();
				atm2.withdrawl(1000);				
				atm2.getBalance();
			}
		};
		
		// start both threads, initiating the run() for each task
		Thread t1 = new Thread(r1);
		t1.start();		
		
		Thread t2 = new Thread(r2);
		t2.start();

	}
}
