package projektkurs.io.config;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import projektkurs.io.config.property.ConfigProperty;

public class ConfigWriter {

    private static final String ASSIGNMENT = " " + ConfigReader.ASSIGNMENT + " ";
    private static final String CATEGORY_CLOSE = ConfigReader.CATEGORY_CLOSE;
    private static final String CATEGORY_OPEN = " " + ConfigReader.CATEGORY_OPEN;
    private static final String COMMENT_END = " " + ConfigReader.COMMENT_START;
    private static final String COMMENT_START = ConfigReader.COMMENT_START + " ";

    public static void writeConfig(Config config) throws IOException {
        File configFile = config.getConfigFile();
        if (!configFile.exists()) {
            configFile.createNewFile();
        }
        try (PrintWriter writer = new PrintWriter(configFile)) {
            writer.println(COMMENT_START + "Configuration file: " + config.getConfigName());
            writer.println();
            Set<ConfigCategory> categories = config.getCategories();
            if (categories != null && categories.size() > 0) {
                for (ConfigCategory category : categories) {
                    String categoryComment = category.getComment();
                    if (categoryComment != null && categoryComment.length() > 0) {
                        String row = getCommentRow(categoryComment);
                        writer.println(row);
                        writer.println(COMMENT_START + categoryComment + COMMENT_END);
                        writer.println(row);
                    }
                    writer.println(category.getName() + CATEGORY_OPEN);
                    Set<ConfigProperty<?>> properties = category.getProperties();
                    if (properties != null && properties.size() > 0) {
                        int i = 0;
                        for (ConfigProperty<?> property : properties) {
                            if (property != null) {
                                boolean printedComments = false;
                                List<String> propertyComments = property.getAllComments();
                                if (propertyComments != null && propertyComments.size() > 0) {
                                    for (String propertyComment : propertyComments) {
                                        if (propertyComment != null && propertyComment.length() > 0) {
                                            writer.println("\t" + COMMENT_START + propertyComment);
                                            printedComments = true;
                                        }
                                    }
                                }
                                writer.println("\t" + property.getKey() + ASSIGNMENT + property.getValue());
                                if (printedComments && i < properties.size() - 1) {
                                    writer.println();
                                }
                            }
                            i++;
                        }
                    }
                    writer.println(CATEGORY_CLOSE);
                    writer.println();
                }
            }
        }
    }

    private static String getCommentRow(String comment) {
        String s = "";
        for (int i = 0; i < comment.length() + COMMENT_START.length() + COMMENT_END.length(); i++) {
            s += ConfigReader.COMMENT_START;
        }
        return s;
    }
}
