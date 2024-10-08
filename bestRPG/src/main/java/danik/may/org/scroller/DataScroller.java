package danik.may.org.scroller;

import danik.may.org.entity.Person;
import danik.may.org.map.DataMapper;
import danik.may.org.storage.CharactersStorage;

public class DataScroller {

    private int currentIndex = 0;
    private int size = CharactersStorage.getState().getStorage().getCharacters().size() - 1;

    public void chooseNextPerson(Person personUpdate) {
        DataMapper.updatePerson(CharactersStorage.getState().getStorage().getCharacters().get(currentIndex), personUpdate);
        if (currentIndex < size) {
            currentIndex++;
        } else {
            currentIndex = 0;
        }
    }

    public void choosePreviousPerson(Person personUpdate) {
        DataMapper.updatePerson(CharactersStorage.getState().getStorage().getCharacters().get(currentIndex), personUpdate);
        if (currentIndex > 0) {
            currentIndex--;
        } else {
            currentIndex = size;
        }
    }
    public void chooseCurrentPerson(Person personUpdate) {
        DataMapper.updatePerson(CharactersStorage.getState().getStorage().getCharacters().get(currentIndex), personUpdate);
    }

    public Person getCurrentPerson() {
        return CharactersStorage.getState().getStorage().getCharacters().get(currentIndex);
    }
}
