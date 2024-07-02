package it.intesys.academy.repository;

import it.intesys.academy.model.TimeOff;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TimeOffRepository extends ListCrudRepository<TimeOff, Long> {

    List<TimeOff> findByUserId(long l);
}
