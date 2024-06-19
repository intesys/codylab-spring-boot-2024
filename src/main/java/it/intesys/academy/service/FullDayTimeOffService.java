package it.intesys.academy.service;

import it.intesys.academy.dto.FullDayTimeOffAPIDTO;
import it.intesys.academy.dto.FullDayTimeOffDTO;
import it.intesys.academy.exceptions.ForbiddenException;
import it.intesys.academy.exceptions.NotFoundException;
import it.intesys.academy.mapper.FullDayTimeOffModelMapper;
import it.intesys.academy.model.FullDayTimeOff;
import it.intesys.academy.model.User;
import it.intesys.academy.repository.FullDayTimeOffRepository;
import it.intesys.academy.repository.UserRepository;
import java.util.Objects;

import it.intesys.academy.validator.FullDayTimeOffAPIDTOValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FullDayTimeOffService {
    private final FullDayTimeOffRepository fullDayTimeOffRepository;
    private final UserRepository userRepository;
    private final FullDayTimeOffAPIDTOValidator fullDayTimeOffAPIDTOValidator;

    public FullDayTimeOffService(FullDayTimeOffRepository fullDayTimeOffRepository, UserRepository userRepository, FullDayTimeOffAPIDTOValidator fullDayTimeOffAPIDTOValidator) {
        this.fullDayTimeOffRepository = fullDayTimeOffRepository;
        this.userRepository = userRepository;
      this.fullDayTimeOffAPIDTOValidator = fullDayTimeOffAPIDTOValidator;
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

    public FullDayTimeOffAPIDTO save(FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO, long userId) {
        fullDayTimeOffAPIDTOValidator.validate(fullDayTimeOffAPIDTO);

        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        var fullDayTimeOffRequest = FullDayTimeOffModelMapper.fromAPIDTOtoEntity(fullDayTimeOffAPIDTO);
        fullDayTimeOffRequest.setUser(user);

        fullDayTimeOffRequest = fullDayTimeOffRepository.save(fullDayTimeOffRequest);

        return FullDayTimeOffModelMapper.fromEntityToAPIDTO(fullDayTimeOffRequest);
    }

    public FullDayTimeOffAPIDTO update(FullDayTimeOffAPIDTO fullDayTimeOffAPIDTO, long userId) {
        // todo validate request, prior to go to the database

        var existingRequest = fullDayTimeOffRepository.findById(fullDayTimeOffAPIDTO.getId())
            .orElseThrow(() -> new NotFoundException("Invalid request id"));

        if (!Objects.equals(existingRequest.getUser().getId(), userId)) {
            throw new ForbiddenException("Permission denied");
        }

        existingRequest.setFrom(fullDayTimeOffAPIDTO.getFrom());
        existingRequest.setTo(fullDayTimeOffAPIDTO.getTo());

        existingRequest = fullDayTimeOffRepository.save(existingRequest);

        return FullDayTimeOffModelMapper.fromEntityToAPIDTO(existingRequest);
    }

    public void deleteFullDayTimeOff(long idFullDayTimeOff) {

        fullDayTimeOffRepository.deleteById(idFullDayTimeOff);

    }

    public FullDayTimeOffDTO getFullDayTimeOffById(long id) {
        return FullDayTimeOffModelMapper.fromEntityToDTO(fullDayTimeOffRepository.findById(id).get(), "yyyy-MM-dd");
    }
}
