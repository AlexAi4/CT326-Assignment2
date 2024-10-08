import java.util.ArrayList;
import java.util.List;

public class PrinterByLabel implements ExpensePrinter{

    @Override
    public void print(List<Expense> expenses) {
        List<String> labels = new ArrayList<>();
        for (Expense expense : expenses) {
            String label = expense.getCategory().name();
            if (!labels.contains(label)) {
                labels.add(label);
            }
        }

        for (String label : labels) {
            System.out.println(label);
            for (Expense expense : expenses) {
                if (expense.getCategory() == Expense.Category.valueOf(label.toUpperCase())) {
                    System.out.println("  " + expense + "\n");
                }
            }
        }
    }
}
