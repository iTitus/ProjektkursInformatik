package projektkurs.io.storage;

import java.io.File;

public class Save {

    private String name;
    private final File saveDir;

    public Save(File saveDir) {
        this.saveDir = saveDir;
        loadSave();
    }

    public String getName() {
        return name;
    }

    private void loadSave() {
        name = saveDir.getName();
    }
}
