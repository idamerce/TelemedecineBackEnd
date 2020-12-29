package com.telemedecine.telemedecine.Controller;

import com.sun.security.auth.UserPrincipal;
import com.telemedecine.telemedecine.dto.reponse.TodayAppointmentResponseDto;
import com.telemedecine.telemedecine.dto.request.TodayAppointmentRequestDto;
import com.telemedecine.telemedecine.mapper.RendezVousMapper;
import com.telemedecine.telemedecine.repository.MedecinRepository;
import com.telemedecine.telemedecine.repository.RendezVousRepository;
import com.telemedecine.telemedecine.security.CurrentUser;
import com.telemedecine.telemedecine.service.MedecinService;
import com.telemedecine.telemedecine.service.UserDetailsImpl;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/private")
@CrossOrigin
public class PrivateController {
    private MedecinRepository medecinRepository;
    private RendezVousRepository appointementRepository;

    private RendezVousMapper appointementMapper;
    private MedecinService medecinService;

    @Autowired
    public PrivateController(MedecinRepository medecinRepository, RendezVousRepository appointementRepository, RendezVousMapper appointementMapper, MedecinService medecinService) {
        this.medecinRepository = medecinRepository;
        this.appointementRepository = appointementRepository;

        this.appointementMapper = appointementMapper;
        this.medecinService = medecinService;
    }

    // Display List of daily events of a doctor (Private cause it hold private patient data )
    @PreAuthorize("hasRole('MEDECIN')")
    @PostMapping("events/daily")
    public ResponseEntity<? extends Object> today(@CurrentUser UserDetailsImpl currentUser, @Valid @RequestBody TodayAppointmentRequestDto todayAppointment, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ValidationException("Appointment has errors; Can not update the status of the appointment;");
        }
        var date = LocalDate.parse(todayAppointment.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        var response = this.medecinService.AllMedecinAvailability(currentUser, todayAppointment);
        if(response==null)
            return ResponseEntity.ok(new TodayAppointmentResponseDto(date, 0, null));
        return ResponseEntity.ok(response);

    }

    // Delete an appointment
    @PreAuthorize("hasRole('MEDECIN')")
    @DeleteMapping("events/{appointmentId}")
    public ResponseEntity<?> delete(@PathVariable String appointmentId){

        var response =this.medecinService.deleteEvent(appointmentId);
        return ResponseEntity.ok(response);
    }

    // Change the status of an event
    @PreAuthorize("hasRole('MEDECIN')")
    @PostMapping("events/status/{appointmentId}")
    public ResponseEntity<?> status(@PathVariable String appointmentId){

        var response = this.medecinService.changeEventStatus(appointmentId);
        return ResponseEntity.ok(response);
    }
}
