public class SavingsAccount extends Account{
    private static final double MINIMUM_BALANCE = 50.0;
    private static final double INTEREST_RATE = 0.05; // 5% interest

    SavingsAccount(String accountHolderName, double balance, int pin) {
        super(accountHolderName, balance, pin);
    }

    @Override
    public void withdraw(double amount) throws InvalidInputException, InsufficientFundsException {
        if (amount < 0) 
            throw new InvalidInputException("Amount must be positive");
        if ((getBalance() - amount) < MINIMUM_BALANCE)
            throw new InsufficientFundsException("Account must have a minimum of $50.0");
        updateBalance(getBalance() - amount);
    }

    @Override
    public double calculateInterest() {
        return getBalance() * INTEREST_RATE;
    }
}
