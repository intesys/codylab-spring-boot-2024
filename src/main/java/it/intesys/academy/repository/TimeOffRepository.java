package it.intesys.academy.repository;

import it.intesys.academy.model.TimeOff;
import org.springframework.data.repository.ListCrudRepository;

public interface TimeOffRepository extends ListCrudRepository<TimeOff, Long> {

}
