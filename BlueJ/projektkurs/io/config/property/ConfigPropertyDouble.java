package projektkurs.io.config.property;

public class ConfigPropertyDouble extends ConfigPropertyWithBounds<Double> {

    public ConfigPropertyDouble(String key, String comment, Double defaultValue) {
        this(key, comment, defaultValue, Double.MIN_VALUE, Double.MAX_VALUE);
    }

    public ConfigPropertyDouble(String key, String comment, Double defaultValue, Double minValue, Double maxValue) {
        super(key, comment, defaultValue, minValue, maxValue);
    }

    @Override
    public void parseValue(String value) {
        try {
            setValue(Double.valueOf(value));
        } catch (NumberFormatException e) {
            // NO-OP
        }
    }

}