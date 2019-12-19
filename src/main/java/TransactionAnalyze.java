import java.math.BigDecimal;
import java.util.*;

public class TransactionAnalyze {


    public static void printFullInfoAboutExpense(TransactionParseResult transactions){

        Map<String, BigDecimal> expenseMap = new HashMap<>();
        transactions.getTransactions().stream()
                .filter(b -> b.getTypeTransaction().equals(BankTransaction.TypeTransaction.EXPENSE))
                .forEach(b -> {
                    expenseMap.putIfAbsent(b.getDescriptionTransaction(), new BigDecimal("0.0"));
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
                    expenseMap.putIfAbsent(b.getDescriptionTransaction(), new BigDecimal("0.0"));
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
        BigDecimal sum = new BigDecimal("0.0");
        for (BankTransaction transaction : transactions.getTransactions()) {
            sum = sum.add(transaction.getIncomeAmount());
        }
        return sum;
    }



    public static BigDecimal getTotalExpenseSum(TransactionParseResult transactions) {

        BigDecimal sum = new BigDecimal("0.0");
        for (BankTransaction transaction : transactions.getTransactions()) {
            sum = sum.add(transaction.getExpenseAmount());
        }
        return sum;
    }

}
