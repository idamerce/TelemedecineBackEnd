package com.telemedecine.telemedecine.service;

import com.sun.security.auth.UserPrincipal;
import com.telemedecine.telemedecine.dto.MedecinDto;
import com.telemedecine.telemedecine.dto.reponse.ApiResponse;
import com.telemedecine.telemedecine.dto.reponse.AvailabilityMedecinResponseDto;
import com.telemedecine.telemedecine.dto.request.MedecinAvailabilityRequestDto;
import com.telemedecine.telemedecine.dto.request.TodayAppointmentRequestDto;
import com.telemedecine.telemedecine.exception.AppException;
import com.telemedecine.telemedecine.exception.ExceptionCode;
import com.telemedecine.telemedecine.mapper.MedecinMapper;
import com.telemedecine.telemedecine.mapper.RendezVousMapper;
import com.telemedecine.telemedecine.repository.MedecinRepository;
import com.telemedecine.telemedecine.repository.RendezVousRepository;
import com.telemedecine.telemedecine.utils.TweakResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class MedecinService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedecinService.class);
    private RendezVousMapper rendezVousMapper;
    private TweakResponse tweakResponse;
    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    private RendezVousRepository appointementRepository;
    @Autowired
    private RendezVousMapper appointementMapper;

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
        return Optional.of(medecinRepository.findById(id))
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

    // Display List of daily events of a doctor (Private cause it hold private patient data )
    public Object AllMedecinAvailability(UserDetailsImpl currentUser, @RequestBody TodayAppointmentRequestDto todayAppointment){
        var medecin = this.medecinRepository.findById(currentUser.getId());
        var date = LocalDate.parse(todayAppointment.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if(!medecin.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"Medecin not found."),
                    HttpStatus.NOT_FOUND);
        }
        var isAvailable = this.appointementRepository.findAllByMedecinAndDate(medecin.get(), date);
        if(isAvailable.isEmpty()){
//            return new ResponseEntity<>(new ApiResponse(false,"No event for today."),
//                    HttpStatus.NOT_FOUND);
            return null;
        }
        int size = isAvailable.size();
        var response = this.appointementMapper.toDto(isAvailable, isAvailable.get(0), size);
        return response;
    }


    // Delete Event.
    public Object deleteEvent(String appointmentId){
        var appointment = this.appointementRepository.findById(Long.parseLong(appointmentId));
        if(!appointment.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"Appointment not found."),
                    HttpStatus.NOT_FOUND);
        }
        this.appointementRepository.deleteById(Long.parseLong(appointmentId));
        // Send Email
//        this.mailSender.notifyPatient(false, appointment.get().getMedecin().getFullName() ,appointment.get().getPatient().getEmail(), appointment.get().getDate(), appointment.get().getShiftHoraire().getTimeStart(), appointment.get().getShiftHoraire().getTimeEnd());

        return new ResponseEntity<>(new ApiResponse(true,"Event supprimer avec success"),
                HttpStatus.OK);
    }

    // Change Event Status :
    public Object changeEventStatus(String appointmentId){
        var appointment = this.appointementRepository.findById(Long.parseLong(appointmentId));

        if(!appointment.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"Appointment not found."),
                    HttpStatus.NOT_FOUND);
        }
        var newAppointmentStatus = !appointment.get().isActive();
        appointment.get().setActive(newAppointmentStatus);

        this.appointementRepository.save(appointment.get());

        // Send Email
//        this.mailSender.notifyPatient(newAppointmentStatus, appointment.get().getMedecin().getFullName() ,appointment.get().getPatient().getEmail(), appointment.get().getDate(), appointment.get().getShiftHoraire().getTimeStart(), appointment.get().getShiftHoraire().getTimeEnd());
        return new ResponseEntity<>(new ApiResponse(true,"Event modifier avec success"),
                HttpStatus.OK);
    }
}
