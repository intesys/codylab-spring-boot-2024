package it.intesys.academy.repository;

import it.intesys.academy.model.FullDayTimeOff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface FullDayTimeOffRepository extends ListCrudRepository<FullDayTimeOff, Long> {

    @Query(value = "select pd from FullDayTimeOff pd where pd.user.id = ?1")
    List<FullDayTimeOff> findByUserId(long userId);

}
