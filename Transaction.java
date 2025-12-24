import java.time.LocalDateTime;

public class Transaction {
    private double amount;
    private LocalDateTime timeOfTransaction;
    private TransactionType typeOfTransaction;

    Transaction(double amount, TransactionType typeOfTransaction) {
        this.amount = amount;
        this.typeOfTransaction = typeOfTransaction;
        timeOfTransaction = LocalDateTime.now();
    }

    public double getAmount() {
        return amount;
    }
    
    public LocalDateTime getTimeOfTransaction() {
        return timeOfTransaction;
    }

    public TransactionType getTypeOfTransaction() {
        return typeOfTransaction;
    }
}
