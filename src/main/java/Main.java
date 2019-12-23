import java.nio.file.Path;

public class Main {


    public static void main(String[] args) {

        TransactionParser transactionParser = new TransactionParser();

        try {
            Path pathToFileCSV = Path.of(Main.class.getResource("movementList.csv").getPath());
            TransactionAnalyze.printInfoAfterParseFileCSV(transactionParser.parse(pathToFileCSV));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
