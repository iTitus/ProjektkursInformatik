package projektkurs.io.config;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import projektkurs.util.Pair;

public class ConfigReader {

    public static final String ASSIGNMENT = "=";
    public static final String CATEGORY_CLOSE = "}";
    public static final String CATEGORY_OPEN = "{";
    public static final String COMMENT_START = "#";
    private static final Pattern CATEGORY_CLOSE_PATTERN = Pattern.compile("^\\" + CATEGORY_CLOSE + "((\\s)*)(" + COMMENT_START + "(?<comment>.*))?$");
    private static final Pattern CATEGORY_OPEN_PATTERN = Pattern.compile("^(?<name>[\\S^" + COMMENT_START + ASSIGNMENT + "\\" + CATEGORY_OPEN + "\\" + CATEGORY_CLOSE + "]{1,})((\\s)?)\\" + CATEGORY_OPEN + "((\\s)*)(" + COMMENT_START + "(?<comment>.*))?$");
    private static final Pattern LINE_PATTERN = Pattern.compile("^((\\s)*)(?<content>(?<key>[\\S^" + COMMENT_START + ASSIGNMENT + "\\" + CATEGORY_OPEN + "\\" + CATEGORY_CLOSE + "]{1,})((\\s)?)" + ASSIGNMENT + "((\\s)?)(?<value>[\\S^" + COMMENT_START + ASSIGNMENT + "\\" + CATEGORY_OPEN + "\\" + CATEGORY_CLOSE
            + "]{1,}))((\\s)*)(" + COMMENT_START + "(?<comment>.*))?$");
    private static final Pattern ONLY_CONTENT_PATTERN = Pattern.compile("^((\\s)*)(?<content>(?<key>[\\S^" + COMMENT_START + ASSIGNMENT + "\\" + CATEGORY_OPEN + "\\" + CATEGORY_CLOSE + "]{1,})((\\s)?)" + ASSIGNMENT + "((\\s)?)(?<value>[\\S^" + COMMENT_START + ASSIGNMENT + "\\" + CATEGORY_OPEN + "\\" + CATEGORY_CLOSE
            + "]{1,}))((\\s)*)$");

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
        Map<String, List<String>> filteredLines = filter(lines);
        Map<String, List<Pair<String, String>>> configMap = mapContent(filteredLines);
        config.read(configMap);
        config.writeConfig();

    }

    private static Map<String, List<String>> filter(List<String> lines) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        if (lines != null && lines.size() > 0) {
            String category = null;
            List<String> categoryLines = null;
            for (String s : lines) {
                if (s != null && s.length() > 0) {
                    if (category != null && categoryLines != null) {
                        Matcher m = LINE_PATTERN.matcher(s);
                        if (m.matches()) {
                            String content = m.group("content");
                            if (content != null && content.length() > 0) {
                                categoryLines.add(content.trim());
                            }
                        }
                        m = CATEGORY_CLOSE_PATTERN.matcher(s);
                        if (m.matches()) {
                            map.put(category, categoryLines);
                            category = null;
                            categoryLines = null;
                        }
                    } else {
                        Matcher m = CATEGORY_OPEN_PATTERN.matcher(s);
                        if (m.matches()) {
                            category = m.group("name");
                            if (category != null && category.length() > 0) {
                                category = category.trim();
                                categoryLines = new ArrayList<String>();
                            } else {
                                category = null;
                            }
                        }
                    }
                }
            }
        }
        return map;
    }

    private static Map<String, List<Pair<String, String>>> mapContent(Map<String, List<String>> filteredLines) {
        Map<String, List<Pair<String, String>>> map = new HashMap<String, List<Pair<String, String>>>();
        if (filteredLines != null && filteredLines.size() > 0) {
            for (Entry<String, List<String>> entry : filteredLines.entrySet()) {
                if (entry != null) {
                    String category = entry.getKey();
                    if (category != null && category.length() > 0) {
                        List<String> lines = entry.getValue();
                        if (lines != null && lines.size() > 0) {
                            List<Pair<String, String>> pairList = new ArrayList<Pair<String, String>>();
                            for (String s : lines) {
                                if (s != null && s.length() > 0) {
                                    Matcher m = ONLY_CONTENT_PATTERN.matcher(s);
                                    if (m.matches()) {
                                        String key = m.group("key");
                                        String value = m.group("value");
                                        if (key != null && value != null && key.length() > 0 && value.length() > 0) {
                                            pairList.add(new Pair<String, String>(key.trim(), value.trim()));
                                        }
                                    }
                                }
                            }
                            map.put(category, pairList);
                        }
                    }
                }
            }
        }
        return map;
    }
}
