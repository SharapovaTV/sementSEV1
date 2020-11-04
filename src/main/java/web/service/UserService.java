package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    User getUserByName(String username);

    void add(User user);
    void delete(Long id);
    void edit(User user);
    User getUserById(Long id);
    List<User> allUsers();
}
