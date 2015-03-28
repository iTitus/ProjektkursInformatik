package projektkurs.io.config.property;

public class ConfigPropertyDouble extends ConfigPropertyWithBounds<Double> {

    public ConfigPropertyDouble(String key, Double defaultValue) {
        this(key, null, defaultValue);
    }

    public ConfigPropertyDouble(String key, Double defaultValue, Double minValue, Double maxValue) {
        this(key, null, defaultValue, minValue, maxValue);
    }

    public ConfigPropertyDouble(String key, String comment, Double defaultValue) {
        this(key, comment, defaultValue, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    public ConfigPropertyDouble(String key, String comment, Double defaultValue, Double minValue, Double maxValue) {
        super(key, comment, defaultValue, minValue, maxValue);
    }

    @Override
    public void parseValue(String value) {
        if (value != null && !value.isEmpty()) {
            try {
                setValue(Double.valueOf(value));
            } catch (NumberFormatException e) {
                // NO-OP
            }
        }
    }

}
