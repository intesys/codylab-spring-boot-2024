package it.intesys.academy.service;

import it.intesys.academy.dto.FullDayTimeOffDTO;
import it.intesys.academy.mapper.FullDayTimeOffModelMapper;
import it.intesys.academy.model.FullDayTimeOff;
import it.intesys.academy.repository.FullDayTimeOffRepository;
import it.intesys.academy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
