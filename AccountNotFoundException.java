public class AccountNotFoundException extends Exception{
    AccountNotFoundException (String message) {
        super(message);
    }
}

class InsufficientFundsException extends Exception{
    InsufficientFundsException (String message) {
        super(message);
    }
}

class InvalidInputException extends Exception{
    InvalidInputException(String message) {
        super(message);
    }
}
