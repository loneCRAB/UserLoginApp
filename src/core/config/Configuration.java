package core.config;

import core.config.util.IniFileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

public class Configuration {

    /**
     * Шлях до файлу, з якого проводиться зчитування даних
     */
    private String filePath;

    /**
     * Рядок, який є префіксом для даної конфігураціїї,
     * всі ключи конфігурацї мають починатися з його,
     * для кожної конфігурації має бути унікальним
     */
    private String prefix;

    /**
     * Об'єкт класа {@link java.util.Properties}, який
     * реалізує зберігання налаштувань
     */
    private Properties properties;

    /**
     * Список конфігурацій, які імпортувались в даній конфігураціїї,
     * Ключом в цьому HashMap, являються аболютні шляхи
     * до файлів цих конфігурацій
     */
    ArrayList<Configuration> includes;

    /**
     * Конструктор, якому передаэться шлях до файлу
     *
     * @param filePath шлях до файлу
     */
    protected Configuration(String filePath) {
        this.filePath = filePath;
        this.prefix = "default";
        includes = new ArrayList<Configuration>();
        properties = new Properties();
    }

    /**
     * Метод вертання налаштування по ключу
     *
     * @param key ключ
     * @return налаштування у вигляді рядка
     */
    public String get(String key) {

        String res = properties.getProperty(key);
        if (res == null)
            res = "null";
        return res;
    }

    /**
     * Метод вертання налаштування по ключу у вигляді чила формату int,
     * у разі помилки {@link NumberFormatException} метод верне 0
     *
     * @param key ключ
     * @return налаштування у вигляді чила формату int
     */
    public int getInt(String key) {
        String res = get(key);

        if (res == "null")
            return 0;

        try {
            return Integer.parseInt(res);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * метож повертання всіх ключів у вигляді Set'a
     *
     * @return set ключів
     * @see {@link java.util.Set}
     */
    public Set<Object> getKeys() {
        return properties.keySet();
    }

    /**
     * Метод повернення префіксу
     *
     * @return префікс у вигляді рядку
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Метод збереження конфігурації
     *
     * @see {@link core.config.util.IniFileUtil} - інструмент зля збереженя/зчитування конфігурацій
     */
    public void save() {

        if (filePath.endsWith(".ini")) {
            IniFileUtil.writeConfig(this, new File(filePath));
        }

        System.out.println(prefix + ": saved");

        for (int i = 0; i < includes.size(); i++)
            includes.get(i).save();
    }

    /**
     * Метод встановлення налаштування по ключу
     *
     * @param key   ключ
     * @param value значення налаштування
     */
    public void set(String key, String value) {
        properties.setProperty(key, value);
    }

    /**
     * Метод встановлення префіксу
     *
     * @param prefix префікс
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Метод повернення вложених конфігурацій
     *
     * @return список конфігурацій
     * @see {@link Configuration#includes}
     */
    public ArrayList<Configuration> getIncludes() {
        return includes;
    }

    /**
     * Метод додавання до списку вложених конфігурацій
     * нової конфігурації
     *
     * @param config нова конфінурація
     */
    public void addConfig(Configuration config) {
        includes.add(config);
    }

    /**
     * Метод повернення шляху до файлу конфігурації
     *
     * @return шлях до файлу конфігурації
     * @see {@link Configuration#filePath}
     */
    public String getFilePath() {
        return filePath;
    }

    public void show() {
        System.out.println("[" + prefix + "]");
        for (Object key : getKeys())
            System.out.println(key + " = " + get(key.toString()));

        for (Configuration c : includes)
            c.show();
    }
}
