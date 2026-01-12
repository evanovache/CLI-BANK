import java.util.*;

public abstract class Account implements Authenticatable{
    private static long accountID = 10100;

    private String accountHolderName;
    private long accountNumber;
    private double balance;
    String password;
    int pin;
    private List<Transaction> transactions;

    Account (String accountHolderName, double balance, String password) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.pin = 0;
        this.password = password;
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

    public void setPin(int pin) throws InvalidFormatException {
        if (pin > 999 && pin < 10000)
            this.pin = pin;
        else 
            throw new InvalidFormatException("PIN must have only 4 digits");
    }

    public int getPin() {
        return pin;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction (Transaction transaction) {
        transactions.add(transaction);
    }

    public abstract double calculateInterest();

    public abstract void withdraw(double amount) throws InsufficientFundsException, InvalidInputException;

    @Override
    public boolean verifyPin(int pin) {
        return this.pin == pin;
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }
}
