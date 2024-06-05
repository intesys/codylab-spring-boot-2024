package it.intesys.academy.service;

import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.mapper.PartialDayTimeOffModelMapper;
import it.intesys.academy.model.PartialDayTimeOff;
import it.intesys.academy.repository.PartialDayTimeOffRepository;
import it.intesys.academy.repository.TimeRangeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartialDayTimeOffService {

  private final PartialDayTimeOffRepository partialDayTimeOffRepository;

  private final TimeRangeRepository timeRangeRepository;

  public PartialDayTimeOffService(PartialDayTimeOffRepository partialDayTimeOffRepository, TimeRangeRepository timeRangeRepository) {
    this.partialDayTimeOffRepository = partialDayTimeOffRepository;
    this.timeRangeRepository = timeRangeRepository;
  }

  public List<PartialDayTimeOffDTO> getPartialDayTimeoff(long userId) {

    List<PartialDayTimeOff> partialDayTimeOffList = partialDayTimeOffRepository.findByUserId(userId);

    return partialDayTimeOffList.stream().map(PartialDayTimeOffModelMapper::fromEntityToDTO).collect(Collectors.toList());

  }

  public void deletePartialDayTimeOff(long idPartialDayTimeOff) {

    /*
    timeRangeRepository.findTimeRange(idPartialDayTimeOff).forEach(
            timeRange -> timeRangeRepository.deleteTimeRange(timeRange.getId())
    );

    partialDayTimeOffRepository.deletePartialDayTimeOff(idPartialDayTimeOff);
    */
  }

}
