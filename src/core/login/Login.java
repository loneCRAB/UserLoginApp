package core.login;

import core.dao.UserDAO;
import core.model.User;

/**
 * Клас авторизації користувача у базі данних {@link core.dao.UserDAO},
 */
public class Login {

    public static User getLogin(String login, String password) {

        User user = UserDAO.getInstance().read(login);
        if (user.getMail().equals(login) && user.getPassword().equals(password))
            return user;
        return null;
    }

}
