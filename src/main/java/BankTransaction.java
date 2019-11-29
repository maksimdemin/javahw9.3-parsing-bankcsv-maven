public class BankTransaction {

    private String descriptionTransaction;
    private double incomeAmount;
    private double outcomeAmount;

    BankTransaction(String descriptionTransaction, double outcomeAmount, double incomeAmount) {
        this.descriptionTransaction = descriptionTransaction;
        this.incomeAmount = incomeAmount;
        this.outcomeAmount = outcomeAmount;

    }

    public String getDescriptionTransaction() {
        return descriptionTransaction;
    }


    public double getOutcomeAmount() {
        return outcomeAmount;
    }


    public double getIncomeAmount() {
        return incomeAmount;
    }


}
