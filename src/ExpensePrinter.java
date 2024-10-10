import java.util.List;

/**
 * @author Alex Ivanov - 22404782
 * An interface of the ExpensePrinter.
 * <p> Implementing this interface should have input args that take a list of
 * @see Expense and returns a String. </p>
 *
 * @Param {@Link Expense} objects
 * @return Neat, formatted String of the list
 */
public interface ExpensePrinter {
    public String print(List<Expense> expenses);
}
