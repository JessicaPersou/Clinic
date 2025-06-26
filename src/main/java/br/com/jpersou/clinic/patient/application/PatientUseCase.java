package br.com.jpersou.clinic.patient.application;

import br.com.jpersou.clinic.patient.domain.Patient;
import br.com.jpersou.clinic.patient.gateway.PatientRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientUseCase {

    private final PatientRepository patientRepository;

    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    public Patient findPatientById(String id) {
        return patientRepository.findById(id);
    }

    public Patient createPatient(Patient patient) {
        patient = patient.withCreatedAt(LocalDateTime.now());
        return patientRepository.create(patient);
    }
}
