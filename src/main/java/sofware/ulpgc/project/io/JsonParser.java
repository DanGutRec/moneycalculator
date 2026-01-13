package sofware.ulpgc.project.io;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import sofware.ulpgc.project.model.Currency;
import java.util.List;

public interface JsonParser {
    public JsonElement parse(InputStream path);
}
