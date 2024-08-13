package danik.may.org.map;

import danik.may.org.entity.Person;

/**
 * Преобразует данные связанные с персонажами
 */
public class DataMapper {

    /**
     * Обновляет данные текущего персонажа полями, которые меняются на экране пользователя
     *
     * @param currentPerson - все данные персонажа открытого на экране
     * @param updatePerson  - данные, которые мы меняем на экране
     */
    public static void updatePerson(Person currentPerson, Person updatePerson) {
        currentPerson.setIntelligence(updatePerson.getIntelligence());
        currentPerson.setAgility(updatePerson.getAgility());
        currentPerson.setStrength(updatePerson.getStrength());
        currentPerson.setSpecialization(updatePerson.getSpecialization());
    }

    public static void updatePerson(Person currentPerson, String intel, String agility, String str, String spec) {
        try {
            currentPerson.setIntelligence(Integer.parseInt(intel));
        } catch (NumberFormatException exc) {
            currentPerson.setIntelligence(0);
        }

        try {
            currentPerson.setAgility(Integer.parseInt(agility));
        } catch (NumberFormatException exc) {
            currentPerson.setAgility(0);
        }

        try {
            currentPerson.setStrength(Integer.parseInt(str));
        } catch (NumberFormatException exc) {
            currentPerson.setStrength(0);
        }

        currentPerson.setSpecialization(spec);
    }
}
