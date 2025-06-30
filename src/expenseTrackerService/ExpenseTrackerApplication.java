
package expenseTrackerService;

import expenseTrackerService.Service.AddTransaction;
import expenseTrackerService.Service.DeleteTransactions;
import expenseTrackerService.Service.UpdateTransactions;
import expenseTrackerService.Service.ViewTransaction;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class ExpenseTrackerApplication {
    public static void main(String[] args) {
        // Initialize service classes
        AddTransaction addTransactions = new AddTransaction();
        ViewTransaction viewTransactions = new ViewTransaction(addTransactions);
        UpdateTransactions updateTransactions = new UpdateTransactions(addTransactions.getTransactions());
        DeleteTransactions deleteTransactions = new DeleteTransactions(viewTransactions);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transactions");
            System.out.println("3. Update Transaction");
            System.out.println("4. Delete Transaction");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    addTransactions.addTransaction();
                    break;
                case 2:
                    viewTransactions.displayTransactions();
                    break;
                case 3:
                    updateTransactions.updateTransaction();
                    break;
                case 4:
                    System.out.print("Enter the UUID of the transaction to delete: ");
                    String uuid = scanner.nextLine().trim();
                    deleteTransactions.deleteTransaction(uuid);
                    break;
                case 5:
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 5.");
            }
        }
    }
}