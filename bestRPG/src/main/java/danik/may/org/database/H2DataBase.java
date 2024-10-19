package danik.may.org.database;

import danik.may.org.entity.Person;

import java.sql.*;
import java.util.List;

public class H2DataBase {
    private H2DataBase() throws SQLException {
        connection = DriverManager.getConnection(jdbcURL);
        String sql = "Create table characters (ID int primary key, specialization varchar(50), intelligence varchar(50), agility varchar(50), strength varchar(50))";
        statement = connection.createStatement();
        statement.execute(sql);
    }
    private final String jdbcURL = "jdbc:h2:mem:test";
    private final Connection connection;
    private final Statement statement;
    private static H2DataBase h2DataBase = null;



    public static H2DataBase getH2DataBase() throws SQLException {
        if(h2DataBase == null) {
            h2DataBase = new H2DataBase();
        }
        return h2DataBase;
    }
    public void add(Person person) throws SQLException {
        String sql = String.format("Insert into characters (ID, specialization) values (%d, '%s')", person.getuId(), person.getSpecialization());
        statement.executeUpdate(sql);
    }
    public void add(List<Person> personList) throws SQLException {
        for(Person tempPerson: personList) {
            String sql = String.format("Insert into characters (ID, specialization, intelligence, agility, strength) values (%d, '%s', '%s', '%s', '%s')",
                    tempPerson.getuId(), tempPerson.getSpecialization(), tempPerson.getIntelligence(), tempPerson.getAgility(), tempPerson.getStrength());
            //String sql = String.format("Insert into persons (ID, specialization) values (%d, '%s')", tempPerson.getuId(), tempPerson.getSpecialization());
            statement.executeUpdate(sql);
        }
    }

    public void read() throws SQLException {
        String sql = "SELECT * FROM characters";
        ResultSet resultSet = statement.executeQuery(sql);

        int count = 0;

        while (resultSet.next()) {
            count++;

            int ID = resultSet.getInt("ID");
            String specialization = resultSet.getString("specialization");
            String intelligence  = resultSet.getString("intelligence");
            String agility = resultSet.getString("agility");
            String strength = resultSet.getString("strength");
            System.out.println("Characters #" + count + ": " + ID + ", " + specialization + ", " + intelligence + ", " + agility + ", " + strength);
        }
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
