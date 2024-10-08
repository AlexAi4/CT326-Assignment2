// Alex Ivanov - 22404782

import java.time.LocalDate;
import org.joda.money.Money;

public class Expense {

    private LocalDate date;
    private String description;
    private Money amount;
    private boolean approved;
    public Category category;

    enum Category {
        TRAVEL_AND_SUBSISTENCE,
        SUPPLIES,
        ENTERTAINMENT,
        EQUIPMENT,
        OTHER
    }

    public Expense(LocalDate date, String description, Category category, Money amount) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.approved = false;
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public Money getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved() {
        this.approved = true;
    }

    @Override
    public String toString() {
        return String.format("%s: %s - %s - %s", date, description, category, amount);
    }

    public static void main(String[] args) {
        Expense e = new Expense(LocalDate.parse("2022-09-20"), "Dell 17-inch monitor", Category.EQUIPMENT, Money.parse("USD 540.00"));
        System.out.println(e);
    }
}