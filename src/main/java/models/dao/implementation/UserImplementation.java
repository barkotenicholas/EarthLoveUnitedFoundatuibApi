package models.dao.implementation;

import models.dao.interfaces.UserDao;
import models.pojos.User;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class UserImplementation implements UserDao {

    private final Sql2o sql2o;

    public UserImplementation(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(User user) {

        String sql = "INSERT INTO users(id,name,email,password) VALUES(:id, :name, :email, :password);";
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

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void authenticate(String email, String password) {

    }

    @Override
    public User findUserById(int id) {
        return null;
    }

    @Override
    public void updateUser(int id, String name, String email, String password) {

    }
}
