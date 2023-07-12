package pl.javastart.streamstask;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamTaskTest {

    public static List<User> userListProvider() {
        List<User> users = new ArrayList<>();

        users.add(new User(1L, "Alicja", 20));
        users.add(new User(2L, "Dominik", 15));
        users.add(new User(3L, "Patrycja", 25));
        users.add(new User(4L, "Marcin", 30));
        users.add(new User(5L, "Tomek", 18));
        users.add(new User(6L, "Damian", 26));

        return users;
    }

    @DisplayName("Should return true")
    @Test
    public void shouldReturnTrueForWomenNames() {
        // given
        StreamsTask streamsTask = new StreamsTask();
        List<User> users = userListProvider();

        // when
        Collection<User> result = streamsTask.findWomen(users);

        // then
        assertThat(result.stream()
                .allMatch(user -> user.getName().endsWith("a"))).isTrue();
    }

    @DisplayName("Should return size of 2")
    @Test
    public void shouldReturnTwoForSizeWomenList() {
        // given
        StreamsTask streamsTask = new StreamsTask();
        List<User> users = userListProvider();

        // when
        Collection<User> result = streamsTask.findWomen(users);

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @DisplayName("Should return 22.25")
    @Test
    public void shouldReturnAverageMenAge() {
        // given
        StreamsTask streamsTask = new StreamsTask();
        List<User> users = userListProvider();

        // when
        Double averageMenAge = streamsTask.averageMenAge(users);

        // then
        assertThat(averageMenAge).isEqualTo(22.25);
    }

    @DisplayName("Should return 2 as size of map")
    @Test
    public void shouldReturnTwoForSizeOfMapExpenses() {
        // given
        StreamsTask streamsTask = new StreamsTask();
        List<User> users = userListProvider();

        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(1L, "Buty", new BigDecimal("149.99"), ExpenseType.WEAR));
        expenses.add(new Expense(1L, "Sałatka", new BigDecimal("14.99"), ExpenseType.FOOD));
        expenses.add(new Expense(2L, "Bluza", new BigDecimal("100"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Skarpetki", new BigDecimal("39"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Pizza", new BigDecimal("25"), ExpenseType.FOOD));

        // when
        Map<Long, List<Expense>> expensesByUserId = streamsTask.groupExpensesByUserId(users, expenses);

        // then
        assertThat(expensesByUserId.keySet().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should return 2 expenses for userId 1")
    public void shouldReturnTwoExpensesForUserOne() {
        // given
        StreamsTask streamsTask = new StreamsTask();
        List<User> users = userListProvider();

        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(1L, "Buty", new BigDecimal("149.99"), ExpenseType.WEAR));
        expenses.add(new Expense(1L, "Sałatka", new BigDecimal("14.99"), ExpenseType.FOOD));
        expenses.add(new Expense(2L, "Bluza", new BigDecimal("100"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Skarpetki", new BigDecimal("39"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Pizza", new BigDecimal("25"), ExpenseType.FOOD));

        // when
        Map<Long, List<Expense>> expensesByUserId = streamsTask.groupExpensesByUserId(users, expenses);

        // then
        assertThat(expensesByUserId.get(1L).size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should return 3 expenses for user Dominik")
    public void shouldReturnExpensesForDominik() {
        // given
        StreamsTask streamsTask = new StreamsTask();
        List<User> users = userListProvider();

        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(1L, "Buty", new BigDecimal("149.99"), ExpenseType.WEAR));
        expenses.add(new Expense(1L, "Sałatka", new BigDecimal("14.99"), ExpenseType.FOOD));
        expenses.add(new Expense(2L, "Bluza", new BigDecimal("100"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Skarpetki", new BigDecimal("39"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Pizza", new BigDecimal("25"), ExpenseType.FOOD));

        // when
        Map<User, List<Expense>> expensesByUser = streamsTask.groupExpensesByUser(users, expenses);

        // then
        assertThat(expensesByUser.get(new User(2L, "Dominik", 15)).size()).isEqualTo(3);

    }
}
