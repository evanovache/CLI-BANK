import java.util.*;
import java.util.stream.*;

public class BankService {
    private Map<Long, Account> accounts;

    BankService() {
    accounts = new HashMap<>();
}
    public void addAccount(Account account) {
    accounts.put(account.getAccountNumber(), account);
    }

    public Account findAccount(long accountNumber) throws AccountNotFoundException {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return account;
    }

    public void deposit(long accountNumber, double amount) throws AccountNotFoundException, InvalidInputException {
        Account account = findAccount(accountNumber);
        account.updateBalance(amount);
        account.addTransaction(new Transaction(amount, TransactionType.DEPOSIT));
    }

    public void withdraw(long accountNumber, double amount)
        throws AccountNotFoundException, InsufficientFundsException, InvalidInputException {
            Account account = findAccount(accountNumber);

            if (account instanceof SavingsAccount) {
                SavingsAccount temp = (SavingsAccount) account;
                temp.withdraw(amount);

        } else if (account instanceof CurrentAccount) {
                CurrentAccount temp = (CurrentAccount) account;
                temp.withdraw(amount);
        }
        account.addTransaction(new Transaction(amount, TransactionType.WITHDRAWAL));
    }

    public boolean validate (long accountNumber, int pin) {
        Account account = accounts.get(accountNumber);
        if (account == null) {
            return false;
        }
        return account.verifyPin(pin);
    }

    public List<Transaction> getMiniStatement(long accountNumber) throws AccountNotFoundException{
        Account account = findAccount(accountNumber);

        return account.getTransactions()
                    .stream()
                    .sorted((a, b) ->b.getTimeOfTransaction().compareTo(a.getTimeOfTransaction()))
                    .limit(5)
                    .collect(Collectors.toList());
    }

}

