import java.math.BigDecimal;
import java.time.LocalDate;

public class BankTransaction {

    private LocalDate date;
    private String descriptionTransaction;
    private String contractor;

    private BigDecimal incomeAmount;
//    private double incomeAmountDouble;

    private BigDecimal expenseAmount;
//    private double expenseAmountDouble;


    private String MCCCode;
    private TypeTransaction typeTransaction;
    BigDecimal bdNull = new BigDecimal(0);


    BankTransaction(String descriptionTransaction, BigDecimal expenseAmount, BigDecimal incomeAmount, String MCCCode) {
//      BankTransaction(String descriptionTransaction, double expenseAmountDouble, double incomeAmountDouble) {
        this.descriptionTransaction = descriptionTransaction;

        this.expenseAmount = expenseAmount;
//          this.expenseAmountDouble = expenseAmountDouble;

        this.incomeAmount = incomeAmount;
//          this.incomeAmountDouble = incomeAmountDouble;

        if (incomeAmount.compareTo(new BigDecimal("0.0")) > 0 && expenseAmount.equals(new BigDecimal("0.0"))) {
            typeTransaction = TypeTransaction.INCOME;
        }
//        if (incomeAmountDouble > 0 && expenseAmountDouble == 0)
//            typeTransaction = TypeTransaction.INCOME;

        if (expenseAmount.compareTo(new BigDecimal("0.0")) > 0 && incomeAmount.equals(new BigDecimal("0.0"))) {
            typeTransaction = TypeTransaction.EXPENSE;
        }
//        if (expenseAmountDouble > 0 && incomeAmountDouble == 0)
//            typeTransaction = TypeTransaction.EXPENSE;

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
