import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;

public final class TransactionParser {

//    private static final String PATH_TO_CSV = "src/main/resources/movementList.csv";

    File file = new File(
            Main.class.getClassLoader().getResource("movementList.csv").getFile()
    );

    public TransactionParseResult parse() throws IOException {
        TransactionParseResult transactionParseResult = new TransactionParseResult();
        List<BankTransaction> transactions = transactionParseResult.getTransactions();
        List<String> invalidLines = transactionParseResult.getInvalidLines();

        Reader readFromFileCSV = new FileReader(file);

        Iterable<CSVRecord> lines = CSVFormat.DEFAULT.withSkipHeaderRecord().withFirstRecordAsHeader().parse(readFromFileCSV);
        for (CSVRecord line : lines) {
            if (line.size() != 8) {
                invalidLines.add("Invalid line N: " + (line.getRecordNumber() + 1));
                continue;
            }
            if(!line.get(6).replaceAll("\\\"", "").matches("\\d+(?:[.,]\\d+)?") || !line.get(7).replaceAll("\\\"", "").matches("\\d+(?:[.,]\\d+)?") ) {
                invalidLines.add("Invalid line N: " + (line.getRecordNumber() + 1));
                continue;
            }
            BigDecimal incomeAmount = new BigDecimal(line.get(6).replaceAll("\\,", "\\."));
            BigDecimal expenseAmount = new BigDecimal((line.get(7).replaceAll("\\,", "\\.")));
            transactions.add(new BankTransaction(getInfoContractor(line), expenseAmount, incomeAmount, getMCCCode(line)));
            }
        return transactionParseResult;
    }


    public String getInfoContractor(CSVRecord line) {
        String[] descriptionSplitted = line.get(5).substring(20)
                .substring(0,40).trim()
                .replaceAll("/", "\\\\")
                .replaceAll("_", " ")
                .replaceAll(">MOSCOW| GOOGLE", "")
                .split("\\\\");
        return descriptionSplitted[descriptionSplitted.length - 1].trim();
    }


    public String getMCCCode(CSVRecord line) {
        String MCC = line.get(5).substring(106)
                .replaceAll("\\(Apple Pay-7666\\) ", "")
                .trim();
        return MCC.trim();
    }

}
