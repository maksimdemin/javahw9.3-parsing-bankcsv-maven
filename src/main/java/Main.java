import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {

        TransactionParser transactionParser = new TransactionParser();

        try {
//            TransactionAnalyze.printFullInfoAboutExpense(transactionParser.parse());
//            TransactionAnalyze.printFullInfoAboutIncome(transactionParser.parse());
            TransactionAnalyze.printInfoAfterParseFileCSV(transactionParser.parse());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
