package it.intesys.academy.repository;

import it.intesys.academy.model.TimeRange;

import java.util.List;

public interface TimeRangeRepository {

    List<TimeRange> findTimeRange(long partialDayTimeoff);

}
