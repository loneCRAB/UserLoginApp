package core.dao;

import core.config.Configuration;
import core.model.User;
import core.dao.util.CRUDLoader;

import java.io.File;
import java.util.*;

/**
 * Клас, що реалізує інтерфейс CRUD {@link core.dao.UserDAOInterface},
 * забеспечуэ роботу з елементами моделі
 */
public class UserDAO implements UserDAOInterface {

    /**
     * Приватний об*єкт даного класа,
     * необхідний для реалізації шаблона Singelton
     */
    private static UserDAO DAO = new UserDAO();

    /**
     * Метод знаходження екземпляра даного класа
     *
     * @return екземпляр даного класа
     * @see {@link core.dao.UserDAO#DAO}
     */
    public static UserDAO getInstance() {
        return DAO;
    }

    /**
     * Конфігурація
     */
    private Configuration config;

    /**
     * Основне зховище ссилок на всі об'єкти
     */
    private HashMap<String, User> map = new HashMap<String, User>();

    /**
     * Пустий конструктор, що забороняє створення
     * нових екземплярів даного класу {@link core.dao.UserDAO}
     */
    private UserDAO() {
    }

    /**
     * Метод "відкривання" бази,
     * під час його виконання, здійснюється
     * зчитування всіх об*єктів з файлів, шляхи
     * яких містить конфігурація
     *
     * @param config конфігурація з файлами
     */
    @Override
    public void open(Configuration config) {
        this.config = config;
        CRUDLoader.load(new File(config.get("base-users")));
    }

    /**
     * Метод закриття бази,
     * виконує збереження конфігурації, і
     * всіх об*єктів, що знаходились в
     * {@link core.dao.UserDAO#DAO}
     */
    @Override
    public void close() {
        Set<Object> keys = config.getKeys();

        for (Object key : keys) {
            if (key.toString().startsWith("base")) {
                String filePath = config.get(key.toString());
                CRUDLoader.save(new File(filePath));
            }
        }
        System.out.println("base closed");
        config.save();
    }

    /**
     * Метод додавання об*екту класу {@link core.model.User}
     * в головне зховище({@link core.dao.UserDAO#DAO})
     */
    @Override
    public void create(User user) {
        if (!map.containsValue(user)) {
            Long id = (long) map.size() + 1;
            user.setID(id);
            map.put(user.getMail(), user);
//            System.out.println("insert" + map.get(user.getMail()));
        }
    }

    /**
     * Метод повернення коллекції об*єктів
     *
     * @return повертає об'єкти у вигляді коллекції
     */
    @Override
    public ArrayList<User> getAllUsers() {
        return new ArrayList<User>(map.values());
    }

    /**
     * Метод повернення об*єкту
     *
     * @param user клас шуканого об*єкту
     * @return шуканий об*єкт, якщо нема в базі, то null
     */
    @Override
    public User read(User user) {
        if (!map.containsKey(user.getMail()))
            return null;
        System.out.println("reading " + map.get(user.getID()));
        return map.get(user.getID());
    }

    /**
     * Метод повернення об*єкту по ключу
     *
     * @param mailKey ключ у сховищі
     * @return шуканий об*єкт, якщо нема в базі, то null
     */
    @Override
    public User read(String mailKey) {
        if (map.containsKey(mailKey)) {
            return map.get(mailKey);
        } else
            return null;
    }

    /**
     * Метод оновлює об'єкт у сховищі
     *
     * @param user  об'єкт який оновлюется
     * @param user2 новий об'єкт
     */
    @Override
    public void update(User user, User user2) {
        if (map.containsValue(user) && user.getMail().equals(user2.getMail())) {
            map.replace(user.getMail(), user, user2);
        }
    }

    /**
     * Метод видалення екземпяра невідомого класа
     */
    @Override
    public void delete(User user) {
        if (map.get(user) != null)
            map.remove(user.getMail());
    }


}
