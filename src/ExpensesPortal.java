import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.*;

/**
 * An ExpensesPortal object to manage a list of expenses.
 *
 * <p> Has methods to add an Expense to the list, print the list and find the total in EUR </p>
 *
 * @author Alex Ivanov 22404782
 */
public class ExpensesPortal implements ExpensePrinter{
    /**
     * List of expenses.
     */
    private List<Expense> expenses;
    /**
     * The exchange rate from USD to EUR.
     */
    public double usdToEurExchangeRate = 0.89; // Gives a total to equal to EUR 769.18

    /**
     * Constructs an ExpensesPortal object.
     */
    public ExpensesPortal() {
        expenses = new ArrayList<Expense>();
    }

    /**
     * Adds Expense to the expenses list.
     * @param expense
     */
    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    /**
     * Takes an ExpensePrinter as an arg to print the list.
     * @param printer
     * @return A call to the print() method
     */
    public String printExpenses(ExpensePrinter printer) {
        return printer.print(expenses);
    }

    /**
     * Calculates the sum of all expenses, converting non-EUR amounts to EUR.
     *
     * @return the total amount in EUR
     */
    public Money sumExpenses() {
        Money total = Money.zero(CurrencyUnit.EUR);
        for (Expense expense : expenses) {
            if (expense.getAmount().getCurrencyUnit() != CurrencyUnit.EUR) {
                total = total.plus(expense.getAmount().convertedTo(CurrencyUnit.EUR, BigDecimal.valueOf(usdToEurExchangeRate), RoundingMode.HALF_UP));
            }
            else {
                total = total.plus(expense.getAmount());
            }
        }
        return total;
    }

    /**
     * The implementation of the print() method from ExpensePrinter
     * @param expenses
     * @return A formatted String of the list of Expenses
     */
    @Override
    public String print(List<Expense> expenses) {
        StringBuilder sb = new StringBuilder();
        for (Expense e : expenses) {
            sb.append(e);
        }
        return sb.toString();
    }
}
