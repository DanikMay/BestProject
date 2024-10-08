package danik.may.org.filter;

import danik.may.org.filter.entity.FiltrationParameters;
import danik.may.org.model.Flight;
import danik.may.org.type.FilterType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Aircraft flight filtering control.
 */
public class FlightFilterManager {

    /**
     * Returns a filtered list of flights using a single filter.
     *
     * @param flight - data on aircraft flights.
     * @param type   - filter type.
     * @return - filtred list.
     */
    public static List<Flight> getFiltredFlight(List<Flight> flight, FilterType type) {
        return getFiltredFlight(flight, type, null);
    }

    /**
     * Returns a filtered list of flights using a multiple filters.
     *
     * @param flight - data on aircraft flights.
     * @param types  - filter types.
     * @return - filtred list.
     */
    public static List<Flight> getFiltredFlight(List<Flight> flight, List<FilterType> types, FiltrationParameters filtrationParameters) {
        final List<Flight> filtredFlights = new ArrayList<>(flight);
        types.stream()
                .forEach(type -> {
                    List<Flight> filtredIteration = getFiltredFlight(filtredFlights, type, filtrationParameters);
                    filtredFlights.clear();
                    filtredFlights.addAll(filtredIteration);
                });
        return filtredFlights;
    }

    /**
     * Returns a filtered list of flights using a single filter with advanced filtering options.
     *
     * @param flight - data on aircraft flights.
     * @param type   - filter type.
     * @return - filtred list.
     */
    public static List<Flight> getFiltredFlight(List<Flight> flight, FilterType type, FiltrationParameters filtrationParameters) {
        List<Flight> filtredFlights = new ArrayList<>();
        filtredFlights = flight.stream()
                .filter(currentFlight -> filtrationByType(currentFlight, type, filtrationParameters))
                .collect(Collectors.toList());
        return filtredFlights;
    }

    /**
     * Filtration result for specific filter.
     *
     * @param flight     - data on aircraft flights.
     * @param type       - filter type.
     * @param parameters -advanced filtering options.
     * @return - filtration result.
     */
    private static boolean filtrationByType(Flight flight, FilterType type, FiltrationParameters parameters) {
        return type.getTimeTableFilter().filter(flight, parameters);
    }
}
