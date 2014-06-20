package core.dao;

import core.config.Configuration;
import core.model.User;

import java.util.ArrayList;

/**
 * Інтерфейс типу Create-Read-Update-Delete
 */
public interface UserDAOInterface {

    public void             close();
    public void             open(Configuration config);

    public void             create (User object);
    public ArrayList<User>  getAllUsers ();
    public User             read   (User user);
    public User             read   (String mailKey);
    public void             update (User user, User user2);
    public void             delete (User user);

}