package cl.tingeso.mueblesstgo.repositories;

import cl.tingeso.mueblesstgo.entities.ClockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClockRepository extends JpaRepository<ClockEntity, Long> {
}
