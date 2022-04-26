import static spark.Spark.*;

import com.google.gson.Gson;
import models.dao.implementation.UserImplementation;
import models.pojos.Auth;
import models.pojos.User;
import org.sql2o.Sql2o;

import java.util.Objects;


public class App {
    public static void main(String[] args) {
        String connectionString = "jdbc:postgresql://localhost:5432/earth_love";
        Sql2o sql2o = new Sql2o(connectionString,"marvin","nrvnqsr13");
        UserImplementation userImplementation = new UserImplementation(sql2o);
        Gson gson = new Gson();

        //Create new user
        post("/user/new", "application/json" , (request, response) -> {
            User user = gson.fromJson(request.body(),User.class);
            userImplementation.addUser(user);
            response.status(200);
            return gson.toJson("User Created");
        });

        //Authenticate user
        get("/user/login", "application/json",(req,res)->{
            Auth auth = gson.fromJson(req.body(), Auth.class);

            if(userImplementation.authenticate(auth.getEmail(), auth.getPassword()) == null){
                return gson.toJson("User Not Found");
            }

            return gson.toJson(userImplementation.authenticate(auth.getEmail(), auth.getPassword()));
        });

        get("users/all", "application/json",(req,res)->{
            return null;
        });

        patch("user/update", "application/json",(req,res)->{
            return null;
        });

        delete("users/delete/:id", "application/json",(req,res)->{
            return null;
        });

        get("user/:id", "application/json",(req,res)->{
            return null;
        });
    }
}
