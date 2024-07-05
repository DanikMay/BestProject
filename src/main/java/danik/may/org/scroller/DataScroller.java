package danik.may.org.scroller;

import danik.may.org.entity.Person;
import danik.may.org.storage.CharactersStorage;

import java.util.List;

public class DataScroller {
    private CharactersStorage charactersStorage = new CharactersStorage();
    private List<Person> characters = charactersStorage.getStorage().getCharacters();

    private int currentIndex = 0;
    private int size = characters.size() - 1;

    public void chooseNextPerson() {
        if (currentIndex < size) {
            currentIndex++;
        } else {
            currentIndex = 0;
        }
    }

    public void choosePreviousPerson() {
        if (currentIndex > 0) {
            currentIndex--;
        } else {
            currentIndex = size;
        }
    }

    public Person getCurrentPerson() {
        return characters.get(currentIndex);
    }
}
