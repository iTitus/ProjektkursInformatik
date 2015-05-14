package projektkurs.io.config.property;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import projektkurs.io.config.Config;

public abstract class ConfigProperty<T> {

	protected final String comment;
	protected final T defaultValue;
	protected final String key;
	public static final Comparator<? super ConfigProperty<?>> COMPARATOR = new Comparator<ConfigProperty<?>>() {

		@Override
		public int compare(ConfigProperty<?> o1, ConfigProperty<?> o2) {
			return String.CASE_INSENSITIVE_ORDER.compare(o1.getKey(), o2.getKey());
		}

	};
	protected T value;

	public ConfigProperty(String key, String comment, T defaultValue) {
		this.key = key;
		this.comment = comment;
		this.defaultValue = defaultValue;
		value = defaultValue;
	}

	public ConfigProperty(String key, T defaultValue) {
		this(key, null, defaultValue);
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

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return key.hashCode();
	}

	public abstract void parseValue(String value);

	public void write(PrintWriter writer, int indent) {
		String indentation = "";
		for (int i = 0; i < indent; i++) {
			indentation += "\t";
		}
		List<String> propertyComments = getAllComments();
		if (propertyComments != null && !propertyComments.isEmpty()) {
			writer.println();
			for (String propertyComment : propertyComments) {
				if (propertyComment != null && propertyComment.length() > 0) {
					writer.println(indentation + Config.A_COMMENT_START + " " + propertyComment);
				}
			}
		}
		writer.println(indentation + key + " " + Config.ASSIGNMENT + " " + value);
	}

}
