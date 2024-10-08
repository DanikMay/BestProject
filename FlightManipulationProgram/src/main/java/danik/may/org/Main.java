package danik.may.org;

import danik.may.org.filter.entity.FiltrationParameters;
import danik.may.org.model.Flight;
import danik.may.org.model.FlightBuilder;
import danik.may.org.type.FilterType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static danik.may.org.filter.FlightFilterManager.getFiltredFlight;
import static danik.may.org.type.FilterType.*;
import static danik.may.org.utils.StdOutUtils.showFiltrationResult;
import static danik.may.org.utils.StdOutUtils.showMessage;

public class Main {

    public static void main(String[] args) {
        LocalDateTime currentTime = LocalDateTime.now();

        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("All test data");
        flights.stream().forEach(System.out::println);

        showMessage("Filtred by specific date");
        showFiltrationResult(getFiltredFlight(flights, DEPARTURE_BY_CURRENT_DATE, new FiltrationParameters(currentTime)));

        showMessage("Filtred by wrong timetable (arrival before departure): ");
        showFiltrationResult(getFiltredFlight(flights, ARRIVAL_BEFORE_DEPARTURE));

        showMessage("Filtred by land time excess: ");
        showFiltrationResult(getFiltredFlight(flights, GROUND_TIME_FILTER));

        showMessage("All filters: ");
        showFiltrationResult(getFiltredFlight(flights,
                new ArrayList<FilterType>() {{
                    add(DEPARTURE_BY_CURRENT_DATE);
                    add(ARRIVAL_BEFORE_DEPARTURE);
                    add(GROUND_TIME_FILTER);
                }},
                new FiltrationParameters(currentTime)));
    }
}
