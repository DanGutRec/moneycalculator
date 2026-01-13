package sofware.ulpgc.project.io;

import java.io.IOException;
import java.io.InputStream;

public interface CodesDataFetcher {
    InputStream fetch(String url) throws IOException;
}
