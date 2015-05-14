package projektkurs.io.storage;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import projektkurs.util.StringUtil;

@XmlRootElement
public class Saves {

	private List<Save> saves = new ArrayList<Save>();

	public Save createNewSave(String name) {
		if (StringUtil.isNullOrEmpty(name)) {
			return null;
		}
		String folderName = getFolderName(name);
		Save s = new Save();
		s.setFolderName(folderName);
		s.setDisplayName(name);
		saves.add(s);
		return s;
	}

	public int getNumberOfSaves() {
		return saves.size();
	}

	public Save getSave(int index) {
		return saves.get(index);
	}

	public Save getSaveForName(String name) {
		if (name != null) {
			for (Save s : saves) {
				if (s != null && name.equals(s.getFolderName())) {
					return s;
				}
			}
		}
		return null;
	}

	@XmlElement(name = "save")
	public List<Save> getSaves() {
		return saves;
	}

	public void setSaves(List<Save> saves) {
		this.saves = saves;
	}

	private String getFolderName(String name) {
		String newName = name;
		newName = name.replace(" ", "_");
		while (getSaveForName(newName) != null) {
			newName += "_";
		}
		return newName;
	}
}
