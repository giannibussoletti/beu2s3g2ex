package gianni_bussoletti.beuu2s3g2ex.repositories;

import gianni_bussoletti.beuu2s3g2ex.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DipendeteRepository extends JpaRepository<Dipendente, UUID> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Dipendente findDipendenteById(UUID dipendenteId);

    Optional<Dipendente> findDipendenteByEmail(String email);
}
