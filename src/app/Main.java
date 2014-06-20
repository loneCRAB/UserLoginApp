package app;

import core.config.ConfigFactory;
import core.config.Configuration;
import core.dao.UserDAO;
import core.login.Util.IOToken;
import core.model.User;
import core.login.UserToken;

import java.io.File;
import java.io.IOException;

/**
 * Выконавчий класс
 */
public class Main {


    public static void main(String[] args) {
        Configuration app = new ConfigFactory().getConfig(new File("src/core/data/config.ini"));
        UserDAO.getInstance().open(app);

        if ((new File(System.getProperty("user.dir") + "/src/app/data/USER.TOKEN")).exists()) {
            try {
                UserToken token = IOToken.readObjects();
                User user = core.login.Login.getLogin(token.getLogin(), token.getPassword());
                if (user != null) {
                    Profile p = new Profile();
                    p.setUser(user);
                } else
                    new Login();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            new Login();

    }


}
