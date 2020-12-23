package com.telemedecine.telemedecine.service;



import com.telemedecine.telemedecine.dto.PatientDto;
import com.telemedecine.telemedecine.exception.AppException;
import com.telemedecine.telemedecine.exception.ExceptionCode;
import com.telemedecine.telemedecine.mapper.PatientMapper;
import com.telemedecine.telemedecine.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class PatientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private com.telemedecine.telemedecine.repository.PatientRepository PatientRepository;

    public Long add(PatientDto PatientDto){

        log.info("SATRT SERVICE CREATE NEW doctor");
        if (PatientDto == null) {
            throw new AppException(ExceptionCode.API_MISSING_PARAMETER, "Patient required");
        }
        PatientRepository.save(PatientMapper.toEntity(PatientDto));
        log.info("END SERVICE ADD NEW doctor BY ID: {}", PatientDto.getId());
        return PatientDto.getId();
    }
    public void save(PatientDto PatientDto) {
        LOGGER.debug("START SERVICE save by id {}",PatientDto.getId());
        PatientRepository.save(PatientMapper.toEntity(PatientDto));
        LOGGER.debug("END SERVICE save by id {}",PatientDto.getId());
    }
    public void delete(Long id) {
        LOGGER.debug("START SERVICE delete by id {}", id);
        PatientRepository.deleteById(id);
        LOGGER.debug("START SERVICE delete by id {}", id);
    }

    public PatientDto findById(Long id) throws Exception {
        LOGGER.debug("START SERVICE find by id {}", id);
        return Optional.ofNullable(PatientRepository.findById(id))
                .map(o -> PatientMapper.toDto(o.get()))
                .orElseThrow(() -> new AppException(ExceptionCode.API_RESOURCE_NOT_FOUND, "doctor not found"));
    }

    public List<PatientDto> findAll() throws AppException {
        LOGGER.debug("START SERVICE find all");
        return Optional.ofNullable(PatientRepository.findAll())
                .map(PatientMapper::toDtos)
                .orElseThrow(() -> new AppException(ExceptionCode.API_RESOURCE_NOT_FOUND, "patients not found"));
    }

}
