import java.time.LocalDate;
import org.joda.money.Money;
/**
 * Represents an employee expense claim.
 *
 * @author Alex Ivanov - 22404782
 */
public class Expense {

    /**
     * The purchase date of the expense.
     */
    private LocalDate date;

    /**
     * A description of the expense.
     */
    private String description;

    /**
     * The cost of the expense.
     */
    private Money amount;

    /**
     * Indicates whether the expense has been approved.
     */
    private boolean approved;

    /**
     * The category of the expense.
     */
    public Category category;

    /**
     * Enum representing different categories of expenses.
     */
    public enum Category {
        /**
         * Expenses for any travel and subsistence.
         */
        TRAVEL_AND_SUBSISTENCE,

        /**
         * Expenses for supplies.
         */
        SUPPLIES,

        /**
         * Expenses for entertainment.
         */
        ENTERTAINMENT,

        /**
         * Expenses for equipment.
         */
        EQUIPMENT,

        /**
         * Other expenses.
         */
        OTHER
    }

    /**
     * Constructs a new Expense object with the following inputs.
     * Approved is false by default.
     *
     * @param date      The date of the expense
     * @param description A brief description of the expense
     * @param category   The category of the expense
     * @param amount     The monetary amount of the expense
     */
    public Expense(LocalDate date, String description, Category category, Money amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.approved = false;
        this.category = category;
    }

    /**
     * Gets the date of the expense.
     *
     * @return The LocalDate of the expense
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Gets the description of the expense.
     *
     * @return The String describing the expense
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the cost of the expense.
     *
     * @return The "Money" representing the expense amount
     */
    public Money getAmount() {
        return amount;
    }

    /**
     * Gets the category of the expense.
     *
     * @return The Category enum representing the expense category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Checks if the expense has been approved.
     *
     * @return True if approved, otherwise false
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * Sets the approval status of the expense to true.
     */
    public void setApproved() {
        this.approved = true;
    }

    /**
     * Returns a formatted string representation of the Expense object.
     *
     * @return A formatted string containing date, description, category, and amount
     */
    @Override
    public String toString() {
        return String.format("%s: %s - %s - %s", date, description, category, amount);
    }

    /**
     * Main method.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Expense e = new Expense(LocalDate.parse("2022-09-20"), "Dell 17-inch monitor", Category.EQUIPMENT, Money.parse("USD 540.00"));
        System.out.println(e);
    }
}
