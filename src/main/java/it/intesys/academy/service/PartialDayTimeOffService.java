package it.intesys.academy.service;

import it.intesys.academy.dto.PartialDayTimeOffAPIDTO;
import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.exceptions.ForbiddenException;
import it.intesys.academy.exceptions.NotFoundException;
import it.intesys.academy.mapper.FullDayTimeOffModelMapper;
import it.intesys.academy.mapper.PartialDayTimeOffModelMapper;
import it.intesys.academy.mapper.TimeRangeModelMapper;
import it.intesys.academy.model.PartialDayTimeOff;
import it.intesys.academy.model.User;
import it.intesys.academy.repository.PartialDayTimeOffRepository;
import it.intesys.academy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PartialDayTimeOffService {

  private final PartialDayTimeOffRepository partialDayTimeOffRepository;

  private final UserRepository userRepository;

  public PartialDayTimeOffService(PartialDayTimeOffRepository partialDayTimeOffRepository,
                                  UserRepository userRepository) {
    this.partialDayTimeOffRepository = partialDayTimeOffRepository;
      this.userRepository = userRepository;
  }

  public List<PartialDayTimeOffDTO> getPartialDayTimeoff(long userId) {

    List<PartialDayTimeOff> partialDayTimeOffList = partialDayTimeOffRepository.findByUserId(userId);

    return partialDayTimeOffList.stream().map(PartialDayTimeOffModelMapper::fromEntityToDTO).collect(Collectors.toList());

  }

  public PartialDayTimeOffAPIDTO getPartialDayTimeOffRequest(Long requestId, Long userId) {
    var existingRequest = partialDayTimeOffRepository.findById(requestId)
            .orElseThrow(() -> new NotFoundException("Invalid request id"));

    if (!Objects.equals(existingRequest.getUser().getId(), userId)) {
      throw new ForbiddenException("Permission denied");
    }

    return PartialDayTimeOffModelMapper.fromEntityToAPIDTO(existingRequest);
  }

  public void save(PartialDayTimeOffDTO partialDayTimeOffDTO, long userId) {

    PartialDayTimeOff partialDayTimeOff = PartialDayTimeOffModelMapper.fromDTOtoEntity(partialDayTimeOffDTO);

    partialDayTimeOff.setUser(userRepository.findById(userId).get());

    partialDayTimeOffRepository.save(partialDayTimeOff);

  }

  public void save(PartialDayTimeOffAPIDTO partialDayTimeOffAPIDTO, long userId) {

    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

    PartialDayTimeOff partialDayTimeOff = PartialDayTimeOffModelMapper.fromAPIDTOtoEntity(partialDayTimeOffAPIDTO);

    partialDayTimeOff.setUser(user);

    partialDayTimeOffRepository.save(partialDayTimeOff);

  }

  public PartialDayTimeOffAPIDTO update(PartialDayTimeOffAPIDTO partialDayTimeOffAPIDTO, long userId) {

    var existingRequest = partialDayTimeOffRepository.findById(partialDayTimeOffAPIDTO.getId())
        .orElseThrow(() -> new IllegalArgumentException("Partial day time off not found"));

    if (!Objects.equals(existingRequest.getUser().getId(), userId)) {
      throw new ForbiddenException("Permission denied");
    }

    existingRequest.setDate(partialDayTimeOffAPIDTO.getDate());
    existingRequest.setTimeRangeList(TimeRangeModelMapper.fromDTOstoEntities(partialDayTimeOffAPIDTO.getTimeRangeDTOList() , existingRequest));

    existingRequest = partialDayTimeOffRepository.save(existingRequest);

    return PartialDayTimeOffModelMapper.fromEntityToAPIDTO(existingRequest);
  }

  public void deletePartialDayTimeOff(long idPartialDayTimeOff) {

    partialDayTimeOffRepository.deleteById(idPartialDayTimeOff);

  }

  public List<PartialDayTimeOffAPIDTO> getPartialDayTimeOffRequests(Long userId) {
    userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User not found"));

    List<PartialDayTimeOff> partialDayTimeOffList = partialDayTimeOffRepository.findByUserId(userId);

    return partialDayTimeOffList.stream().map(PartialDayTimeOffModelMapper::fromEntityToAPIDTO).collect(Collectors.toList());
  }

  public void delete(Long requestId, Long userId) {
    var existingRequest = partialDayTimeOffRepository.findById(requestId)
        .orElseThrow(() -> new IllegalArgumentException("Request not found"));

    if (!Objects.equals(existingRequest.getUser().getId(), userId)) {
      throw new IllegalArgumentException("Permission denied");
    }

    partialDayTimeOffRepository.delete(existingRequest);
  }
}
