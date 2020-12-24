package com.telemedecine.telemedecine.service;

import com.telemedecine.telemedecine.dto.MedecinDto;
import com.telemedecine.telemedecine.dto.RendezVousDto;
import com.telemedecine.telemedecine.exception.AppException;
import com.telemedecine.telemedecine.exception.ExceptionCode;
import com.telemedecine.telemedecine.mapper.MedecinMapper;
import com.telemedecine.telemedecine.mapper.RendezVousMapper;
import com.telemedecine.telemedecine.repository.MedecinRepository;
import com.telemedecine.telemedecine.repository.RendezVousRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class RendezVousService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RendezVousService.class);
    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    private RendezVousRepository rendezVousRepository;
    public Long add(RendezVousDto rendezVousDto){

        log.info("SATRT SERVICE CREATE NEW rendez vous");
        if (rendezVousDto == null) {
            throw new AppException(ExceptionCode.API_MISSING_PARAMETER, "rendez vous required");
        }
        rendezVousRepository.save(RendezVousMapper.toEntity(rendezVousDto));
        log.info("END SERVICE ADD NEW appointement BY ID: {}", rendezVousDto.getId());
        return rendezVousDto.getId();
    }
    public void save(RendezVousDto rendezVousDto) {
        LOGGER.debug("START SERVICE save by id {}",rendezVousDto.getId());
        rendezVousRepository.save(RendezVousMapper.toEntity(rendezVousDto));
        LOGGER.debug("END SERVICE save by id {}",rendezVousDto.getId());
    }
    public void delete(Long id) {
        LOGGER.debug("START SERVICE delete by id {}", id);
        rendezVousRepository.deleteById(id);
        LOGGER.debug("START SERVICE delete by id {}", id);
    }
    public RendezVousDto findById(Long id) throws Exception {
        LOGGER.debug("START SERVICE find by id {}", id);
        return Optional.ofNullable(rendezVousRepository.findById(id))
                .map(o -> RendezVousMapper.toDto(o.get()))
                .orElseThrow(() -> new AppException(ExceptionCode.API_RESOURCE_NOT_FOUND, "appointementnot found"));
    }
    public List<RendezVousDto> findAppointmentByDoctorByDay(long idMedecin, Date jour) throws AppException {


        return Optional.ofNullable(rendezVousRepository.findAppointmentByDoctorByDay(idMedecin, jour))
                .map(RendezVousMapper::toDtos)

                .orElseThrow(() -> new AppException(ExceptionCode.API_RESOURCE_NOT_FOUND, "appointements not found"));
    }
    public Long countAllAppointments() {
        // TODO add condition > current day, active appointment
        return rendezVousRepository.count();
    }

}
