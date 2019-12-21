import java.util.ArrayList;
import java.util.List;

public class TransactionParseResult {

    private List<BankTransaction> transactions;
    private List<String> invalidLines;

    public TransactionParseResult() {
        transactions = new ArrayList<>();
        invalidLines = new ArrayList<>();
    }

    public List<BankTransaction> getTransactions() {
        return transactions;
    }

    public List<String> getInvalidLines() {
        return invalidLines;
    }
}


