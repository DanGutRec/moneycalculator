package sofware.ulpgc.project.application;

import sofware.ulpgc.project.io.PairDataFetcher;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ExchangeRateFetch implements PairDataFetcher {

    @Override
    public InputStream fetch(String url, String from, String to)  {
        return getInputStream(url+"pair/"+from+"/"+to);
    }
    private InputStream getInputStream(String url)  {
        try {
            return createUrl(url).getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URLConnection createUrl(String url) {
        try {
            return new URL(url).openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
