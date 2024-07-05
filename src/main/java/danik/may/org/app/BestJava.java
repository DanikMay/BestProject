package danik.may.org.app;

import danik.may.org.converter.JsonConverter;
import danik.may.org.entity.Characters;
import danik.may.org.entity.Person;
import danik.may.org.loader.FileLoader;

public class BestJava {
    public static void main(String[] args) {
        System.out.println("Its best project");
        FileLoader fileLoader = new FileLoader();
        String value = fileLoader.load();

        JsonConverter jsonConverter = new JsonConverter();
        Characters characters = jsonConverter.convertData(value);
        System.out.println(value);
        for (Person person : characters.getCharacters()) {
            System.out.println(person.getSpecialization());
        }
        System.out.println(value);
    }
}
