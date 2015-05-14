package projektkurs.io.config;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import projektkurs.io.Directories;
import projektkurs.io.config.property.ConfigProperty;
import projektkurs.util.Logger;

public class Config {

	public static final String A_COMMENT_START = "#";
	public static final String ASSIGNMENT = "=";
	public static final String CATEGORY_CLOSE = "}";
	public static final String CATEGORY_OPEN = "{";
	private static final Pattern CATEGORY_CLOSE_PATTERN = Pattern.compile("^\\" + CATEGORY_CLOSE + "((\\s)*)(" + A_COMMENT_START + "(?<comment>.*))?$");
	private static final Pattern CATEGORY_OPEN_PATTERN = Pattern.compile("^(?<name>[\\S^" + A_COMMENT_START + ASSIGNMENT + "\\" + CATEGORY_OPEN + "\\" + CATEGORY_CLOSE + "]{1,})((\\s)?)\\" + CATEGORY_OPEN + "((\\s)*)(" + A_COMMENT_START + "(?<comment>.*))?$");
	private static final Pattern LINE_PATTERN = Pattern.compile("^((\\s)*)(?<content>(?<key>[\\S^" + A_COMMENT_START + ASSIGNMENT + "\\" + CATEGORY_OPEN + "\\" + CATEGORY_CLOSE + "]{1,})((\\s)?)" + ASSIGNMENT + "((\\s)?)(?<value>[\\S^" + A_COMMENT_START + ASSIGNMENT + "\\" + CATEGORY_OPEN + "\\" + CATEGORY_CLOSE
			+ "]{1,}))((\\s)*)(" + A_COMMENT_START + "(?<comment>.*))?$");

	private final Set<ConfigCategory> categories;
	private final File configFile;
	private final String configName;

	public Config(String configName) {
		this.configName = configName;
		configFile = new File(Directories.getConfigDir(), configName + ".cfg");
		categories = new TreeSet<ConfigCategory>(ConfigCategory.COMPARATOR);
	}

	public void addCategory(ConfigCategory category) {
		categories.add(category);
	}

	public Set<ConfigCategory> getCategories() {
		return categories;
	}

	public ConfigCategory getConfigCategory(String categoryName) {
		if (categoryName != null && categoryName.length() > 0) {
			for (ConfigCategory category : categories) {
				if (category != null && categoryName.equalsIgnoreCase(category.getName())) {
					return category;
				}
			}
		}
		return null;
	}

	public File getConfigFile() {
		return configFile;
	}

	public String getConfigName() {
		return configName;
	}

	public void readConfig() {
		try {
			if (!configFile.exists()) {
				configFile.createNewFile();
			}
			List<String> lines = new ArrayList<String>();
			try (Scanner sc = new Scanner(configFile)) {
				while (sc.hasNextLine()) {
					lines.add(sc.nextLine());
				}
			}
			List<ConfigCategory> categoryChain = new ArrayList<ConfigCategory>();
			for (String s : lines) {
				if (s != null && !s.isEmpty()) {
					if (!categoryChain.isEmpty()) {
						Matcher lineMatcher = LINE_PATTERN.matcher(s);
						if (lineMatcher.matches()) {
							String key = lineMatcher.group("key");
							String value = lineMatcher.group("value");
							if (key != null && value != null && key.length() > 0 && value.length() > 0) {
								ConfigCategory currentCategory = categoryChain.get(categoryChain.size() - 1);
								if (currentCategory != null) {
									key = key.trim();
									value = value.trim();
									ConfigProperty<?> property = currentCategory.getConfigProperty(key);
									if (property != null) {
										property.parseValue(value);
									}
								}
							}
						}
						Matcher closeMatcher = CATEGORY_CLOSE_PATTERN.matcher(s);
						if (closeMatcher.matches()) {
							categoryChain.remove(categoryChain.size() - 1);
						}
					}
					Matcher openMatcher = CATEGORY_OPEN_PATTERN.matcher(s);
					if (openMatcher.matches()) {
						String categoryName = openMatcher.group("name");
						if (categoryName != null && !categoryName.isEmpty()) {
							categoryName = categoryName.trim();
							ConfigCategory category = null;
							if (categoryChain.isEmpty()) {
								category = getConfigCategory(categoryName);
							} else {
								ConfigCategory currentCategory = categoryChain.get(categoryChain.size() - 1);
								if (currentCategory != null) {
									category = currentCategory.getChildCategory(categoryName);
								}
							}
							if (category != null) {
								categoryChain.add(category);
							}
						}
					}
				}
			}

			writeConfig();
		} catch (IOException e) {
			Logger.logThrowable("Unable to read config", e);
		}
	}

	public void write(PrintWriter writer) {
		writer.println(A_COMMENT_START + " Configuration file: " + configName);
		for (ConfigCategory category : categories) {
			if (category != null) {
				category.write(writer, 0);
			}
		}
	}

	public void writeConfig() {
		try {
			if (!configFile.exists()) {
				configFile.createNewFile();
			}
			try (PrintWriter writer = new PrintWriter(configFile)) {
				write(writer);
			}
		} catch (IOException e) {
			Logger.logThrowable("Unable to write config", e);
		}
	}

}
