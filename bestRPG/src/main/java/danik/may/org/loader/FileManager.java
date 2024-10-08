package danik.may.org.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    public String load() {
        StringBuilder expectedValue = new StringBuilder();

        Path path = Paths.get("data/based/characters.json");
        try {
            String line = "";
            BufferedReader reader = Files.newBufferedReader(path);
            while ((line = reader.readLine()) != null) {
                expectedValue.append(line);
                expectedValue.append('\n');
            }
        } catch (Exception exception) {
            expectedValue.append("Loading data fail!");
        }
        return expectedValue.toString();
    }

    public void save(String jsonData) {
        Path path = Paths.get("data/saved/charactersUpdates.json");
        byte[] arr = jsonData.getBytes();
        try {
            Files.write(path, arr);
        } catch (IOException ex) {
            System.out.print("Invalid Path");
        }
    }
}
