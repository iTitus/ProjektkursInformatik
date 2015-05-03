package projektkurs.io.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class SaveLoader implements Callable<List<Save>> {

    @Override
    public List<Save> call() throws Exception {
        List<Save> list = new ArrayList<Save>();
        return list;
    }

}
