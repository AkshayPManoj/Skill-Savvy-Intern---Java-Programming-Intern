import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExpenseTracker {

    // Define an Expense class
    static class Expense implements Serializable {
        private static final long serialVersionUID = 1L;
        private double amount;
        private String category;
        private Date date;
        private String notes;

        public Expense(double amount, String category, Date date, String notes) {
            this.amount = amount;
            this.category = category;
            this.date = date;
            this.notes = notes;
        }

        // Getters
        public double getAmount() { return amount; }
        public String getCategory() { return category; }
        public Date getDate() { return date; }
        public String getNotes() { return notes; }

        @Override
        public String toString() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return String.format("Amount: %.2f, Category: %s, Date: %s, Notes: %s",
                    amount, category, dateFormat.format(date), notes);
        }
    }

    // ExpenseManager class to manage a list of expenses
    static class ExpenseManager {
        private List<Expense> expenses;

        public ExpenseManager() {
            expenses = new ArrayList<>();
        }

        public void addExpense(Expense expense) {
            expenses.add(expense);
        }

        public List<Expense> getExpenses() {
            return expenses;
        }

        public double getTotalExpenses() {
            return expenses.stream().mapToDouble(Expense::getAmount).sum();
        }

        public void saveExpenses(String fileName) throws IOException {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
                out.writeObject(expenses);
            }
        }

        public void loadExpenses(String fileName) throws IOException, ClassNotFoundException {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
                expenses = (List<Expense>) in.readObject();
            }
        }
    }

    // Main program logic
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();
        String fileName = "expenses.dat";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Load expenses from file if available
        try {
            manager.loadExpenses(fileName);
            System.out.println("Loaded previous expenses.");
        } catch (Exception e) {
            System.out.println("No previous data found or error loading data.");
        }

        while (true) {
            System.out.println("\n1. Add Expense\n2. View Expenses\n3. View Total Expenses\n4. Save & Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    // Add expense
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();  // consume newline

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();

                    System.out.print("Enter date (dd/MM/yyyy): ");
                    String dateString = scanner.nextLine();
                    Date date;
                    try {
                        date = dateFormat.parse(dateString);
                    } catch (Exception e) {
                        System.out.println("Invalid date format, using current date.");
                        date = new Date();
                    }

                    System.out.print("Enter notes (optional): ");
                    String notes = scanner.nextLine();

                    // Create and add new expense
                    Expense expense = new Expense(amount, category, date, notes);
                    manager.addExpense(expense);
                    System.out.println("Expense added successfully.");
                    break;

                case 2:
                    // View expenses
                    List<Expense> expenses = manager.getExpenses();
                    if (expenses.isEmpty()) {
                        System.out.println("No expenses recorded.");
                    } else {
                        System.out.println("Recorded Expenses:");
                        for (Expense exp : expenses) {
                            System.out.println(exp);
                        }
                    }
                    break;

                case 3:
                    // View total expenses
                    double total = manager.getTotalExpenses();
                    System.out.printf("Total Expenses: %.2f\n", total);
                    break;

                case 4:
                    // Save and exit
                    try {
                        manager.saveExpenses(fileName);
                        System.out.println("Expenses saved successfully.");
                    } catch (IOException e) {
                        System.out.println("Failed to save expenses.");
                    }
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
