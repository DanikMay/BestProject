package danik.may.org;
import java.util.*;

public class CharacterManager {
    List<Character> charactersList = new ArrayList<Character>();
    String WARRIOR = "ВОИН";
    String MAGICIAN = "МАГ";
    String ROGUE = "РАЗБОЙНИК";
    private Character getCharacter(String specialization) {
        return null;
    }
    public void addCharacter(String specialization, String name) {
        if(specialization == WARRIOR) {
            charactersList.add(new Warrior(name));
        } else if (specialization == MAGICIAN) {
            charactersList.add(new Magician(name));
        } else if (specialization == ROGUE) {
            charactersList.add(new Rogue(name));
        }
    }
    public void getAllCharacterInfo() {
        for(Character character: charactersList) {
            character.getInfo();
        }
    }
}
