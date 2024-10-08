package danik.may.org.filter.impl;

import danik.may.org.filter.TimeTableFilter;
import danik.may.org.filter.entity.FiltrationParameters;
import danik.may.org.model.Flight;

/**
 * Filter excludes data with arrival time before departure time.
 */
public class FilterArrivalBeforeDeparture implements TimeTableFilter {

    /**
     * Filtration result with arrival time before departure time.
     *
     * @param flight     - aircraft flight data.
     * @param parameters - advanced Filtering parameters.
     * @return - filtration result.
     */
    @Override
    public boolean filter(Flight flight, FiltrationParameters parameters) {
        return flight.getSegments().stream().noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));
    }
}
