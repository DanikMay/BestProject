package danik.may.org.filter.entity;

import java.time.LocalDateTime;

/**
 * Advanced Filtering Options. Can be enriched if new filters are added.
 */
public class FiltrationParameters {
    //Specific date
    private LocalDateTime filtrationTime;

    public FiltrationParameters(LocalDateTime filtrationTime) {
        this.filtrationTime = filtrationTime;
    }

    public LocalDateTime getFiltrationTime() {
        return filtrationTime;
    }

    public void setFiltrationTime(LocalDateTime filtrationTime) {
        this.filtrationTime = filtrationTime;
    }
}
