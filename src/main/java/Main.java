import java.nio.file.Path;

public class Main {

    private static final String PATH_TO_FILE_CSV = "src/main/resources/movementList.csv";

    public static void main(String[] args) {

        TransactionParser transactionParser = new TransactionParser();

        try {
//            Path pathToFileCSV = Path.of(Main.class.getResource("movementList.csv").getPath());
            TransactionAnalyze.printInfoAfterParseFileCSV(transactionParser.parse(PATH_TO_FILE_CSV));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
