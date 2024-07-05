package danik.may.org.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import danik.may.org.entity.Characters;

public class JsonConverter {
    private ObjectMapper objectMapper = new ObjectMapper();

    public Characters convertData(String json) {
        Characters characters = null;
        try {
            characters = objectMapper.readValue(json, Characters.class);
        } catch (Exception exception) {

        }
        return characters;
    }

    public String writeData(Characters characters){
        return null;
    }
}
