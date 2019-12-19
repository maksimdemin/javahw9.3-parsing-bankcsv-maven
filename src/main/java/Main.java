import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        TransactionParser transactionParser = new TransactionParser();

        TransactionAnalyze.printFullInfoAboutExpense(transactionParser.parse());
        TransactionAnalyze.printFullInfoAboutIncome(transactionParser.parse());
    }

}
