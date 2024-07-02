//package it.intesys.academy.repository;
//
//import it.intesys.academy.model.PartialDayTimeOff;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.ListCrudRepository;
//
//import java.util.List;
//
//public interface PartialDayTimeOffRepository extends ListCrudRepository<PartialDayTimeOff, Long> {
//
//    @Query(value = "select pd from PartialDayTimeOff pd where pd.user.id = ?1")
//    List<PartialDayTimeOff> findByUserId(long userId);
//
//}
