package kr.jpamulti.jpa.alert.repository;

import kr.jpamulti.jpa.alert.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<Alert, Long> {
}
