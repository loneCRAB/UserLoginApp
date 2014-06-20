package core.test;

import core.config.ConfigFactory;
import core.config.Configuration;
import core.dao.UserDAO;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

public class ConfigTest {

    public static void main(String[] args) throws ParserConfigurationException, SAXException {

        Configuration app = new ConfigFactory().getConfig(new File("src/core/data/config.ini"));

        UserDAO.getInstance().open(app);
        UserDAO.getInstance().close();

    }


}
