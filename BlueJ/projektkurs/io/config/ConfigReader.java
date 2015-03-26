package projektkurs.io.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigReader {

    private static final String ASSIGNMENT = "=";
    private static final String COMMENT = "#";
    private static final String COMMENT_REGEX = COMMENT + "(?<comment>.*)";
    private static final String CONTENT_REGEX = "(?<content>(?<key>.[^" + COMMENT + ASSIGNMENT + "]+)" + ASSIGNMENT + "(?<value>.[^" + COMMENT + ASSIGNMENT + "]+))";
    private static final Pattern LINE_PATTERN = Pattern.compile("^" + CONTENT_REGEX + "(" + COMMENT_REGEX + ")?$");
    private static final Pattern ONLY_CONTENT_PATTERN = Pattern.compile("^" + CONTENT_REGEX + "$");

    public static void readConfig(Config config) throws IOException {

        File configFile = config.getConfigFile();
        if (!configFile.exists()) {
            configFile.createNewFile();
        }
        List<String> lines = new ArrayList<String>();
        try (Scanner sc = new Scanner(configFile)) {
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
        }
        lines = filter(lines);
        Map<String, String> configMap = mapContent(lines);
        System.out.println(configMap);
    }

    private static List<String> filter(List<String> lines) {
        List<String> list = new ArrayList<String>();
        if (lines != null && lines.size() > 0) {
            for (String s : lines) {
                if (s != null && s.length() > 0) {
                    Matcher m = LINE_PATTERN.matcher(s);
                    if (m.matches()) {
                        String content = m.group("content");
                        if (content != null && content.length() > 0) {
                            list.add(content.trim());
                        }
                    }
                }
            }
        }
        return list;
    }

    private static Map<String, String> mapContent(List<String> lines) {
        Map<String, String> map = new HashMap<String, String>();
        if (lines != null && lines.size() > 0) {
            for (String s : lines) {
                if (s != null && s.length() > 0) {
                    Matcher m = ONLY_CONTENT_PATTERN.matcher(s);
                    if (m.matches()) {
                        String key = m.group("key");
                        String value = m.group("value");
                        if (key != null && value != null && key.length() > 0 && value.length() > 0) {
                            map.put(key.trim(), value.trim());
                        }
                    }
                }
            }
        }
        return map;
    }
}
