import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.*;

public class ExpensesPortal implements ExpensePrinter{

    private List<Expense> expenses;
    public double usdToEurExchangeRate = 0.85;

    public ExpensesPortal() {
        expenses = new ArrayList<Expense>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void printExpenses(ExpensePrinter printer) {

    }

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

    @Override
    public void print(List<Expense> expenses) {
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }
}
