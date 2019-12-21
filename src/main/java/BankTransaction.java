import java.math.BigDecimal;
import java.time.LocalDate;

public class BankTransaction {

    private LocalDate date;
    private String descriptionTransaction;
    private String contractor;

    private BigDecimal incomeAmount;
    private BigDecimal expenseAmount;
    private String MCCCode;
    private TypeTransaction typeTransaction;
    BigDecimal bdNil = BigDecimal.ZERO;


    BankTransaction(String descriptionTransaction, BigDecimal expenseAmount, BigDecimal incomeAmount, String MCCCode) {
        this.descriptionTransaction = descriptionTransaction;
        this.expenseAmount = expenseAmount;
        this.incomeAmount = incomeAmount;

        if (incomeAmount.compareTo(bdNil) > 0 && expenseAmount.equals(bdNil)) {
            typeTransaction = TypeTransaction.INCOME;
        }
        if (expenseAmount.compareTo(bdNil) > 0 && incomeAmount.equals(bdNil)) {
            typeTransaction = TypeTransaction.EXPENSE;
        }

        this.MCCCode = MCCCode;
    }

    public String getDescriptionTransaction() {
        return descriptionTransaction;
    }

    public BigDecimal getExpenseAmount() {
        return expenseAmount;
    }

    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    public String getContractor() {
        return contractor;
    }

    enum TypeTransaction {
        INCOME,
        EXPENSE
    }

    public TypeTransaction getTypeTransaction() {
        return typeTransaction;
    }

    public String getMCCCode() {
        return MCCCode;
    }

}
