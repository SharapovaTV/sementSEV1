package web.repository;

import web.model.User;

import java.util.List;

public interface UserDao {
    User getUserByName(String username);

    List<User> allUsers();
    void add(User user);
    void delete(Long id);
    void edit(User user);
    User getUserById(Long id);
}
