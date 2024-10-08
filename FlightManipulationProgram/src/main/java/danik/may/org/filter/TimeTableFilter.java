package danik.may.org.filter;

import danik.may.org.filter.entity.FiltrationParameters;
import danik.may.org.model.Flight;

/**
 * Aircraft flight filter interface.
 */
public interface TimeTableFilter {
    /**
     * Behavior of aircraft flight data filter.
     *
     * @param flight - aircraft flight data.
     * @param parameters - advanced Filtering parameters.
     * @return - filtration result.
     */
    boolean filter(Flight flight, FiltrationParameters parameters);
}
