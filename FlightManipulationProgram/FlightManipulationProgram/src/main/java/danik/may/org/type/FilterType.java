package danik.may.org.type;

import danik.may.org.filter.TimeTableFilter;
import danik.may.org.filter.impl.FilterArrivalBeforeDeparture;
import danik.may.org.filter.impl.FilterByGroundTimeExceed;
import danik.may.org.filter.impl.FilterDepartureByCurrentDate;

/**
 * Filtration types with implementation
 */
public enum FilterType {
    DEPARTURE_BY_CURRENT_DATE(new FilterDepartureByCurrentDate()),
    ARRIVAL_BEFORE_DEPARTURE(new FilterArrivalBeforeDeparture()),
    GROUND_TIME_FILTER(new FilterByGroundTimeExceed());

    FilterType(TimeTableFilter timeTableFilter) {
        this.timeTableFilter = timeTableFilter;
    }

    private TimeTableFilter timeTableFilter;

    public TimeTableFilter getTimeTableFilter() {
        return timeTableFilter;
    }
}
