package core.login.Util;

import core.login.UserToken;

import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.NoSuchAlgorithmException;

/**
 * Клас для об'єктів типу {@link core.login.UserToken},
 * виступає також в ролі утиліти для збереження та считування
 * серілізованих файлів
 */
public class IOToken {

    /**
     * Записуе серілізований об'єкт до файлу.
     */
    public void writeObjects(UserToken token) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException {
        ObjectOutput oos = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir") + "/src/app/data/USER.TOKEN"));
        oos.writeObject(token);
        oos.flush();
        oos.close();
    }

    /**
     * Десирілізуе файл
     */
    public static UserToken readObjects() throws IOException {
        UserToken token = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir") + "/src/app/data/USER.TOKEN"));
            token = (UserToken) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        } catch (ClassNotFoundException e) {
            e.printStackTrace(System.out);
        } finally {
            if (ois != null)
                ois.close();
        }
        return token;
    }

}
