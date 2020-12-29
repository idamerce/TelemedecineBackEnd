package com.telemedecine.telemedecine.Controller;

import com.telemedecine.telemedecine.dto.reponse.ApiResponse;
import com.telemedecine.telemedecine.dto.request.MedecinAvailabilityRequestDto;
import com.telemedecine.telemedecine.dto.request.NewAppointmentRequestDto;
import com.telemedecine.telemedecine.mapper.RendezVousMapper;
import com.telemedecine.telemedecine.repository.MedecinRepository;
import com.telemedecine.telemedecine.repository.PatientRepository;
import com.telemedecine.telemedecine.repository.RendezVousRepository;
import com.telemedecine.telemedecine.repository.ShiftHoraireRepository;
import com.telemedecine.telemedecine.service.publicService;
import com.telemedecine.telemedecine.utils.TweakResponse;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class PublicController {

    @Autowired
    private com.telemedecine.telemedecine.service.publicService publicService;
    private MedecinRepository medecinRepository;
    private PatientRepository patientRepository;
    private ShiftHoraireRepository shiftHoraireRepository;
    private RendezVousRepository appointementRepository;

    private RendezVousMapper appointementMapper;
    private TweakResponse tweakResponse;

    @Autowired
    public PublicController(MedecinRepository medecinRepository, PatientRepository patientRepository, ShiftHoraireRepository shiftHoraireRepository, RendezVousRepository appointementRepository, RendezVousMapper appointementMapper, TweakResponse tweakResponse) {
        this.medecinRepository = medecinRepository;
        this.patientRepository = patientRepository;
        this.shiftHoraireRepository = shiftHoraireRepository;
        this.appointementRepository = appointementRepository;

        this.appointementMapper = appointementMapper;
        this.tweakResponse = tweakResponse;
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
    // EndPoint : List all available Medecin
    @GetMapping("/medecin")
    public ResponseEntity<? extends Object> listMedecin(){
        var ListMedecin = this.publicService.listAllMedecin();
        return ResponseEntity.ok(ListMedecin);
    }

    // End Point : Get list of availability of a doctor by day.
    @PostMapping("/availability")
    public ResponseEntity<? extends Object> listMedecinAvailability(@Valid @RequestBody MedecinAvailabilityRequestDto medecinAvailability, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException("Availabilities has errors; Can not fetch all medecin availabilities;");
        }
        var result = this.publicService.listAllMedecinAvailability(medecinAvailability);

        if(result==null)
            return new ResponseEntity<>(new ApiResponse(false,"Medecin not found.."),
                    HttpStatus.NOT_FOUND);

        return ResponseEntity.ok(result);
    }

}
