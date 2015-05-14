package projektkurs.io.config.property;

public class ConfigPropertyInteger extends ConfigPropertyWithBounds<Integer> {

	public ConfigPropertyInteger(String key, Integer defaultValue) {
		this(key, null, defaultValue);
	}

	public ConfigPropertyInteger(String key, Integer defaultValue, Integer minValue, Integer maxValue) {
		this(key, null, defaultValue, minValue, maxValue);
	}

	public ConfigPropertyInteger(String key, String comment, Integer defaultValue) {
		this(key, comment, defaultValue, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public ConfigPropertyInteger(String key, String comment, Integer defaultValue, Integer minValue, Integer maxValue) {
		super(key, comment, defaultValue, minValue, maxValue);
	}

	@Override
	public void parseValue(String value) {
		if (value != null && !value.isEmpty()) {
			try {
				setValue(Integer.valueOf(value));
			} catch (NumberFormatException e) {
				// NO-OP
			}
		}
	}

}
