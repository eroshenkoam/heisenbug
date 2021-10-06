package io.github.eroshenkoam.heisenbug;

import io.github.eroshenkoam.heisenbug.domain.Todo;
import io.github.eroshenkoam.heisenbug.domain.User;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.function.Consumer;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class RandomUtils {

    public static User randomUser() {
        return randomUser((user) -> {});
    }

    public static User randomUser(Consumer<User> consumer) {
        User user = new User();
        user.setLogin(randomAlphabetic(10));
        user.setPassword(randomAlphabetic(60));
        consumer.accept(user);
        return user;
    }

    public static Todo randomTodo(User user) {
        Todo todo = new Todo();
        todo.setUser(user);
        todo.setTitle(randomAlphabetic(10));
        return todo;
    }

}
