package com.telemedecine.telemedecine.service;

import com.sun.security.auth.UserPrincipal;
import com.telemedecine.telemedecine.domain.Utilisateur;
import com.telemedecine.telemedecine.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UtilisateurRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utilisateur user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
	// This method is used by JWTAuthenticationFilter
	@Transactional
	public UserDetails loadUserById(Long userId){
		Utilisateur user = this.userRepository.findById(userId).orElseThrow(
				() -> new UsernameNotFoundException("User not found with id : " + userId)
		);
		return UserDetailsImpl.build(user);
	}

}
