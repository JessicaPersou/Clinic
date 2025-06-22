package br.com.jpersou.clinic.patient.adapters;

import static org.springframework.http.HttpStatus.OK;

import br.com.jpersou.clinic.patient.adapters.response.PatientResponse;
import br.com.jpersou.clinic.patient.application.PatientUseCase;
import br.com.jpersou.clinic.patient.config.mapper.PatientMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
public class PatientController {

    private final PatientUseCase patientUseCase;
    private final PatientMapper patientMapper;

    @GetMapping
    public List<PatientResponse> findAll() {
        return patientUseCase.findAllPatients()
            .stream()
            .map(patientMapper::toResponse)
            .toList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public PatientResponse findById(@PathVariable String id) {
        var patientReponse = patientUseCase.findPatientById(id);
        return patientMapper.toResponse(patientReponse);
    }
}
