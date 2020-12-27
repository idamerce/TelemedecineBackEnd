package com.telemedecine.telemedecine.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.telemedecine.telemedecine.dto.MedecinDto;
import com.telemedecine.telemedecine.dto.PatientDto;
import com.telemedecine.telemedecine.dto.views.UserView;
import com.telemedecine.telemedecine.exception.AppException;
import com.telemedecine.telemedecine.service.MedecinService;
import com.telemedecine.telemedecine.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medecin")
public class MedecinResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedecinResource.class);

    private final MedecinService MedecinService;

    public MedecinResource(MedecinService MedecinService) {
        this.MedecinService = MedecinService;
    }

    @PostMapping
    @JsonView(UserView.Basic.class)
    public Long addMedecin(@RequestBody MedecinDto medecinDto) throws AppException {
       // String username = AuthenticationHelper.getCurrentUsername();
        LOGGER.debug("START RESOURCE ADD DOCTOR BY USER: {}");
        Long medecinId = MedecinService.add(medecinDto);
        LOGGER.debug("END RESOURCE ADD DOCTOR BY USER: {}, REACTED ID: {}", medecinId);
        return medecinId;
    }
    @PutMapping
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody MedecinDto MedecinDto) throws AppException {
        LOGGER.debug("START RESOURCE update doctor by name : {}, id: {}", MedecinDto.getNom(), MedecinDto.getId());
        MedecinService.save(MedecinDto);
        LOGGER.debug("END RESOURCE update doctor by id : {}, name: {} is ok", MedecinDto.getId(), MedecinDto.getNom());
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public MedecinDto get(@PathVariable Long id) throws Exception {
        LOGGER.debug("START RESOURCE find doctor by id : {}", id);
        MedecinDto MedecinDto = MedecinService.findById(id);
        LOGGER.debug("END RESOURCE find doctor by id : {}, name :{}", id, MedecinDto.getNom());
        return MedecinDto;
    }

    @GetMapping
    @JsonView(UserView.Basic.class)
    public List<MedecinDto> getAllByDesignation(@PathVariable String nom) throws AppException {
        LOGGER.debug("START RESOURCE all find doctors");
        List<MedecinDto> MedecinDtos = MedecinService.findBySpecialityDesignation(nom);
        LOGGER.debug("END RESOURCE find all doctors, size: {}", MedecinDtos.size());
        return MedecinDtos;
    }
    @GetMapping
    @JsonView(UserView.Basic.class)
    public List<MedecinDto> getAllByHospitalName(@PathVariable String nom) throws AppException {
        LOGGER.debug("START RESOURCE all find doctors");
       List<MedecinDto> MedecinDtos = MedecinService.findByHospitalName(nom);
        LOGGER.debug("END RESOURCE find all doctors, size: {}", MedecinDtos.size());
        return MedecinDtos;
    }

    @DeleteMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public void delete(@PathVariable Long id) throws AppException {
        LOGGER.debug("START RESOURCE delete doctor by id : {}", id);
        MedecinService.delete(id);
        LOGGER.debug("END RESOURCE delete doctor by id : {}, is ok", id);
    }
}
