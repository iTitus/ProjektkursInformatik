package projektkurs.io.config.property;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class ConfigPropertyEnum<T extends Enum<T>> extends ConfigProperty<T> {

    private final Class<T> enumClass;
    private final Set<T> possibleValues, whiteList, blackList;

    public ConfigPropertyEnum(String key, Class<T> enumClass, T defaultValue) {
        this(key, null, enumClass, defaultValue);
    }

    public ConfigPropertyEnum(String key, String comment, Class<T> enumClass, T defaultValue) {
        super(key, comment, defaultValue);
        possibleValues = EnumSet.allOf(enumClass);
        whiteList = EnumSet.noneOf(enumClass);
        blackList = EnumSet.noneOf(enumClass);
        this.enumClass = enumClass;

    }

    @SuppressWarnings("unchecked")
    public void addToBlackList(T... enumValues) {
        if (enumValues != null && enumValues.length > 0) {
            blackList.addAll(Arrays.asList(enumValues));
        }
    }

    @SuppressWarnings("unchecked")
    public void addToWhiteList(T... enumValues) {
        if (enumValues != null && enumValues.length > 0) {
            whiteList.addAll(Arrays.asList(enumValues));
        }
    }

    @Override
    public List<String> getAllComments() {
        List<String> list = super.getAllComments();
        Set<T> allowedValues = EnumSet.noneOf(enumClass);
        allowedValues.addAll(possibleValues);
        allowedValues.removeAll(blackList);
        if (!whiteList.isEmpty()) {
            allowedValues.retainAll(whiteList);
        }
        list.add("Possible Values: " + allowedValues);
        return list;
    }

    public Set<T> getBlackList() {
        return blackList;
    }

    public Class<T> getEnumClass() {
        return enumClass;
    }

    public Set<T> getPossibleValues() {
        return possibleValues;
    }

    public Set<T> getWhiteList() {
        return whiteList;
    }

    @Override
    public void parseValue(String value) {
        if (value != null && !value.isEmpty()) {
            T enumValue = null;
            try {
                enumValue = Enum.valueOf(enumClass, value);
            } catch (IllegalArgumentException e) {
                // NO-OP
            }
            if (enumValue != null && possibleValues.contains(enumValue) && !blackList.contains(enumValue) && (whiteList.isEmpty() || whiteList.contains(enumValue))) {
                setValue(enumValue);
            }
        }
    }

}
