package models.dao.implementation;

import models.dao.interfaces.UserDao;
import models.pojos.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.sql.SQLException;
import java.util.List;

public class UserImplementation implements UserDao {

    private final Sql2o sql2o;

    public UserImplementation(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    //Add User
    @Override
    public void addUser(User user) {

        String sql = "INSERT INTO users(name,email,password) VALUES(:name, :email, :password);";
        try(Connection conn = sql2o.open()){
            int id = (int) conn.createQuery(sql,true)
                    .bind(user)
                    .executeUpdate()
                    .getKey();

            user.setId(id);
        }catch(Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }

    //Get All Users
    @Override
    public List<User> getAllUsers() {

        String sql = "SELECT * FROM users";
        try(Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                       .executeAndFetch(User.class);
        }
    }

    //Delete User
    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM users WHERE id = :id";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("id",id)
                    .executeUpdate();
        }catch(Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }

    //Authenticate User
    @Override
    public User authenticate(String email, String password) {
        String sql = "SELECT * FROM USERS WHERE password = :password AND email = :email";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("password", password)
                    .addParameter("email",email)
                    .executeAndFetchFirst(User.class);
        }
    }


    //Find User
    @Override
    public User findUserById(int id) {
        String sql = "SELECT FROM users WHERE id = :id";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                       .addParameter("id",id)
                       .executeAndFetchFirst(User.class);
        }
    }


    //Update User
    @Override
    public void updateUser(int id, String name, String email, String password) {
        String sql = "UPDATE users SET(name, email, password) = (:name, :email, :password) WHERE id = :id";
        try (Connection conn = sql2o.open()){
            conn.createQuery(sql)
                    .addParameter("name",name)
                    .addParameter("email", email)
                    .addParameter("password", password)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch(Sql2oException ex){
            System.out.println(ex.getMessage());
        }
    }
}
