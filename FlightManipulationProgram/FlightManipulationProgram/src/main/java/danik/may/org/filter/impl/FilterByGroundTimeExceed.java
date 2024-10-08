package danik.may.org.filter.impl;

import danik.may.org.filter.TimeTableFilter;
import danik.may.org.filter.entity.FiltrationParameters;
import danik.may.org.model.Flight;
import danik.may.org.model.Segment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Filter excludes data if time on ground in hours is more than a max value.
 */
public class FilterByGroundTimeExceed implements TimeTableFilter {

    //Maximum aircraft hours on ground.
    private static final int GROUND_MAX_HOURS = 2;
    private long currentGroundHours = 0;

    /**
     * Filtration result if time on ground in hours is more than a max value.
     *
     * @param flight     - aircraft flight data.
     * @param parameters - advanced Filtering parameters.
     * @return - filtration result.
     */
    @Override
    public boolean filter(Flight flight, FiltrationParameters parameters) {
        boolean result = true;
        currentGroundHours = 0;
        if (flight.getSegments().size() > 1) {
            List<LocalDateTime> depatures = flight.getSegments()
                    .stream()
                    .map(Segment::getDepartureDate)
                    .collect(Collectors.toList());
            List<LocalDateTime> arrivals = flight.getSegments()
                    .stream()
                    .map(Segment::getArrivalDate)
                    .collect(Collectors.toList());

            IntStream.range(0, depatures.size())
                    .forEach(i -> {
                        if (i < depatures.size() - 1) {
                            incrementHours(arrivals.get(i).until(depatures.get(i + 1), ChronoUnit.HOURS));
                        }
                    });

            result = currentGroundHours <= GROUND_MAX_HOURS;
        }
        return result;
    }

    private void incrementHours(long count) {
        currentGroundHours = currentGroundHours + count;
    }
}
