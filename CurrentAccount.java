public class CurrentAccount extends Account{
    private final double OVERDRAFT_LIMIT = -500.0;

    CurrentAccount(String accountHolderName, double balance, String password) {
        super(accountHolderName, balance, password);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException, InvalidInputException {
        if (getBalance() - amount < OVERDRAFT_LIMIT) {
            throw new InsufficientFundsException("Withdraw Unsuccessful. Overdraft limit exceeded");
        }
        updateBalance(getBalance() - amount);        
    }

    @Override
    public double calculateInterest() {
        return 0;
    }
}
