package danik.may.org;

import danik.may.org.data.FlightBuilderTestData;
import danik.may.org.filter.FlightFilterManager;
import danik.may.org.filter.entity.FiltrationParameters;
import danik.may.org.model.Flight;
import danik.may.org.model.FlightBuilder;
import danik.may.org.type.FilterType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static danik.may.org.type.FilterType.ARRIVAL_BEFORE_DEPARTURE;
import static danik.may.org.type.FilterType.DEPARTURE_BY_CURRENT_DATE;
import static danik.may.org.type.FilterType.GROUND_TIME_FILTER;
import static org.testng.AssertJUnit.assertEquals;

public class FlightFilterManagerTest {

    @Test
    public void allFlightsDeparturesBeforeCurrentDateTest() {
        //Given:
        int expectedElements = 0;

        LocalDateTime currentTime = LocalDateTime.now().plusDays(10);
        List<Flight> flights = FlightBuilder.createFlights();
        //When:
        List<Flight> result = FlightFilterManager.getFiltredFlight(flights, DEPARTURE_BY_CURRENT_DATE, new FiltrationParameters(currentTime));
        //Than:
        assertEquals(expectedElements, result.size());
    }

    @Test
    public void allFlightsDeparturesAfterCurrentDateTest() {
        //Given:
        int expectedElements = 6;

        LocalDateTime currentTime = LocalDateTime.now().minusDays(10);
        List<Flight> flights = FlightBuilder.createFlights();
        //When:
        List<Flight> result = FlightFilterManager.getFiltredFlight(flights, DEPARTURE_BY_CURRENT_DATE, new FiltrationParameters(currentTime));
        //Than:
        assertEquals(expectedElements, result.size());
    }

    @Test
    public void groundDelayWithTwoFlightsExcludeTest() {
        //Given:
        int expectedElements = 4;
        List<Flight> flights = FlightBuilder.createFlights();
        //When:
        List<Flight> result = FlightFilterManager.getFiltredFlight(flights, GROUND_TIME_FILTER);
        //Than:
        assertEquals(expectedElements, result.size());
    }
    @Test
    public void groundDelayWithFourFlightsExcludeTest() {
        //Given:
        int expectedElements = 0;
        List<Flight> flights = FlightBuilderTestData.createFlightsWithDifferentGroundTime();
        //When:
        List<Flight> result = FlightFilterManager.getFiltredFlight(flights, GROUND_TIME_FILTER);
        //Than:
        assertEquals(expectedElements, result.size());
    }

    @Test
    public void arrivalBeforeDepartureTest() {
        //Given:
        int expectedElements = 0;
        List<Flight> flights = FlightBuilderTestData.createFlightsWhereArrivalBeforeDeparture();
        //When:
        List<Flight> result = FlightFilterManager.getFiltredFlight(flights, ARRIVAL_BEFORE_DEPARTURE);
        //Than:
        assertEquals(expectedElements, result.size());
    }

    @Test
    public void groundDelayAllDataSuccessTest() {
        //Given:
        int expectedElements = 6;
        List<Flight> flights = FlightBuilderTestData.createFlightsWithoutGroundDelay();
        //When:
        List<Flight> result = FlightFilterManager.getFiltredFlight(flights, GROUND_TIME_FILTER);
        //Than:
        assertEquals(expectedElements, result.size());
    }

    @Test
    public void flightsWrongDataTest() {
        //Given:
        int expectedElements = 0;
        LocalDateTime currentTime = LocalDateTime.now().minusDays(10);
        List<Flight> flights = new ArrayList<>(FlightBuilder.createFlights());
        flights.clear();
        //When:
        List<Flight> result = FlightFilterManager.getFiltredFlight(flights, DEPARTURE_BY_CURRENT_DATE, new FiltrationParameters(currentTime));
        //Than:
        assertEquals(expectedElements, result.size());
    }

    @Test
    public void allFiltersTest() {
        //Given:
        int expectedElements = 2;
        List<Flight> flights = FlightBuilder.createFlights();
        String firstElementDepartureArrivalExpected = flights.get(0).toString();
        String secondElementDepartureArrivalExpected = flights.get(1).toString();
        LocalDateTime currentTime = LocalDateTime.now();
        //When:
        List<Flight> result = FlightFilterManager.getFiltredFlight(flights,
                new ArrayList<FilterType>() {{
                    add(DEPARTURE_BY_CURRENT_DATE);
                    add(ARRIVAL_BEFORE_DEPARTURE);
                    add(GROUND_TIME_FILTER);
                }},
                new FiltrationParameters(currentTime));
        //Than:
        assertEquals(expectedElements, result.size());
        assertEquals(firstElementDepartureArrivalExpected, result.get(0).toString());
        assertEquals(secondElementDepartureArrivalExpected, result.get(1).toString());
    }

}
