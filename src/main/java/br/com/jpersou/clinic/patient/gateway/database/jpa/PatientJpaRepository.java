package br.com.jpersou.clinic.patient.gateway.database.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientJpaRepository extends JpaRepository<PatientEntity, String> {

    // Additional query methods can be defined here if needed

}
