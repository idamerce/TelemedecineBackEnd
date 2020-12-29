package com.telemedecine.telemedecine.Controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.telemedecine.telemedecine.dto.RendezVousDto;
import com.telemedecine.telemedecine.dto.request.NewAppointmentRequestDto;
import com.telemedecine.telemedecine.dto.views.UserView;
import com.telemedecine.telemedecine.exception.AppException;
import com.telemedecine.telemedecine.service.RendezVousService;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rendezVous")
public class RendezVousResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(RendezVousResource.class);

    private final RendezVousService rendezVousService;
    @Autowired
    private com.telemedecine.telemedecine.service.publicService publicService;


    public RendezVousResource(RendezVousService rendezVousService) {
        this.rendezVousService = rendezVousService;
    }
    @PostMapping
    @JsonView(UserView.Basic.class)
    public Long addAppointement(@RequestBody RendezVousDto RendezVousDto) throws AppException {
        // String username = AuthenticationHelper.getCurrentUsername();
        LOGGER.debug("START RESOURCE ADD DOCTOR BY USER: {}");
        Long rvId = rendezVousService.add(RendezVousDto);
        LOGGER.debug("END RESOURCE ADD DOCTOR BY USER: {}, REACTED ID: {}", rvId);
        return rvId;
    }
    //  Add new appointement;
    @PostMapping("appointment/save")
    public ResponseEntity<?> save(@Valid @RequestBody NewAppointmentRequestDto newAppointment, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException("Appointment has errors; Can not save the appointment;");
        }

        var saveAppointement = this.publicService.save(newAppointment);
        return ResponseEntity.ok(saveAppointement);

    }
    @PutMapping
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody RendezVousDto RendezVousDto) throws AppException {
        LOGGER.debug("START RESOURCE update rv by name : {}, id: {}", RendezVousDto.getId());
        rendezVousService.save(RendezVousDto);
        LOGGER.debug("END RESOURCE update rv by id : {}, name: {} is ok", RendezVousDto.getId());
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public RendezVousDto get(@PathVariable Long id) throws Exception {
        LOGGER.debug("START RESOURCE find rv by id : {}", id);
        RendezVousDto RendezVousDto = rendezVousService.findById(id);
        LOGGER.debug("END RESOURCE find rv by id : {}, name :{}", id);
        return RendezVousDto;
    }

    @GetMapping
    @JsonView(UserView.Basic.class)
    public List<RendezVousDto> getAllByDoctorAndDay(@PathVariable Long idMed,@PathVariable Date date) throws AppException {
        LOGGER.debug("START RESOURCE all find rvs");
        List<RendezVousDto> RendezVousDtos = rendezVousService.findAppointmentByDoctorByDay(idMed,date);
        LOGGER.debug("END RESOURCE find all rvs, size: {}", RendezVousDtos.size());
        return RendezVousDtos;
}}
