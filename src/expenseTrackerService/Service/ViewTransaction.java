package expenseTrackerService.Service;
import expenseTrackerService.Transaction;

import java.util.List;

public class ViewTransaction {
 private final AddTransaction addTransaction;

 public ViewTransaction(AddTransaction addTransaction) {
  this.addTransaction = addTransaction;
 }



 public void displayTransactions() {
  List<Transaction> transactions = addTransaction.getTransactions();
  System.out.println("Transaction History:");
  if (transactions.isEmpty()) {
   System.out.println("No transactions recorded.");
  } else {
   for (Transaction t : transactions) {
    System.out.println(t);
   }
  }
  System.out.println("Current Balance: " + addTransaction.getBalance());
 }
}