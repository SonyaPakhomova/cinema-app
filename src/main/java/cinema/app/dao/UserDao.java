package cinema.app.dao;

import cinema.app.model.User;
import java.util.Optional;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
