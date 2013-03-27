
public final class ATM {

	private Account a;
	private String user;

	// a specific user is accessing a specific account
	public synchronized void useAccount(Account a, String name){
		this.a = a;		
		this.user = name;
	}

	// if the account has funds to cover, take out a specific amount
	public synchronized void withdrawl(int amount){
		if (a.deduct(amount))
			dispenseCash(amount);
		else
			System.out.println(user + ", you have insufficient funds!");
	}

	// add funds to the account
	public synchronized void deposit(int amount){
		a.add(amount);
		acceptCash(amount);
	}

	// once funds are withdrawn from the account, the ATM provides 
	// the paper money to the user
	private synchronized void dispenseCash(int amount){
		System.out.println(user + " receives $" + amount);
		System.out.println();
	}

	// once a user has keyed in a deposit, the ATM allows the user to 
	// insert cash or checks
	private synchronized void acceptCash(int amount){
		System.out.println(user + " deposits $" + amount);
		System.out.println();
	}	

	// display the current amount of funds in the account
	public synchronized void getBalance(){	
		System.out.println("Account balance displayed to " + user + ": $" + a.getBalance());
	}

}
