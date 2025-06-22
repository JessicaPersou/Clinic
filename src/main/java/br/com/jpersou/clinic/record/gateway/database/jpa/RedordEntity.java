package br.com.jpersou.clinic.record.gateway.database.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RECORD_RECORDS")
public class RedordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "PATIENT_ID", nullable = false)
    private String patientId;

    @Column(name = "RECORD_TYPE", nullable = false)
    private String recordType;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "RECORD_DATE", nullable = false)
    private LocalDateTime recordDate;

    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

}
