package br.com.jpersou.clinic.patient.config.mapper;

import br.com.jpersou.clinic.patient.adapters.request.PatientRequest;
import br.com.jpersou.clinic.patient.adapters.response.PatientResponse;
import br.com.jpersou.clinic.patient.domain.Patient;
import br.com.jpersou.clinic.patient.gateway.database.jpa.PatientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {

    PatientResponse toResponse(Patient patient);

    Patient toDomain(PatientResponse patientResponse);

    PatientEntity toEntity(Patient patient);

    Patient toDomain(PatientEntity patientEntity);

    PatientRequest toRequest(Patient patient);

    Patient toDomain(PatientRequest patientRequest);


}
