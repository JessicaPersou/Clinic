package br.com.jpersou.clinic.patient.gateway;

import br.com.jpersou.clinic.patient.config.mapper.PatientMapper;
import br.com.jpersou.clinic.patient.domain.Patient;
import br.com.jpersou.clinic.patient.gateway.database.jpa.PatientJpaRepository;
import br.com.jpersou.clinic.shared.exceptions.ResourceNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepository {

    public static final String PATIENT_NOT_FOUND_WITH_ID = "Patient not found with ID: ";
    public static final String PATIENT_ID_CANNOT_BE_NULL_OR_BLANK = "Patient ID cannot be null or blank";

    private final PatientJpaRepository patientJpaRepository;
    private final PatientMapper patientMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Patient> findAll() {
        return patientJpaRepository.findAll()
            .stream()
            .peek(System.out::println)
            .map(patientMapper::toDomain)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Patient findById(String id) {
        if (id == null || id.isBlank()) {
            throw new ResourceNotFoundException(PATIENT_ID_CANNOT_BE_NULL_OR_BLANK);
        }
        var patientEntity = patientJpaRepository.findById(id).orElseThrow(() ->
            new ResourceNotFoundException(PATIENT_NOT_FOUND_WITH_ID + id));
        return patientMapper.toDomain(patientEntity);
    }
}
