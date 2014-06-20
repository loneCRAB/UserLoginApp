package core.login;

import java.io.Serializable;

/**
 * Клас зберігає данні користувача {@link core.model.User}
 * для автоматичной авторизації
 */
public class UserToken implements Serializable {

    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
