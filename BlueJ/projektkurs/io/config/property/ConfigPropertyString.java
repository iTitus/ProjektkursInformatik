package projektkurs.io.config.property;

public class ConfigPropertyString extends ConfigProperty<String> {

    public ConfigPropertyString(String key, String defaultValue) {
        this(key, null, defaultValue);
    }

    public ConfigPropertyString(String key, String comment, String defaultValue) {
        super(key, comment, defaultValue);
    }

    @Override
    public void parseValue(String value) {
        if (value != null && !value.isEmpty()) {
            setValue(value);
        }
    }

}
