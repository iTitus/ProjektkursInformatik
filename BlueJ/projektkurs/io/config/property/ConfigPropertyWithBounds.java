package projektkurs.io.config.property;

import java.util.List;

public abstract class ConfigPropertyWithBounds<T extends Comparable<T>> extends ConfigProperty<T> {

    protected final T minValue, maxValue;

    public ConfigPropertyWithBounds(String key, String comment, T defaultValue, T minValue, T maxValue) {
        super(key, comment, defaultValue);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public List<String> getAllComments() {
        List<String> list = super.getAllComments();
        list.add("Bounds: [" + minValue + "; " + maxValue + "]");
        return list;
    }

    public T getMaxValue() {
        return maxValue;
    }

    public T getMinValue() {
        return minValue;
    }

    @Override
    public void setValue(T value) {
        super.setValue(value.compareTo(minValue) < 0 || value.compareTo(maxValue) > 0 ? defaultValue : value);
    }

}
