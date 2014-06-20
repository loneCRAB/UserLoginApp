package core.config;


import core.config.util.IniFileUtil;
import core.dao.util.CRUDLoader;

import java.io.File;
import java.util.HashMap;

/**
 * Клас-фабрика для об'єктів типу {@link Configuration},
 * виступає також в ролі зховища для всіх конфігурацій
 */
public class ConfigFactory {

    /**
     * Містить всі конфігурації,
     * Ключ - шлях до файлів конфігурації
     */
    private static HashMap<String, Configuration> filePathMap = new HashMap<String, Configuration>();

    /**
     * Містить всі конфігурації,
     * Ключ - префікси уонфігурвцій
     */
    private static HashMap<String, Configuration> prefixMap = new HashMap<String, Configuration>();

    /**
     * Здійснює пошук конфігураціі, якщо вона ще не завантажена,
     * в іншому випадку завантажує з файлу конфігураціїї
     *
     * @param file файл конфігурації(*.ini)
     * @return конфігурацію
     * @see {@link ConfigFactory#getConfig(String)}
     */
    public static Configuration getConfig(File file) {
        String path = file.getPath();
        Configuration config = filePathMap.get(path);
        if (config != null)
            return config;
        else
            config = new Configuration(file.getPath());
        if (path.endsWith(".ini"))
            config = IniFileUtil.readConfig(config, file);
        else if (path.endsWith("users.xml"))
            CRUDLoader.load(file);
        else
            prefixMap.put(config.getPrefix(), config);
        filePathMap.put(path, config);
        return config;
    }

    /**
     * Здійснює пошук конфігураціїї по її префіксу
     *
     * @param prefix префікс конфігурації
     * @return конфігурацію
     * @see {@link ConfigFactory#getConfig(java.io.File)}
     */
    public static Configuration getConfig(String prefix) {
        return prefixMap.get(prefix);
    }


}
