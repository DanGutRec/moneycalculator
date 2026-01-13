package sofware.ulpgc.project.io;

import java.io.IOException;
import java.io.InputStream;

public interface DataFetcher {
    InputStream fetch(String url) throws IOException;
}
