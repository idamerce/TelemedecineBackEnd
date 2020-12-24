package com.telemedecine.telemedecine.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.telemedecine.telemedecine.dto.MedecinDto;
import com.telemedecine.telemedecine.dto.PatientDto;
import com.telemedecine.telemedecine.dto.views.UserView;
import com.telemedecine.telemedecine.exception.AppException;
import com.telemedecine.telemedecine.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientResource.class);

    private final com.telemedecine.telemedecine.service.PatientService PatientService;

    public PatientResource(com.telemedecine.telemedecine.service.PatientService PatientService) {
        this.PatientService = PatientService;
    }

    @PostMapping
    @JsonView(UserView.Basic.class)
    public Long addPatient(@RequestBody PatientDto patientDto) throws AppException {
        // String username = AuthenticationHelper.getCurrentUsername();
        LOGGER.debug("START RESOURCE ADD PAtient BY USER: {}");
        Long patientId = PatientService.add(patientDto);
        LOGGER.debug("END RESOURCE ADD PAtient BY USER: {}, REACTED ID: {}", patientId);
        return patientId;
    }


    @PutMapping
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody PatientDto PatientDto) throws AppException {
        LOGGER.debug("START RESOURCE update doctor by name : {}, id: {}", PatientDto.getNom(), PatientDto.getId());
        PatientService.save(PatientDto);
        LOGGER.debug("END RESOURCE update doctor by id : {}, name: {} is ok", PatientDto.getId(), PatientDto.getNom());
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public PatientDto get(@PathVariable Long id) throws Exception {
        LOGGER.debug("START RESOURCE find doctor by id : {}", id);
        PatientDto PatientDto = PatientService.findById(id);
        LOGGER.debug("END RESOURCE find doctor by id : {}, name :{}", id, PatientDto.getNom());
        return PatientDto;
    }

    @GetMapping
    @JsonView(UserView.Basic.class)
    public List<PatientDto> getAll() throws AppException {
        LOGGER.debug("START RESOURCE all find doctors");
        List<PatientDto> PatientDtos = PatientService.findAll();
        LOGGER.debug("END RESOURCE find all doctors, size: {}", PatientDtos.size());
        return PatientDtos;
    }
}
