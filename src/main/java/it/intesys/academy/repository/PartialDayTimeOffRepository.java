package it.intesys.academy.repository;

import it.intesys.academy.model.PartialDayTimeOff;

import java.util.List;

public interface PartialDayTimeOffRepository {

    List<PartialDayTimeOff> findPartialDaysForUser(long idUser);

}
