package br.com.jpersou.clinic.patient.gateway;

import br.com.jpersou.clinic.patient.domain.Patient;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface PatientRepository {

    @Transactional(readOnly = true)
    List<Patient> findAll();

    @Transactional(readOnly = true)
    Patient findById(String id);
}
