package core.test;

import core.dao.UserDAO;
import core.model.User;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by aleck_000 on 27.04.14.
 */
public class UserDAOTest {

    public static void main(String[] args) throws ParserConfigurationException, SAXException {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

        user1.setMail("Aleck94@gmail.com");
        user1.setPassword("pawww131");
        user1.setSurname("Гончаренко");
        user1.setName("Александр");
        user1.setFirstName("Русланович");
        user1.setBirthday(1995, 03, 24);

//        user1.setStringBirthday("24 07 1995");
//        System.out.println(user1.getBirthday());
//
        user2.setMail("Dmt@mail.ru");
        user2.setPassword("rtyuehe");
        user2.setSurname("Сопиженко");
        user2.setName("Сергей");
        user2.setFirstName("Олегович");
        user2.setBirthday(1994, 03, 14);

        System.out.println(user2.getBirthday());

        UserDAO.getInstance().create(user1);
        UserDAO.getInstance().create(user2);
//        UserDAO.getInstance().create(user3);
//
//        UserDAO.getInstance().read(user1);
//        UserDAO.getInstance().read(user3);
//
//
//        System.out.println("REMOVING");
//        UserDAO.getInstance().delete(user2);
//        UserDAO.getInstance().read(user2);

//        CRUDLoader CRUD = new CRUDLoader();
//        CRUD.save("users.xml");


    }


}
