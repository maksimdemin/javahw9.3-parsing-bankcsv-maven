import java.util.ArrayList;
import java.util.List;

public class TransactionParseResult {

    private List<BankTransaction> transactions;
    private List<String> invalidLines;

    public TransactionParseResult(List<BankTransaction> transactions, List<String> invalidLines) {
        this.transactions = transactions;
        this.invalidLines = invalidLines;
    }

    public List<BankTransaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public List<String> getInvalidLines() {
        return new ArrayList<>(invalidLines);
    }



    public boolean isAllLinesValid() {
        return invalidLines.isEmpty();
    }
}


