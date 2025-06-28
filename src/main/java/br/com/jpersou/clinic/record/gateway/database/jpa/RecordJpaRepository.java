package br.com.jpersou.clinic.record.gateway.database.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordJpaRepository extends JpaRepository<RecordEntity, Long> {
}
