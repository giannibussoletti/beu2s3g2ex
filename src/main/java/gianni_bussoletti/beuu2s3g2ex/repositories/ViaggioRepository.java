package gianni_bussoletti.beuu2s3g2ex.repositories;

import gianni_bussoletti.beuu2s3g2ex.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, UUID> {

    Viaggio findViaggioById(UUID viaggioID);
}
