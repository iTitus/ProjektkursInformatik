package projektkurs.io.storage;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Save {

    private String displayName, folderName;

    public String getDisplayName() {
        return displayName;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void start() {
        // TODO
    }

}
