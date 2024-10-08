package danik.may.org.utils;

import danik.may.org.model.Flight;

import java.util.List;

/**
 * Utility class for standard console output.
 */
public class StdOutUtils {
    /**
     * Write text with log titles.
     *
     * @param message - title of stdout log data
     */
    public static void showMessage(String message) {
        System.out.println();
        System.out.println(message);
    }

    /**
     * Write {@link Flight} elements after filtration.
     *
     * @param result - filtration result data.
     */
    public static void showFiltrationResult(List<Flight> result) {
        result.forEach(System.out::println);
    }
}
