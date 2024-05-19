package danik.may.org;

public class Character {
    Character(String name, int strength, int agility, int intelligence) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.intelligence = intelligence;
    }
    Character(String name) {
        this.name = name;
    }
    protected String name;
    protected int strength;
    protected int agility;
    protected int intelligence;
    protected String specialization = "У персонажа нет специализации";

    public void getInfo() {
        System.out.print("Имя - "+name+" ");
        System.out.print("Класс:"+specialization+" ");
        System.out.print("Cила:"+strength+" ");
        System.out.print("Ловкость:"+agility+" ");
        System.out.print("Магия:"+intelligence+" ");
        System.out.println();
    }
}
