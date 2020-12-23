package com.telemedecine.telemedecine.service;

import com.telemedecine.telemedecine.dto.MedecinDto;
import com.telemedecine.telemedecine.exception.AppException;
import com.telemedecine.telemedecine.exception.ExceptionCode;
import com.telemedecine.telemedecine.exception.FieldError;
import com.telemedecine.telemedecine.mapper.MedecinMapper;
import com.telemedecine.telemedecine.repository.MedecinRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
@Slf4j
public class MedecinService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedecinService.class);

    @Autowired
    private MedecinRepository medecinRepository;

    public Long add(MedecinDto medecinDto){

        log.info("SATRT SERVICE CREATE NEW doctor");
        if (medecinDto == null) {
            throw new AppException(ExceptionCode.API_MISSING_PARAMETER, "medecin required");
        }
        medecinRepository.save(MedecinMapper.toEntity(medecinDto));
        log.info("END SERVICE ADD NEW doctor BY ID: {}", medecinDto.getId());
        return medecinDto.getId();
    }
    public void save(MedecinDto medecinDto) {
        LOGGER.debug("START SERVICE save by id {}",medecinDto.getId());
        medecinRepository.save(MedecinMapper.toEntity(medecinDto));
        LOGGER.debug("END SERVICE save by id {}",medecinDto.getId());
    }
    public void delete(Long id) {
        LOGGER.debug("START SERVICE delete by id {}", id);
        medecinRepository.deleteById(id);
        LOGGER.debug("START SERVICE delete by id {}", id);
    }

    public MedecinDto findById(Long id) throws Exception {
        LOGGER.debug("START SERVICE find by id {}", id);
        return Optional.ofNullable(medecinRepository.findById(id))
                .map(o -> MedecinMapper.toDto(o.get()))
                .orElseThrow(() -> new AppException(ExceptionCode.API_RESOURCE_NOT_FOUND, "doctor not found"));
    }
    public List<MedecinDto> findBySpecialityDesignation(String designation) throws AppException {


        return Optional.ofNullable(medecinRepository.findAllBySpecialityDesignation(designation))
                .map(MedecinMapper::toDtos)

                .orElseThrow(() -> new AppException(ExceptionCode.API_RESOURCE_NOT_FOUND, "medecins not found"));
    }
    public List<MedecinDto> findByHospitalName(String nom) throws AppException {


        return Optional.ofNullable(medecinRepository.findAllByHospitalName(nom))
                .map(MedecinMapper::toDtos)
                .orElseThrow(() -> new AppException(ExceptionCode.API_RESOURCE_NOT_FOUND, "medecins not found"));
    }
}
