import java.util.*;

public abstract class Account implements Authenticatable{
    private static long accountID = 10100;

    private String accountHolderName;
    private long accountNumber;
    private double balance;
    int pin;
    private List<Transaction> transactions;

    Account (String accountHolderName, double balance, int pin) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.pin = pin;
        accountNumber = accountID++;
        transactions = new ArrayList<>();
    }

    public String getAccountHolderName () {
        return accountHolderName;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double balance) throws InvalidInputException {
        if (balance < 0) 
            throw new InvalidInputException("Amount must be positive");
        this.balance += balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction (Transaction transaction) {
        transactions.add(transaction);
    }

    public abstract double calculateInterest();

    @Override
    public boolean verifyPin(int pin) {
        return this.pin == pin;
    }
}
