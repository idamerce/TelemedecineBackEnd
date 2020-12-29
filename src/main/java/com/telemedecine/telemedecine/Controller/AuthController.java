package com.telemedecine.telemedecine.Controller;

import java.util.*;
import java.util.stream.Collectors;

import com.telemedecine.telemedecine.domain.Role;
import com.telemedecine.telemedecine.domain.RoleName;
import com.telemedecine.telemedecine.domain.Utilisateur;
import com.telemedecine.telemedecine.dto.reponse.JwtResponse;
import com.telemedecine.telemedecine.dto.reponse.MessageResponse;
import com.telemedecine.telemedecine.jwt.JwtUtils;
import com.telemedecine.telemedecine.dto.request.LoginRequest;
import com.telemedecine.telemedecine.dto.request.SignupRequest;
import com.telemedecine.telemedecine.repository.RoleRepository;
import com.telemedecine.telemedecine.repository.UtilisateurRepository;

import com.telemedecine.telemedecine.service.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UtilisateurRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	private final Logger log = LoggerFactory.getLogger(AuthController.class);


	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		Utilisateur user = userRepository.findByUsername(loginRequest.getLogin()).get();
		log.info("user object: {}",user);

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		log.info("roles : {}",roles);

		List<Role> roles1 = userRepository.findByUsername(loginRequest.getLogin()).get().getRoles();

		JwtResponse response = new JwtResponse(jwt,userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getNom(),
				userDetails.getPrenom(),
				userDetails.getVille(),
				userDetails.getAdresse(),
				userDetails.getSexe(),
				userDetails.getCin(),
				userDetails.getDateNaissance(),roles1);
		return ResponseEntity.ok(response);
	}
	@PutMapping("/customer/{id}")
	public ResponseEntity<?> updateUtilisateur(@PathVariable("id") long id, @RequestBody SignupRequest signUpRequest) {


		System.out.println("Update User with ID = " + id + "...");

		Optional<Utilisateur> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			Utilisateur _user = userData.get();
			_user .setLogin(signUpRequest.getLogin());
			_user .setNom(signUpRequest.getNom());
			_user .setPrenom(signUpRequest.getPrenom());
			_user .setSexe(signUpRequest.getSexe());
			_user .setCin(signUpRequest.getCin());
			_user .setAdresse(signUpRequest.getAdresse());
			_user .setDateNaissance(signUpRequest.getDateNaissence());
			_user .setPassword(signUpRequest.getPassword());
			//_user .setVille(signUpRequest.getVille());
			_user .setPassword(encoder.encode(signUpRequest.getPassword()));

			return new ResponseEntity<>(userRepository.save(_user ), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {

		log.info("Registering user with infos : {}",signUpRequest);

		if (userRepository.existsByUsername(signUpRequest.getLogin())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: login is already taken!"));
		}

		//if (userRepository.existsByphoneNumber(signUpRequest.getPhoneNumber())) {
		//	return ResponseEntity
		//			.badRequest()
		//			.body(new MessageResponse("Error: phone number is already in use!"));
		//}

		// Create new user's account
		Utilisateur user = new Utilisateur(
				signUpRequest.getLogin(),
				signUpRequest.getNom(),
				signUpRequest.getPrenom(),
				signUpRequest.getSexe(),
				signUpRequest.getVille(),
				signUpRequest.getDateNaissence(),
				signUpRequest.getCin(),
				signUpRequest.getAdresse(),
				encoder.encode(signUpRequest.getPassword()));

		List<String> strRoles = signUpRequest.getRole();
		List<Role> roles = new ArrayList<>();

		if (strRoles == null) {
			Role clientRole = roleRepository.findByName(RoleName.Role_Patient)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(clientRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "patient":
						Role adminRole = roleRepository.findByName(RoleName.Role_Patient)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "medecin":
						Role modRole = roleRepository.findByName(RoleName.Role_Medecin)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);

						break;
					default:
						Role userRole = roleRepository.findByName(RoleName.Role_User)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		log.info("Register success");

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@GetMapping("/roles")
	public ResponseEntity<List<Role>> roles() {

		log.info("requesting all roles...");

		List<Role> roles = roleRepository.findAll();

		log.info("Roles : {}",roles);
		return ResponseEntity.ok(roles);
	}


}
