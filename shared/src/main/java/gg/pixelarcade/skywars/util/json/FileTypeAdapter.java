package gg.pixelarcade.skywars.util.json;

import xyz.refinedev.lib.gson.*;

import java.io.File;
import java.lang.reflect.Type;

public class FileTypeAdapter implements JsonSerializer<File>, JsonDeserializer<File> {
    public File deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return new File(json.getAsString());
    }

    public JsonElement serialize(File src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getPath());
    }
}
