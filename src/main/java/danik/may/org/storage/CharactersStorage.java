package danik.may.org.storage;

import danik.may.org.converter.JsonConverter;
import danik.may.org.entity.Characters;
import danik.may.org.entity.Person;
import danik.may.org.loader.FileManager;

import java.util.List;

public class CharactersStorage {

    private static CharactersStorage charactersStorage = null;

    public static CharactersStorage getState() {
        if (charactersStorage == null) {
            charactersStorage = new CharactersStorage();
        }
        return charactersStorage;
    }

    private FileManager fileLoader = new FileManager();
    private String value = fileLoader.load();

    private JsonConverter jsonConverter = new JsonConverter();
    private Characters characters = jsonConverter.convertData(value);

    public Characters getStorage() {
        return characters;
    }

    public void saveData() {
        String jsonResult = jsonConverter.writeData(characters);
        FileManager fileManager = new FileManager();
        fileManager.save(jsonResult);
        System.out.println(jsonResult);
    }
}
