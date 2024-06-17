package it.intesys.academy.service;

import it.intesys.academy.dto.FullDayTimeOffAPIDTO;
import it.intesys.academy.dto.FullDayTimeOffDTO;
import it.intesys.academy.exceptions.ForbiddenException;
import it.intesys.academy.exceptions.NotFoundException;
import it.intesys.academy.exceptions.UnauthorizedException;
import it.intesys.academy.mapper.FullDayTimeOffModelMapper;
import it.intesys.academy.model.FullDayTimeOff;
import it.intesys.academy.model.User;
import it.intesys.academy.repository.FullDayTimeOffRepository;
import it.intesys.academy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FullDayTimeOffService {
  private final FullDayTimeOffRepository fullDayTimeOffRepository;

  private final UserRepository userRepository;

  public FullDayTimeOffService(FullDayTimeOffRepository fullDayTimeOffRepository, UserRepository userRepository) {
    this.fullDayTimeOffRepository = fullDayTimeOffRepository;
    this.userRepository = userRepository;
  }

  public List<FullDayTimeOffDTO> getFullDayTimeoff(long userId) {

    List<FullDayTimeOff> fullDayTimeOffList = fullDayTimeOffRepository.findByUserId(userId);

    return fullDayTimeOffList.stream().map(fullDayTimeOff -> FullDayTimeOffModelMapper.fromEntityToDTO(fullDayTimeOff, "dd/MM/yyyy")).collect(Collectors.toList());

  }

  public void save(FullDayTimeOffDTO fullDayTimeOffDTO, long userId) {

    FullDayTimeOff fullDayTimeOff = FullDayTimeOffModelMapper.fromDTOtoEntity(fullDayTimeOffDTO);

    fullDayTimeOff.setUser(userRepository.findById(userId).get());

    fullDayTimeOffRepository.save(fullDayTimeOff);

  }

  public void deleteFullDayTimeOff(long idFullDayTimeOff) {

    fullDayTimeOffRepository.deleteById(idFullDayTimeOff);

  }

  public FullDayTimeOffDTO getFullDayTimeOffById(long id) {
    return FullDayTimeOffModelMapper.fromEntityToDTO(fullDayTimeOffRepository.findById(id).get(), "yyyy-MM-dd");
  }

  public FullDayTimeOffAPIDTO getFullDayTimeOffRequest(Long userId, Long requestId) {
    FullDayTimeOff fullDayTimeOffRequest = fullDayTimeOffRepository.findById(requestId)
            .orElseThrow(() -> new NotFoundException("Bad request Id"));
    if (!Objects.equals(userId, fullDayTimeOffRequest.getUser().getId())) {
      throw new UnauthorizedException("Logged user is not the owner of the request");
    }

    return FullDayTimeOffModelMapper.fromEntityToAPIDTO(fullDayTimeOffRequest);
  }

  public FullDayTimeOffAPIDTO createFullDayTimeOffRequest(FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO, Long userId) {
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("User not found"));

    var fullDayTimeOffRequest = FullDayTimeOffModelMapper.fromAPIDTOtoEntity(fullDayTimeOffAPIDTO);
    fullDayTimeOffRequest.setUser(user);

    fullDayTimeOffRequest = fullDayTimeOffRepository.save(fullDayTimeOffRequest);

    return FullDayTimeOffModelMapper.fromEntityToAPIDTO(fullDayTimeOffRequest);
  }

  public FullDayTimeOffAPIDTO updateFullDayTimeOffRequest(Long requestId, FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO, Long userId) {
    userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

    if (!Objects.equals(requestId, fullDayTimeOffAPIDTO.getId())) {
      throw new ForbiddenException("Request body id must be the same as the path variable");
    }

    var existingFullDayTimeOffRequest = fullDayTimeOffRepository.findById(requestId)
            .orElseThrow(() -> new NotFoundException("Request not found"));

    if (!Objects.equals(existingFullDayTimeOffRequest.getUser().getId(), userId)) {
      throw new UnauthorizedException("Logged user is not the owner of the request");
    }

    existingFullDayTimeOffRequest.setFrom(fullDayTimeOffAPIDTO.getFrom());
    existingFullDayTimeOffRequest.setTo(fullDayTimeOffAPIDTO.getTo());
    existingFullDayTimeOffRequest = fullDayTimeOffRepository.save(existingFullDayTimeOffRequest);

    return FullDayTimeOffModelMapper.fromEntityToAPIDTO(existingFullDayTimeOffRequest);
  }
}


