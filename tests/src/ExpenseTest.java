import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import org.joda.money.*;

/**
 * The class to test the system for expenses.
 *
 * @author Alex Ivanov - 22404782
 */
class ExpenseTest {

    /**
     * Tests creating a valid Expense object.
     *
     * @see Expense
     */
    @Test
    void testExpense() {

        LocalDate date = LocalDate.parse("2022-09-23");
        String description = "Flight to Glasgow";
        Expense.Category category = Expense.Category.TRAVEL_AND_SUBSISTENCE;
        Money amount = Money.parse("EUR 270.59");

        Expense e = new Expense(date, description, category, amount);

        assertNotNull(e);
        assertEquals(date, e.getDate());
        assertEquals(description, e.getDescription());
        assertEquals(category, e.getCategory());
        assertEquals(amount, e.getAmount());
        assertFalse(e.isApproved());
    }

    /**
     * Tests creating and utilising a valid ExpensesPortal object.
     *
     * @see ExpensesPortal
     */
    @Test
    public void testExpensesPortal() {
        ExpensesPortal portal  = new ExpensesPortal();
        Expense e = new Expense(LocalDate.parse("2022-09-23"), "Flight to Glasgow", Expense.Category.TRAVEL_AND_SUBSISTENCE, Money.parse("EUR 270.59"));

        portal.addExpense(e);
        String printedExpense = portal.printExpenses((List<Expense> expenses) -> e.toString());


        assertNotNull(e);
        assertEquals(printedExpense, e.toString());
    }

    /**
     * Tests the ExpensesPortal object with a lambda expression to mock the ExpensePrinter arg.
     */
    @Test
    public void testLambdaPrintExpense() {
        ExpensesPortal portal  = new ExpensesPortal();
        Expense e1 = new Expense(LocalDate.parse("2022-09-23"), "Flight to Glasgow", Expense.Category.TRAVEL_AND_SUBSISTENCE, Money.parse("EUR 270.59"));
        Expense e2 = new Expense(LocalDate.parse("2022-09-20"), "Dell 17-inch monitor", Expense.Category.EQUIPMENT, Money.parse("USD 540.00"));
        Expense e3 = new Expense(LocalDate.parse("2022-09-21"), "Java for Dummies", Expense.Category.OTHER, Money.parse("EUR 17.99"));

        portal.addExpense(e1);
        portal.addExpense(e2);
        portal.addExpense(e3);

        String expectedOutput =
                "2022-09-23: Flight to Glasgow - TRAVEL_AND_SUBSISTENCE - EUR 270.59\n" +
                "2022-09-20: Dell 17-inch monitor - EQUIPMENT - USD 540.00\n" +
                "2022-09-21: Java for Dummies - OTHER - EUR 17.99";

        String printedExpense = portal.printExpenses((List<Expense> expenses) -> {
            StringBuilder sb = new StringBuilder();
            for (Expense e : expenses) {
                sb.append(e.toString());
                sb.append("\n");
            }
            return sb.toString().trim();
        });

        assertEquals(expectedOutput, printedExpense);
    }

    /**
     * Tests the ExpensesPortal object with an anonymous inner class to mock the ExpensePrinter arg.
     */
    @Test
    public void testAnonPrintExpense() {
        ExpensesPortal portal  = new ExpensesPortal();
        Expense e1 = new Expense(LocalDate.parse("2022-09-23"), "Flight to Glasgow", Expense.Category.TRAVEL_AND_SUBSISTENCE, Money.parse("EUR 270.59"));
        Expense e2 = new Expense(LocalDate.parse("2022-09-20"), "Dell 17-inch monitor", Expense.Category.EQUIPMENT, Money.parse("USD 540.00"));
        Expense e3 = new Expense(LocalDate.parse("2022-09-21"), "Java for Dummies", Expense.Category.OTHER, Money.parse("EUR 17.99"));

        portal.addExpense(e1);
        portal.addExpense(e2);
        portal.addExpense(e3);

        String expectedOutput =
                "2022-09-23: Flight to Glasgow - TRAVEL_AND_SUBSISTENCE - EUR 270.59\n" +
                        "2022-09-20: Dell 17-inch monitor - EQUIPMENT - USD 540.00\n" +
                        "2022-09-21: Java for Dummies - OTHER - EUR 17.99";

        StringBuilder sb = new StringBuilder();
        portal.printExpenses(new ExpensePrinter() {
            @Override
            public String print(List<Expense> expenses) {
                for (Expense e : expenses) {
                    sb.append(e.toString());
                    sb.append("\n");
                }
                return sb.toString();
            }
        });

        assertEquals(expectedOutput, sb.toString().trim());
        assertEquals(Money.parse("EUR 769.18"), portal.sumExpenses());
    }

    /**
     * Tests the PrinterByLabel object, where the expense list is sorted by its category.
     */
    @Test
    public void testPrinterByLabel() {
        ExpensesPortal portal  = new ExpensesPortal();
        Expense e1 = new Expense(LocalDate.parse("2022-09-23"), "Flight to Glasgow", Expense.Category.TRAVEL_AND_SUBSISTENCE, Money.parse("EUR 270.59"));
        Expense e2 = new Expense(LocalDate.parse("2022-09-20"), "Dell 17-inch monitor", Expense.Category.EQUIPMENT, Money.parse("USD 540.00"));
        Expense e3 = new Expense(LocalDate.parse("2022-09-21"), "Java for Dummies", Expense.Category.OTHER, Money.parse("EUR 17.99"));

        portal.addExpense(e1);
        portal.addExpense(e2);
        portal.addExpense(e3);

        PrinterByLabel labelledPrinter = new PrinterByLabel();
        String labelledOutput = portal.printExpenses(labelledPrinter);

        String expectedOutput = "TRAVEL_AND_SUBSISTENCE\n" +
                "2022-09-23: Flight to Glasgow - TRAVEL_AND_SUBSISTENCE - EUR 270.59\n\n" +
                "EQUIPMENT\n" +
                "2022-09-20: Dell 17-inch monitor - EQUIPMENT - USD 540.00\n\n" +
                "OTHER\n" +
                "2022-09-21: Java for Dummies - OTHER - EUR 17.99";

        assertEquals(expectedOutput, labelledOutput.trim());
    }
}