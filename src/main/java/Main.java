import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String PATH_TO_CSV = "src/main/resources/movementList.csv";
    private static List<String> list;

    public static void main(String[] args) {

        listFromCSV(PATH_TO_CSV);

        System.out.println("\nParsing a csv file (bank transactions)\n");
        System.out.println("Sort expenses:");

        try {
            for (int i = 0; i < MCCCode.values().length; i++) {
                printTotalOutcomeOrIncome(MCCCode.values()[i].toString());
            }
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }

        System.out.println("\nTotal income, total outcome:");
        printTotalValueOutcomeAndIncome();


        List<BankTransaction> transactions = parseTransactions();
        System.out.println(transactions.stream().filter(bT -> bT.getIncomeAmount() > 0).mapToDouble(BankTransaction::getIncomeAmount).sum());
        System.out.println(transactions.stream().filter(bT -> bT.getOutcomeAmount() > 0).mapToDouble(BankTransaction::getOutcomeAmount).sum());
        System.out.println(transactions.stream().filter(bankTransaction -> bankTransaction.getDescriptionTransaction()
                .matches(".*" + MCCCode.MCC5812)).mapToDouble(BankTransaction::getOutcomeAmount).sum());


    }


    private static List<String> listFromCSV(String path) {
        list = new ArrayList<>();
        try {
            list = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static boolean choiceOutcomeOrIncome(String mcc) {
        return MCCCode.getMCC(mcc).toString().matches("MCC6536");
    }


    private static void printTotalValueOutcomeAndIncome() {
        double sumIncome = 0;
        double sumOutcome = 0;
        try {
            for (String line : list) {
                String[] columsCSV = line.replaceAll("\\\"", "").split(",", 8);
                if (columsCSV.length != 8) {
                    System.out.println("Wrong line " + line);
                    continue;
                }
                sumIncome += Double.parseDouble(columsCSV[columsCSV.length - 2].replaceAll("\\,", "\\."));
                sumOutcome += Double.parseDouble(columsCSV[columsCSV.length - 1].replaceAll("\\,", "\\."));
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        System.out.printf("Total value Income  = %,.2f rub\n", sumIncome);
        System.out.printf("Total value Outcome = %,.2f rub\n", sumOutcome);
    }


    private static void printTotalOutcomeOrIncome(String mcc) {
        double sum = 0;
        try {
            for (String line : list) {
                String[] columsCSV = line.replaceAll("\\\"", "").split(",", 8);
                if (columsCSV.length != 8) {
                    System.out.println("Wrong line " + line);
                    continue;
                }
                if (columsCSV[columsCSV.length - 3].matches(".*" + MCCCode.getMCC(mcc))) {
                    if (choiceOutcomeOrIncome(mcc)) {
                        sum += Double.parseDouble(columsCSV[columsCSV.length - 2].replaceAll("\\,", "\\."));
                    } else sum += Double.parseDouble(columsCSV[columsCSV.length - 1].replaceAll("\\,", "\\."));
                }
            }
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        boolean isIncomeOrOutcome = choiceOutcomeOrIncome(mcc);
        String textPrintIncomeOrOutcome = isIncomeOrOutcome ? "income" : "outcome";
        System.out.printf("Total value of %s (%s) = %,.2f rub\n", textPrintIncomeOrOutcome, MCCCode.getMCC(mcc).getCategory(), sum);
    }



    private static List<BankTransaction> parseTransactions() {
        List<BankTransaction> transactions = new ArrayList<>();
        for (String line : list) {
            String[] columsCSV = line.replaceAll("\\\"", "").split(",", 8);
            if (columsCSV.length != 8) {
                System.out.println("Wrong line " + line);
                continue;
            }
            transactions.add(new BankTransaction(columsCSV[columsCSV.length - 3],
                    Double.parseDouble(columsCSV[columsCSV.length - 2].replaceAll("\\,", "\\.")),
                    Double.parseDouble(columsCSV[columsCSV.length - 1].replaceAll("\\,", "\\."))));
        }
        return transactions;
    }
}
