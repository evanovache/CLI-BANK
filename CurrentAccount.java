public class CurrentAccount extends Account{
    private final double OVERDRAFT_LIMIT = -500.0;

    CurrentAccount(String accountHolderName, double balance, int pin) {
        super(accountHolderName, balance, pin);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (getBalance() - amount < OVERDRAFT_LIMIT) {
            throw new InsufficientFundsException("Overdraft limit exceeded");
        }
    }

    @Override
    public double calculateInterest() {
        return 0;
    }
}
