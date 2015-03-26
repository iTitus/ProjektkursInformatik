package projektkurs.io.config.property;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class ConfigProperty<T> {

    public static final Comparator<? super ConfigProperty<?>> COMPARATOR = new Comparator<ConfigProperty<?>>() {

        @Override
        public int compare(ConfigProperty<?> o1, ConfigProperty<?> o2) {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.getKey(), o2.getKey());
        }

    };
    protected final String comment;
    protected final T defaultValue;
    protected final String key;
    protected T value;

    public ConfigProperty(String key, String comment, T defaultValue) {
        this.key = key;
        this.comment = comment;
        this.defaultValue = defaultValue;
        value = defaultValue;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof ConfigProperty<?> && key != null && key.equalsIgnoreCase(((ConfigProperty<?>) obj).key);
    }

    public List<String> getAllComments() {
        List<String> list = new ArrayList<String>();
        list.add(getComment());
        list.add("Default: " + defaultValue);
        return list;
    }

    public String getComment() {
        return comment;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    public abstract void parseValue(String value);

    public void setValue(T value) {
        this.value = value;
    }

}
