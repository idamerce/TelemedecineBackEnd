package com.telemedecine.telemedecine.service;

import com.telemedecine.telemedecine.domain.Medecin;
import com.telemedecine.telemedecine.domain.Patient;
import com.telemedecine.telemedecine.domain.RendezVous;
import com.telemedecine.telemedecine.dto.reponse.ApiResponse;
import com.telemedecine.telemedecine.dto.reponse.AvailabilityMedecinResponseDto;
import com.telemedecine.telemedecine.dto.request.MedecinAvailabilityRequestDto;
import com.telemedecine.telemedecine.dto.request.NewAppointmentRequestDto;
import com.telemedecine.telemedecine.mapper.RendezVousMapper;
import com.telemedecine.telemedecine.repository.MedecinRepository;
import com.telemedecine.telemedecine.repository.PatientRepository;
import com.telemedecine.telemedecine.repository.RendezVousRepository;
import com.telemedecine.telemedecine.repository.ShiftHoraireRepository;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
@Service
//@Transactional
@Slf4j
public class publicService {
    private final static Logger LOGGER = LoggerFactory.getLogger(MedecinService.class);

    private MedecinRepository medecinRepository;
    private PatientRepository patientRepository;
    private ShiftHoraireRepository shiftHoraireRepository;
    private RendezVousRepository appointementRepository;
    private RendezVousMapper appointementMapper;
    private TweakResponse tweakResponse;

   @Autowired
   public publicService(MedecinRepository medecinRepository, PatientRepository patientRepository, ShiftHoraireRepository shiftHoraireRepository, RendezVousRepository appointementRepository, RendezVousMapper appointementMapper, TweakResponse tweakResponse) {
        this.medecinRepository = medecinRepository;
        this.patientRepository = patientRepository;
        this.shiftHoraireRepository = shiftHoraireRepository;
        this.appointementRepository = appointementRepository;

        this.appointementMapper = appointementMapper;
        this.tweakResponse = tweakResponse;
    }

    // List all available Medecin
    public List<Medecin> listAllMedecin(){
        return this.medecinRepository.findAll();
    }

    // Get list of availability of a doctor by day.

    public List<AvailabilityMedecinResponseDto> listAllMedecinAvailability(MedecinAvailabilityRequestDto medecinAvailability){

        var medecin = this.medecinRepository.findById(medecinAvailability.getMedecinId());
        var date = LocalDate.parse(medecinAvailability.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if(!medecin.isPresent()){
            return null;
        }
        var medecinAvailabilities = this.appointementRepository.findAllByMedecinAndDate(medecin.get(), date);

        var medecinAvailabilityList =
                medecinAvailabilities.stream().sorted((a1, a2) -> {
                    System.out.printf("sort: %s; %s\n", a1, a2);
                    return a1.getShiftHoraire().getId().compareTo(a2.getShiftHoraire().getId());
                })
                        .map(a -> this.appointementMapper.toAvailabilityMedecinDto(a))
                        .collect(Collectors.toList());

        return this.tweakResponse.listAllAvailiblityByStatus(medecinAvailabilityList);
    }

    // Add new appointement;
    public Object save(NewAppointmentRequestDto newAppointment){

       var medecin = medecinRepository.findById(newAppointment.getMedecinId());
        var shiftHoraire = this.shiftHoraireRepository.findById(newAppointment.getShiftTimeId());
        var date = LocalDate.parse(newAppointment.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));


        if(!medecin.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"Medecin non trouvé."),
                    HttpStatus.NOT_FOUND);
        }
        if(!shiftHoraire.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"Time shift does not exist, please try again."),
                    HttpStatus.BAD_REQUEST);
        }

        var isAvailable = this.appointementRepository.findByMedecinAndDateAndShiftHoraire(medecin.get(), date, shiftHoraire.get());
        // Check if not already take.
        if(!isAvailable.isPresent()){
            return new ResponseEntity<>(new ApiResponse(false,"Time shift already taken, please choose another one."),
                    HttpStatus.BAD_REQUEST);
        }
        var patientName = newAppointment.getName();
        //var patientEmail = newAppointment.getEmail();

        // Finally perform the save operation
        Patient patient = new Patient(patientName);
        Patient savedPatient = this.patientRepository.save(patient);
        RendezVous appointement = new RendezVous(medecin.get(), savedPatient,shiftHoraire.get(), date, false);
        this.appointementRepository.save(appointement);

        return appointement;
    }

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

