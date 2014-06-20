package core.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Об'єкт користувача, який використовуеться у базі данних {@link core.dao.UserDAO}
 */
public class User {

    private long ID;
    private String photo;
    private String mail;
    private String password;
    private String name;
    private String firstName;
    private String surname;
    private Calendar birthday;


    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthday() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        return sdf.format(birthday.getTime());
    }

    public void setStringBirthday(String birthday) {
        String[] buf = birthday.split(" ");
        this.birthday = new GregorianCalendar(Integer.parseInt(buf[2]), Integer.parseInt(buf[1]) - 1, Integer.parseInt(buf[0]));
    }

    public void setBirthday(int year, int month, int day) {
        this.birthday = new GregorianCalendar(year, month - 1, day);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
