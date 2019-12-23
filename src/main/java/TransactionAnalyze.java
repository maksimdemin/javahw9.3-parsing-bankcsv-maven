import java.math.BigDecimal;
import java.util.*;

public class TransactionAnalyze {


    public static void printInfoAfterParseFileCSV(TransactionParseResult transactions) {
        printFullInfoAboutExpense(transactions);
        printFullInfoAboutIncome(transactions);
        printInvalidLinesFromFileCSV(transactions);
    }

    public static void printFullInfoAboutExpense(TransactionParseResult transactions){

        Map<String, BigDecimal> expenseMap = new HashMap<>();
        transactions.getTransactions().stream()
                .filter(b -> b.getTypeTransaction().equals(BankTransaction.TypeTransaction.EXPENSE))
                .forEach(b -> {
                    expenseMap.putIfAbsent(b.getDescriptionTransaction(), BigDecimal.ZERO);
                    expenseMap.put(b.getDescriptionTransaction(), expenseMap.get(b.getDescriptionTransaction()).add(b.getExpenseAmount()));
                });

        System.out.printf("%-25s %17s%n", "Description", "Expense");
        expenseMap.entrySet().stream().sorted((b1, b2) -> b2.getValue().compareTo(b1.getValue())).forEach((b) -> {
            System.out.println("--------------------------------------------");
            System.out.printf("| %-25s %10.2f RUB |%n", b.getKey(), b.getValue());
        });
        System.out.println("============================================");
        System.out.printf("| %-25s %10.2f RUB |%n", "Total Expense sum", getTotalExpenseSum(transactions));
        System.out.println("============================================");
    }



    public static void printFullInfoAboutIncome(TransactionParseResult transactions){

        Map<String, BigDecimal> expenseMap = new HashMap<>();
        transactions.getTransactions().stream()
                .filter(b -> b.getTypeTransaction().equals(BankTransaction.TypeTransaction.INCOME))
                .forEach(b -> {
                    expenseMap.putIfAbsent(b.getDescriptionTransaction(), BigDecimal.ZERO);
                    expenseMap.put(b.getDescriptionTransaction(), expenseMap.get(b.getDescriptionTransaction()).add(b.getIncomeAmount()));
                });

        System.out.println();
        System.out.printf(" %-25s %16s %n", "Description", "Income");
        expenseMap.entrySet().stream().sorted((b1, b2) -> b2.getValue().compareTo(b1.getValue())).forEach((b) -> {
            System.out.println("--------------------------------------------");
            System.out.printf("| %-25s %10.2f RUB |%n", b.getKey(), b.getValue());
        });
        System.out.println("============================================");
        System.out.printf("| %-25s %10.2f RUB |%n", "Total Income sum", getTotalIncomeSum((transactions)));
        System.out.println("============================================");
    }



    public static BigDecimal getTotalIncomeSum(TransactionParseResult transactions) {
        return transactions.getTransactions().stream()
                .map(BankTransaction::getIncomeAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }



    public static BigDecimal getTotalExpenseSum(TransactionParseResult transactions) {
        return transactions.getTransactions().stream()
                .map(BankTransaction::getExpenseAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static void printInvalidLinesFromFileCSV(TransactionParseResult invalidLines) {
        if (!invalidLines.isAllLinesValid()) {
            System.out.printf("\nFile CSV has %d invalid lines:%n", invalidLines.getInvalidLines().size());
            for (String line: invalidLines.getInvalidLines()) {
                System.out.println(line);
            }
        }
        else
            System.out.println("\nFile CSV has 0 invalid line(s)");
    }
}
