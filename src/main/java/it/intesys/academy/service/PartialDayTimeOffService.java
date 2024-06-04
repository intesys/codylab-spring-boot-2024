package it.intesys.academy.service;

import it.intesys.academy.model.PartialDayTimeOff;
import it.intesys.academy.repository.PartialDayTimeOffRepository;
import it.intesys.academy.repository.TimeRangeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartialDayTimeOffService {

  private final PartialDayTimeOffRepository partialDayTimeOffRepository;

  private final TimeRangeRepository timeRangeRepository;

  public PartialDayTimeOffService(PartialDayTimeOffRepository partialDayTimeOffRepository, TimeRangeRepository timeRangeRepository) {
    this.partialDayTimeOffRepository = partialDayTimeOffRepository;
    this.timeRangeRepository = timeRangeRepository;
  }

  public List<?> getPartialDayTimeoff(long idUser) {

    List<PartialDayTimeOff> partialDayTimeOffList = partialDayTimeOffRepository.findPartialDaysForUser(idUser);

    partialDayTimeOffList.forEach(
            partialDayTimeOff
            ->
            partialDayTimeOff.setTimeRangeList( timeRangeRepository.findTimeRange(partialDayTimeOff.getId()) )
    );

    return partialDayTimeOffList;
  }

}
