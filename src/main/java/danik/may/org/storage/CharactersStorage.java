package danik.may.org.storage;

import danik.may.org.converter.JsonConverter;
import danik.may.org.entity.Characters;
import danik.may.org.loader.FileLoader;

public class CharactersStorage {
    private FileLoader fileLoader = new FileLoader();
    private String value = fileLoader.load();

    private JsonConverter jsonConverter = new JsonConverter();
    private Characters characters = jsonConverter.convertData(value);

    public Characters getStorage() {
        return characters;
    }
}
