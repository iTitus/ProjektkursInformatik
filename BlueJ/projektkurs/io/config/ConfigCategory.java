package projektkurs.io.config;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import projektkurs.io.config.property.ConfigProperty;

public final class ConfigCategory {

    public static final Comparator<? super ConfigCategory> COMPARATOR = new Comparator<ConfigCategory>() {

        @Override
        public int compare(ConfigCategory o1, ConfigCategory o2) {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
        }

    };
    private final String comment;
    private final String name;
    private final Set<ConfigProperty<?>> properties;

    public ConfigCategory(String name, String comment) {
        this.name = name.trim();
        this.comment = comment;
        properties = new TreeSet<ConfigProperty<?>>(ConfigProperty.COMPARATOR);
    }

    public void addProperty(ConfigProperty<?> property) {
        properties.add(property);
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof ConfigCategory && name != null && name.equalsIgnoreCase(((ConfigCategory) obj).name);
    }

    public String getComment() {
        return comment;
    }

    public ConfigProperty<?> getConfigCategory(String key) {
        if (key != null && key.length() > 0) {
            for (ConfigProperty<?> property : properties) {
                if (property != null && key.equalsIgnoreCase(property.getKey())) {
                    return property;
                }
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public Set<ConfigProperty<?>> getProperties() {
        return properties;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "ConfigCategory[" + name + "]";
    }

}
