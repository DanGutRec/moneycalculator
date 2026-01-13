package sofware.ulpgc.project.application;

import sofware.ulpgc.project.io.CodesDataFetcher;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class CurrencyCodesDataFetcher implements CodesDataFetcher {
    @Override
    public InputStream fetch(String url)  {
        return getInputSream(createURL(url+"codes"));
    }

    private InputStream getInputSream(URLConnection url)  {
        try {
            return url.getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URLConnection createURL(String url)  {
        try {
            return new URL(url).openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
