package projektkurs.io.config.property;

import java.util.List;

import projektkurs.util.StringUtil;

public class ConfigPropertyBoolean extends ConfigProperty<Boolean> {

	public ConfigPropertyBoolean(String key, Boolean defaultValue) {
		this(key, null, defaultValue);
	}

	public ConfigPropertyBoolean(String key, String comment, Boolean defaultValue) {
		super(key, comment, defaultValue);
	}

	@Override
	public List<String> getAllComments() {
		List<String> list = super.getAllComments();
		list.add("Possible Values: " + Boolean.TRUE + " & " + Boolean.FALSE);
		return list;
	}

	@Override
	public void parseValue(String value) {
		if (StringUtil.isNotNullOrEmpty(value)) {
			if (value.equalsIgnoreCase(Boolean.TRUE.toString())) {
				setValue(Boolean.TRUE);
			} else if (value.equalsIgnoreCase(Boolean.FALSE.toString())) {
				setValue(Boolean.FALSE);
			}
		}
	}
}
