package danik.may.org.filter.impl;

import danik.may.org.filter.TimeTableFilter;
import danik.may.org.filter.entity.FiltrationParameters;
import danik.may.org.model.Flight;

import java.time.LocalDateTime;

/**
 * The filter excludes flights if the current date is later than at least one arrival date in the segment
 */
public class FilterDepartureByCurrentDate implements TimeTableFilter {

    /**
     * Filtration result if the current date is later than at least one arrival date in the segment
     *
     * @param flight     - aircraft flight data.
     * @param parameters - advanced Filtering parameters.
     * @return - filtration result.
     */
    @Override
    public boolean filter(Flight flight, FiltrationParameters parameters) {
        LocalDateTime filtrationTime = parameters.getFiltrationTime();
        return filtrationTime.isBefore(flight.getSegments().stream().findFirst().get().getDepartureDate());
    }
}
