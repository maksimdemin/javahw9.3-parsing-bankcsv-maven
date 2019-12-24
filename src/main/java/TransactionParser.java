import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public final class TransactionParser {

    public TransactionParseResult parse(String path) throws IOException {
        List<BankTransaction> transactions = new ArrayList<>();
        List<String> invalidLines = new ArrayList<>();

        Iterable<CSVRecord> lines = CSVFormat.DEFAULT.withSkipHeaderRecord().withFirstRecordAsHeader().withIgnoreEmptyLines().parse(new FileReader(path));
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
        return new TransactionParseResult(transactions, invalidLines);
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
