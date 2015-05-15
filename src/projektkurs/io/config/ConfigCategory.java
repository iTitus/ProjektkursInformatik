package projektkurs.io.config;

import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import projektkurs.io.config.property.ConfigProperty;

public final class ConfigCategory {

    public static final Comparator<? super ConfigCategory> COMPARATOR = new Comparator<ConfigCategory>() {

        @Override
        public int compare(ConfigCategory o1, ConfigCategory o2) {
            return String.CASE_INSENSITIVE_ORDER.compare(o1.getName(), o2.getName());
        }

    };
    private final Set<ConfigCategory> childCategories;
    private final String comment;
    private final String name;
    private ConfigCategory parent;
    private final Set<ConfigProperty<?>> properties;

    public ConfigCategory(String name) {
        this(name, null);
    }

    public ConfigCategory(String name, String comment) {
        this.name = name.trim();
        this.comment = comment;
        properties = new TreeSet<ConfigProperty<?>>(ConfigProperty.COMPARATOR);
        childCategories = new TreeSet<ConfigCategory>(COMPARATOR);
    }

    public void addChildCategory(ConfigCategory category) {
        if (category != null && category != this && !childCategories.contains(category)) {
            category.parent = this;
            childCategories.add(category);
        }
    }

    public void addProperty(ConfigProperty<?> property) {
        if (property != null && !properties.contains(property)) {
            properties.add(property);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && obj instanceof ConfigCategory && name != null && name.equalsIgnoreCase(((ConfigCategory) obj).name);
    }

    public Set<ConfigCategory> getChildCategories() {
        return childCategories;
    }

    public ConfigCategory getChildCategory(String categoryName) {
        if (categoryName != null && !categoryName.isEmpty() && !childCategories.isEmpty()) {
            for (ConfigCategory childCategory : childCategories) {
                if (childCategory != null && categoryName.equalsIgnoreCase(childCategory.name)) {
                    return childCategory;
                }
            }
        }
        return null;
    }

    public String getComment() {
        return comment;
    }

    public ConfigProperty<?> getConfigProperty(String key) {
        if (key != null && key.length() > 0) {
            for (ConfigProperty<?> property : properties) {
                if (property != null && key.equalsIgnoreCase(property.getKey())) {
                    return property;
                }
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public ConfigCategory getParent() {
        return parent;
    }

    public Set<ConfigProperty<?>> getProperties() {
        return properties;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public boolean hasParent() {
        return parent != null;
    }

    public boolean isParent() {
        return !childCategories.isEmpty();
    }

    @Override
    public String toString() {
        return "ConfigCategory[" + name + "]";
    }

    public void write(PrintWriter writer, int indent) {
        String indentation = "";
        for (int i = 0; i < indent; i++) {
            indentation += "\t";
        }
        writer.println();
        if (comment != null && !comment.isEmpty()) {
            String row = "";
            if (comment != null) {
                for (int i = 0; i < comment.length() + 2 * Config.A_COMMENT_START.length() + 2; i++) {
                    row += Config.A_COMMENT_START;
                }
            }
            writer.println(indentation + row);
            writer.println(indentation + Config.A_COMMENT_START + " " + comment + " " + Config.A_COMMENT_START);
            writer.println(indentation + row);
        }
        writer.println(indentation + name + " " + Config.CATEGORY_OPEN);
        for (ConfigProperty<?> property : properties) {
            if (property != null) {
                property.write(writer, indent + 1);
            }
        }
        if (isParent()) {
            for (ConfigCategory childCategory : childCategories) {
                if (childCategory != null) {
                    childCategory.write(writer, indent + 1);
                }
            }
        }
        writer.println();
        writer.println(indentation + Config.CATEGORY_CLOSE);
    }

}
