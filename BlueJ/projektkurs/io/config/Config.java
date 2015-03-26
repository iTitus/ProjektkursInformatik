package projektkurs.io.config;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import projektkurs.io.Directories;
import projektkurs.io.config.property.ConfigProperty;
import projektkurs.util.Pair;

public class Config {

    private final Set<ConfigCategory> categories;
    private final File configFile;
    private final String configName;

    public Config(String configName) {
        this.configName = configName;
        configFile = new File(Directories.getConfigDir(), configName + ".cfg");
        categories = new TreeSet<ConfigCategory>(ConfigCategory.COMPARATOR);
    }

    public void addCategory(ConfigCategory category) {
        categories.add(category);
    }

    public Set<ConfigCategory> getCategories() {
        return categories;
    }

    public ConfigCategory getConfigCategory(String categoryName) {
        if (categoryName != null && categoryName.length() > 0) {
            for (ConfigCategory category : categories) {
                if (category != null && categoryName.equalsIgnoreCase(category.getName())) {
                    return category;
                }
            }
        }
        return null;
    }

    public File getConfigFile() {
        return configFile;
    }

    public String getConfigName() {
        return configName;
    }

    public void read(Map<String, List<Pair<String, String>>> configMap) {
        if (configMap != null && configMap.size() > 0) {
            for (Entry<String, List<Pair<String, String>>> entry : configMap.entrySet()) {
                if (entry != null) {
                    String category = entry.getKey();
                    List<Pair<String, String>> list = entry.getValue();
                    if (list != null && list.size() > 0) {
                        ConfigCategory configCategory = getConfigCategory(category);
                        if (configCategory != null) {
                            for (Pair<String, String> pair : list) {
                                if (pair != null) {
                                    String key = pair.getValueA();
                                    String value = pair.getValueB();
                                    if (key != null && value != null && key.length() > 0 && value.length() > 0) {
                                        ConfigProperty<?> property = configCategory.getConfigCategory(key);
                                        if (property != null) {
                                            property.parseValue(value);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void readConfig() throws IOException {
        ConfigReader.readConfig(this);
    }

    public void writeConfig() throws IOException {
        ConfigWriter.writeConfig(this);
    }

}
