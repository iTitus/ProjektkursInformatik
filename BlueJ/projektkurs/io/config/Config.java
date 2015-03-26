package projektkurs.io.config;

import java.io.File;
import java.io.IOException;

import projektkurs.io.Directories;

public class Config {

    private final File configFile;

    public Config(String configName) {
        configFile = new File(Directories.getConfigDir(), configName + ".cfg");
    }

    public File getConfigFile() {
        return configFile;
    }

    public void readConfig() throws IOException {
        ConfigReader.readConfig(this);
    }

}
