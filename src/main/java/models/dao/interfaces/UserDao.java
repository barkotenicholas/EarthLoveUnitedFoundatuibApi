package models.dao.interfaces;

import models.pojos.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);
    List<User> getAllUsers();
    void deleteById(int id);
    User authenticate(String email, String password);
    User findUserById(int id);
    void updateUser(int id,String name, String email, String password);
}
