package core.dao.util;

import core.model.User;
import core.dao.UserDAO;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

public class CRUDLoader {

    /**
     * Метод заватнтаження
     *
     * @param file файл, з якого буде выдбуватись завантаження
     */
    public static void load(File file) {
        if (file.getPath().endsWith(".xml"))
            try {
                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(file);
                document.getDocumentElement().normalize();

                NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());
                for (int tmp = 0; tmp < nodeList.getLength(); tmp++) {
                    Node node = nodeList.item(tmp);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;
                        User user = new User();
                        user.setID(Long.parseLong(element.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue()));
                        user.setPhoto(element.getElementsByTagName("photo_id").item(0).getChildNodes().item(0).getNodeValue());
                        user.setMail(element.getElementsByTagName("mail").item(0).getChildNodes().item(0).getNodeValue());
                        user.setPassword(element.getElementsByTagName("password").item(0).getChildNodes().item(0).getNodeValue());
                        user.setName(element.getElementsByTagName("name").item(0).getChildNodes().item(0).getNodeValue());
                        user.setFirstName(element.getElementsByTagName("firstName").item(0).getChildNodes().item(0).getNodeValue());
                        user.setSurname(element.getElementsByTagName("surname").item(0).getChildNodes().item(0).getNodeValue());
                        user.setStringBirthday(element.getElementsByTagName("birthday").item(0).getChildNodes().item(0).getNodeValue());

                        UserDAO.getInstance().create(user);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                e.printStackTrace();
            }
        else
            throw new RuntimeException("File with UserDAO must have .xml format");
    }

    /**
     * Метод збереження
     */
    public static void save(File file) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            //Корневой элемент
            Document document = documentBuilder.newDocument();
            Element rootElement = document.createElement("users");
            document.appendChild(rootElement);
            ArrayList<User> users = UserDAO.getInstance().getAllUsers();
            for (User user : users) {
                Element element = document.createElement("user");
                rootElement.appendChild(element);

                Element id = document.createElement("id");
                id.appendChild(document.createTextNode(String.valueOf(user.getID())));
                element.appendChild(id);

                Element photo = document.createElement("photo_id");
                photo.appendChild(document.createTextNode(String.valueOf(user.getPhoto())));
                element.appendChild(photo);

                Element mail = document.createElement("mail");
                mail.appendChild(document.createTextNode(String.valueOf(user.getMail())));
                element.appendChild(mail);

                Element password = document.createElement("password");
                password.appendChild(document.createTextNode(String.valueOf(user.getPassword())));
                element.appendChild(password);

                Element surname = document.createElement("surname");
                surname.appendChild(document.createTextNode(String.valueOf(user.getSurname())));
                element.appendChild(surname);

                Element name = document.createElement("name");
                name.appendChild(document.createTextNode(String.valueOf(user.getName())));
                element.appendChild(name);

                Element firstName = document.createElement("firstName");
                firstName.appendChild(document.createTextNode(String.valueOf(user.getFirstName())));
                element.appendChild(firstName);

                Element birthday = document.createElement("birthday");
                birthday.appendChild(document.createTextNode(String.valueOf(user.getBirthday().toString())));
                element.appendChild(birthday);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "Windows-1251");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(file.getPath()));

            transformer.transform(domSource, streamResult);
//            System.out.println("Файл сохранен!");
        } catch (ParserConfigurationException pce) {
            System.out.println(pce.getLocalizedMessage());
            pce.printStackTrace();
        } catch (TransformerException te) {
            System.out.println(te.getLocalizedMessage());
            te.printStackTrace();

        }

    }
}
