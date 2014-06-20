package core.config.util;


import core.config.ConfigFactory;
import core.config.Configuration;

import java.io.*;
import java.util.List;
import java.util.Set;

/**
 * Клас для роботи з Файлами формату "*.ini"
 * <p/>
 * Слід зауважити що ці файли мають не стандартний вигляд.
 * спочатку потрібно вказати префікс конфігурації: "[секція_конфігурації]",
 * файли які імпортуються повинні мати вигляд: "file=шлях_до_файлу_конфігурації",
 * будь-які аргументи мають вигляд: пара - "ключ=значення".
 * Кожен ключ може мати підключі : "ключ.підключ1=значення"
 * <p/>
 * Порожні рядки або рядки які починаються з рядка "//" ігноруються
 *
 * @author Александр Гончаренко
 */
public class IniFileUtil {

    /**
     * writer для запису інформації у файли
     */
    private static BufferedWriter out;

    /**
     * Метод зчитування конфігурації з файлу
     *
     * @param config конфігурація, до якої будуть включені налаштування з цього фалу
     * @param file   файл конфігурації
     * @return конфігурація
     */
    public static Configuration readConfig(Configuration config, File file) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = in.readLine()) != null) {
                if (line.startsWith("//")) continue;
                if (line.startsWith("[") && line.endsWith("]")) {
                    line = line.replace("[", "");
                    line = line.replace("]", "");
                    config.setPrefix(line);
                    continue;
                }
                if (line.contains("=")) {
                    line = line.trim();
                    line = line.replaceAll("\\\\", "/");
                    String[] split = line.split("=");
                    String key = split[0];
                    String value = split[1];
                    if (key.equals("file")) {
                        config.addConfig(ConfigFactory.getConfig(new File(value)));
                    } else
                        config.set(key, value);
                    continue;
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Can't find file " + file.getAbsolutePath()
                    + "\n" + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    /**
     * Метод збереження конфігурації до файлу
     *
     * @param file   файл
     * @param config конфігурація
     */
    public static void writeConfig(Configuration config, File file) {
        try {
            out = new BufferedWriter(new FileWriter(file));
            out.write("[" + config.getPrefix() + "]\n");
            out.write("\n");

            List<Configuration> includes = config.getIncludes();
            for (Configuration c : includes)
                out.write("file=" + c.getFilePath() + "\n");

            out.write("\n");

            Set<Object> keys = config.getKeys();
            for (Object key : keys) {
                out.write(key + "=" + config.get(key.toString()) + "\n");
            }
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
