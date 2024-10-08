import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.joda.money.*;

import static org.junit.jupiter.api.Assertions.*;

class ExpenseTest {

    @Test
    void testExpense() {

        LocalDate date = LocalDate.parse("2022-09-23");
        String description = "Flight to Glasgow";
        Expense.Category category = Expense.Category.TRAVEL_AND_SUBSISTENCE;
        Money amount = Money.parse("EUR 270.59");

        Expense e = new Expense(date, description, category, amount);

        assertEquals(date, e.getDate());
        assertEquals(description, e.getDescription());
        assertEquals(category, e.getCategory());
        assertEquals(amount, e.getAmount());
        assertFalse(e.isApproved());
    }

    @Test
    public void testExpensesPortal() {
        ExpensesPortal portal  = new ExpensesPortal();
        Expense e = new Expense(LocalDate.parse("2022-09-23"), "Flight to Glasgow", Expense.Category.TRAVEL_AND_SUBSISTENCE, Money.parse("EUR 270.59"));

        List<Expense> expenses = new ArrayList<>();
        expenses.add(e);
        portal.addExpense(e);


        portal.print(expenses);

    }

    @Test
    public void testLambdaPrintExpense() {
        ExpensesPortal portal  = new ExpensesPortal();
        Expense e1 = new Expense(LocalDate.parse("2022-09-23"), "Flight to Glasgow", Expense.Category.TRAVEL_AND_SUBSISTENCE, Money.parse("EUR 270.59"));
        Expense e2 = new Expense(LocalDate.parse("2022-09-20"), "Dell 17-inch monitor", Expense.Category.EQUIPMENT, Money.parse("USD 540.00"));
        Expense e3 = new Expense(LocalDate.parse("2022-09-21"), "Java for Dummies", Expense.Category.OTHER, Money.parse("EUR 17.99"));

        portal.addExpense(e1);
        portal.addExpense(e2);
        portal.addExpense(e3);

        ExpensePrinter printer = (List<Expense> expenses) -> {
            for (Expense e : expenses) {
                System.out.println(e);
            }
        };
    }

    @Test
    public void testAnonPrintExpense() {
        ExpensesPortal portal  = new ExpensesPortal();
        Expense e1 = new Expense(LocalDate.parse("2022-09-23"), "Flight to Glasgow", Expense.Category.TRAVEL_AND_SUBSISTENCE, Money.parse("EUR 270.59"));
        Expense e2 = new Expense(LocalDate.parse("2022-09-20"), "Dell 17-inch monitor", Expense.Category.EQUIPMENT, Money.parse("USD 540.00"));
        Expense e3 = new Expense(LocalDate.parse("2022-09-21"), "Java for Dummies", Expense.Category.OTHER, Money.parse("EUR 17.99"));

        portal.addExpense(e1);
        portal.addExpense(e2);
        portal.addExpense(e3);

        portal.printExpenses(new ExpensePrinter() {
            @Override
            public void print(List<Expense> expenses) {
                for (Expense e : expenses) {
                    System.out.println(e);
                }
            }
        });

        assertEquals(portal.sumExpenses(), Money.parse("EUR 769.18") );

    }

    @Test
    public void testPrinterByLabel() {
        ExpensesPortal portal  = new ExpensesPortal();
        Expense e1 = new Expense(LocalDate.parse("2022-09-23"), "Flight to Glasgow", Expense.Category.TRAVEL_AND_SUBSISTENCE, Money.parse("EUR 270.59"));
        Expense e2 = new Expense(LocalDate.parse("2022-09-20"), "Dell 17-inch monitor", Expense.Category.EQUIPMENT, Money.parse("USD 540.00"));
        Expense e3 = new Expense(LocalDate.parse("2022-09-21"), "Java for Dummies", Expense.Category.OTHER, Money.parse("EUR 17.99"));

        portal.addExpense(e1);
        portal.addExpense(e2);
        portal.addExpense(e3);

        PrinterByLabel labelledprinter = new PrinterByLabel();
        portal.printExpenses(labelledprinter);
    }
}