import java.util.*;

public class Main {
    public static void main(String[] args) {
        
        BankArt style = new BankArt();
        style.welcome();

        BankService bank = new BankService();

        Scanner in = new Scanner(System.in);

        
        int choice = 0;

        System.out.print("Press Enter To Continue:");
        in.nextLine();

        while(true) {

            try {

            clearScreen();
            System.out.println("*****APEX BANKING******");
            System.out.println("1. Create an account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter a number: ");
            while (true) {
            try {
            choice = in.nextInt();   
            if (choice >= 1 && choice <= 3)
                break;
            else
                System.out.print("Choice invalid! (1 - 3): ");
            } catch (InputMismatchException e) {
                System.out.print("Input Invalid!! Try again: ");
                in.nextLine();
            }
            }

            switch (choice) {

                case 1:
                    clearScreen();
                    System.out.println("****Create An Account****");
                    System.out.print("Enter name: ");
                    in.nextLine();
                    String name = in.nextLine();
                    
                    System.out.println("1. Savings Account \n2. Current Account");
                    int accountType = 0;
                    while (true) {
                    try {
                    accountType = in.nextInt();

                    if (accountType >= 1 && accountType <=2)
                        break;
                    else
                        System.out.println("Choice Invalid!! (1-2)");
                    } catch (InputMismatchException e) {
                        System.out.print("Input Invalid!! Try again: ");
                        in.nextLine();
                    }
                    }
                  
                    System.out.print("Enter Initial Deposit: ");
                    double initialDeposit = 0.0;
                    while (true) {
                    try {
                    initialDeposit = in.nextDouble();
                    if (initialDeposit >= 50.0)
                        break;
                    else 
                        System.out.println("Initial Deposit should be $50.00 or more.");
                    }  catch (InputMismatchException e) {
                        System.out.print("Input invalid!! Try again: ");
                        in.nextLine();
                    }
                    }
                    in.nextLine();
                    System.out.print("Enter password: ");

                    String password = in.nextLine();

                    Account account;
                    if (accountType == 1)
                    account = new SavingsAccount(name, initialDeposit, password);
                    else
                        account = new CurrentAccount(name, initialDeposit, password);

                    bank.addAccount(account);
                    System.out.println("Account Created!!!\nYour account number is: " + account.getAccountNumber());
                    System.out.print("Press Enter to continue: ");
                    in.nextLine();
                    break;


                case 2:
                    System.out.print("Enter your account number: ");
                    long accountNo = 0;
                    while (true) {
                    try {
                    accountNo = in.nextLong();
                    break;
                    } catch (InputMismatchException e) {
                        System.out.print("Input Invalid!!! Try again: ");
                        in.nextLine();
                    }
                    }
                    in.nextLine();
                    System.out.print("Enter your password: ");

                    password = in.nextLine();

                    if (bank.validatePassword(accountNo, password)) {
                        while (true) {

                        try {
                            
                        Account acc = bank.findAccount(accountNo);
                        if (acc.getPin() == 0) {
                            clearScreen();
                            System.out.print("Enter a 4 digit PIN to secure deposits and withdrawals: ");
                            /////////////////////////////////////////////////////////***********TEST */
                            while (true) {
                                try {
                                int pin = in.nextInt();
                                acc.setPin(pin);
                                break;
                                } catch (InvalidFormatException e) {
                                    System.out.println(e.getMessage() + " Try again!!");
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input!!!!");
                                    in.nextLine();
                                }
                            }
                        }

                        clearScreen();
                        System.out.println("Welcome, " + acc.getAccountHolderName());
                        System.out.println("1. Check Balance");
                        System.out.println("2. Deposit");
                        System.out.println("3. Withdrawal");
                        System.out.println("4. Mini-Statement");
                        System.out.println("5. Logout");
                        
                        System.out.print("Enter a number: ");

                        while (true) {
                        try {
                        choice = in.nextInt();
                        if (choice >= 1 && choice <= 5) 
                            break;
                        else
                            System.out.print("Choice Invalid!! (1-5): ");
                        } catch (InputMismatchException e) {
                            System.out.print("Input Invalid!! Try again: ");
                            in.nextLine();
                        }
                        }

                        switch (choice) {

                            case 1: 
                                clearScreen();
                                System.out.println("*******Check Balance********");
                                System.out.print("Enter your PIN: ");
                                int pin = in.nextInt();

                                if (bank.validatePin(accountNo, pin)) {
                                    System.out.println("Your balance is $" + acc.getBalance());
                                    enter();
                                    in.nextLine();
                                    in.nextLine();
                                    break;
                                }
                                else {
                                    System.out.println("Invalid PIN!!");
                                    enter();
                                    in.nextLine();
                                    in.nextLine();
                                    break;
                                }
                        
                            case 2:
                                clearScreen();
                                System.out.println("*********Deposit**********");
                                System.out.print("Enter your PIN: ");
                                pin = in.nextInt();

                                if (bank.validatePin(accountNo, pin)) {
                                    System.out.print("Enter amount you want to deposit: ");
                                    double amount = in.nextDouble();
                                    try {
                                    bank.deposit(accountNo, amount);
                                    System.out.println("An amount of $" + amount + " has been deposited succesfully");
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    enter();
                                    in.nextLine();
                                    in.nextLine();
                                    break;
                                }
                                else {
                                    System.out.println("Invalid PIN!!");
                                    enter();
                                    in.nextLine();
                                    in.nextLine();
                                    break;
                                }

                                
                            case 3: 
                                clearScreen();
                                System.out.println("*********Withdraw**********");
                                System.out.print("Enter your PIN: ");
                                pin = in.nextInt();

                                if (bank.validatePin(accountNo, pin)) {
                                    System.out.print("Enter amount you want to withdraw: ");
                                    double amount = in.nextDouble();
                                    try {
                                        bank.withdraw(accountNo, amount);
                                        System.out.println("An amount of $" + amount + " has been withdrawn successfully");
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                    enter();
                                    in.nextLine();
                                    in.nextLine();
                                    break;
                                }
                                else {
                                    System.out.println("Invalid PIN!!");
                                    enter();
                                    in.nextLine();
                                    in.nextLine();
                                    break;
                                }

                            case 4: 
                                clearScreen();
                                System.out.println("*********Mini-Statement*********");
                                System.out.print("Enter your PIN: ");
                                pin = in.nextInt();

                                if (bank.validatePin(accountNo, pin)) { 
                                    List<Transaction> transactions = bank.getMiniStatement(accountNo);
                                    if (transactions.isEmpty())
                                        System.out.println("There are no transactions");
                                    for (Transaction t : transactions) 
                                        System.out.println(t.getTypeOfTransaction() + " $" + t.getAmount() + 
                                        " " + t.getTimeOfTransaction());
                                    System.out.println();
                                    enter();
                                    in.nextLine();
                                    in.nextLine();
                                    break;
                                }
                                else {
                                    System.out.println("Invalid PIN!!");
                                    enter();
                                    in.nextLine();
                                    in.nextLine();
                                    break;
                                }

                            case 5:
                                break;
                        }
                        if (choice == 5) break;
                    } catch (InputMismatchException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                        }   
                    }
                    else {
                        System.out.println("Wrong account number or PIN");
                    }
                    System.out.print("Press Enter to continue: ");
                    in.nextLine();
                    in.nextLine();
                    break;



                case 3:
                    clearScreen();
                    style.goodbye();
                    in.close();
                    return;
            }
            
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            in.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            in.nextLine();
        }
        } 
    }

    public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
    }

    public static void enter () {
        System.out.print("Press Enter To Continue: ");
    }
}
