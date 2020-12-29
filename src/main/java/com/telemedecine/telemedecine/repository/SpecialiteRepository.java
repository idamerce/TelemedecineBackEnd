package com.telemedecine.telemedecine.repository;

import com.telemedecine.telemedecine.domain.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {
}
