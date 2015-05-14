package projektkurs.io.storage;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import projektkurs.io.Directories;
import projektkurs.util.Logger;

public class SaveIO {

	private static final Saves EMPTY_SAVES = new Saves();
	private static final String SAVES_FILE_NAME = "saves.xml";

	public static Saves loadSaves() {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Saves.class, Save.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			File savesFile = new File(Directories.getSavesDir(), SAVES_FILE_NAME);
			if (!savesFile.exists()) {
				Marshaller marshaller = jaxbContext.createMarshaller();
				marshaller.marshal(EMPTY_SAVES, savesFile);
			}

			return (Saves) unmarshaller.unmarshal(savesFile);
		} catch (JAXBException e) {
			Logger.logThrowable("Unable to load saves", e);
		}
		return null;
	}

	public static void writeSaves(Saves saves) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Saves.class, Save.class);
			Marshaller marshaller = jaxbContext.createMarshaller();

			File savesFile = new File(Directories.getSavesDir(), SAVES_FILE_NAME);
			marshaller.marshal(saves, savesFile);
		} catch (JAXBException e) {
			Logger.logThrowable("Unable to save saves", e);
		}
	}

}
