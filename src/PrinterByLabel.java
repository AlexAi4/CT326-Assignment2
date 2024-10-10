import java.util.ArrayList;
import java.util.List;

/**
 * A Printer used to sort the Expenses in the expense list by the category label.
 *
 * @author Alex Ivanov - 22404782
 */
public class PrinterByLabel implements ExpensePrinter{
    /**
     * The override of print() gets the labels from the current expenses List.
     * @param expenses list
     * @return Formatted String with an {@see Expense} following a category header
     */
    @Override
    public String print(List<Expense> expenses) {
        List<String> labels = new ArrayList<>();
        for (Expense expense : expenses) {
            String label = expense.getCategory().name();
            if (!labels.contains(label)) {
                labels.add(label);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (String label : labels) {
            sb.append(label);
            sb.append("\n");
            for (Expense expense : expenses) {
                if (expense.getCategory() == Expense.Category.valueOf(label.toUpperCase())) {
                    sb.append(expense);
                    sb.append("\n");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
