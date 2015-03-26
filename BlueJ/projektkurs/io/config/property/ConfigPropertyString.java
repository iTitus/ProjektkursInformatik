package projektkurs.io.config.property;

public class ConfigPropertyString extends ConfigProperty<String> {

    public ConfigPropertyString(String key, String comment, String defaultValue) {
        super(key, comment, defaultValue);
    }

    @Override
    public void parseValue(String value) {
        setValue(value);
    }

}
