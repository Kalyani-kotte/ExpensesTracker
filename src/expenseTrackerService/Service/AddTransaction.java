package expenseTrackerService.Service;

import expenseTrackerService.Transaction;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Getter
public class AddTransaction {
    private final List<Transaction> transactions = new ArrayList<>();
    private double balance = 0.0;

    private final Scanner scanner = new Scanner(System.in);

    public void addTransaction() {
        System.out.println("Add New Transaction");

        System.out.print("Type (Income/Expense): ");
        String type = scanner.nextLine().trim();

        if (!"Income".equalsIgnoreCase(type) && !"Expense".equalsIgnoreCase(type)) {
            System.out.println("Invalid type. Use 'Income' or 'Expense'.");
            return;
        }

        System.out.print("Amount: ");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        Transaction transaction = new Transaction(description, amount, type);
        transactions.add(transaction);

        if (transaction.isIncome()) {
            balance += amount;
            System.out.println("Added Income: " + amount + ". New balance: " + balance);
        } else {
            balance -= amount;
            System.out.println("Added Expense: " + amount + ". New balance: " + balance);
        }

        System.out.println("Transaction added: " + transaction);
    }
   public double getAmount(){
        return getBalance();
   }
    public double getBalance() {
        return transactions.stream()
                .mapToDouble(t -> t.isIncome() ? t.getAmount() : -t.getAmount())
                .sum();
    }

    public double getExpenses() {
        return transactions.stream()
                .filter(t -> !t.isIncome())
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
    public void printExpenses() {
        System.out.println("Added Expenses: " + getExpenses());
    }
    public double getIncome() {
        return transactions.stream()
                .filter(Transaction::isIncome)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }
    public void printIncome() {
        System.out.println("Added Income: " + getIncome());
    }


    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public Transaction findTransactionByUuid(String uuid) {
        return transactions.stream()
                .filter(t -> t.getUuid().equals(uuid))
                .findFirst()
                .orElse(null);
    }



    }
