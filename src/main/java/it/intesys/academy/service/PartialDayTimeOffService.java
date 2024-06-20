package it.intesys.academy.service;

import it.intesys.academy.dto.PartialDayTimeOffDTO;
import it.intesys.academy.dto.PartialDayTimeOffAPIDTO;
import it.intesys.academy.exceptions.NotFoundException;
import it.intesys.academy.mapper.PartialDayTimeOffModelMapper;
import it.intesys.academy.model.PartialDayTimeOff;
import it.intesys.academy.repository.PartialDayTimeOffRepository;
import it.intesys.academy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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


  //ritorna la lista di permessi di un determinato utente
  public List<PartialDayTimeOffDTO> getPartialDayTimeoff(long userId) {
    List<PartialDayTimeOff> partialDayTimeOffList = partialDayTimeOffRepository.findByUserId(userId);

    return partialDayTimeOffList.stream().map(PartialDayTimeOffModelMapper::fromEntityToDTO).collect(Collectors.toList());
  }


  //ritorna un permesso di un determinato utente
  public PartialDayTimeOffAPIDTO getPartialDayTimeOffRequest (Long requestId, Long userID) {
    PartialDayTimeOff partialDayTimeOffRequest = partialDayTimeOffRepository.findById(requestId)
            .orElseThrow(() -> new NotFoundException("Bad Request Id"));

    return PartialDayTimeOffModelMapper.fromEntityToAPIDTO(partialDayTimeOffRequest);
  }


  public void save(PartialDayTimeOffDTO partialDayTimeOffDTO, long userId) {

    PartialDayTimeOff partialDayTimeOff = PartialDayTimeOffModelMapper.fromDTOtoEntity(partialDayTimeOffDTO);

    partialDayTimeOff.setUser(userRepository.findById(userId).get());

    partialDayTimeOffRepository.save(partialDayTimeOff);

  }

  public void deletePartialDayTimeOff(long idPartialDayTimeOff) {

    partialDayTimeOffRepository.deleteById(idPartialDayTimeOff);

  }

}
