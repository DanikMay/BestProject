package danik.may.org;

public class GameSession {
    public static void main(String[] args) {
        CharacterManager characterManager = new CharacterManager();

        characterManager.addCharacter("ВОИН","Удаленький");
        characterManager.addCharacter("РАЗБОЙНИК","Проныра");
        characterManager.addCharacter("МАГ","Зазнайка");

        characterManager.getAllCharacterInfo();
    }
}
