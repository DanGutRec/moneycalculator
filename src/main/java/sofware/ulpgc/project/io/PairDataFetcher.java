package sofware.ulpgc.project.io;

import java.io.IOException;
import java.io.InputStream;

public interface PairDataFetcher {
    public InputStream fetch(String url, String from, String to) throws IOException;
}
