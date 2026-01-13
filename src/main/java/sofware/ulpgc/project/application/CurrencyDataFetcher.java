package sofware.ulpgc.project.application;

import sofware.ulpgc.project.io.DataFetcher;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class CurrencyDataFetcher implements DataFetcher {
    @Override
    public InputStream fetch(String url) throws IOException {
        return getInputSream(createURL(url));
    }

    private InputStream getInputSream(URLConnection url) throws IOException {
        return url.getInputStream();
    }

    private URLConnection createURL(String url)  {
        try {
            return new URL(url).openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
