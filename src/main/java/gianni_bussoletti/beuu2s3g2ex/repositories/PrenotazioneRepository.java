package gianni_bussoletti.beuu2s3g2ex.repositories;

import gianni_bussoletti.beuu2s3g2ex.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {

    @Query("SELECT COUNT(p) > 0 FROM Prenotazione p WHERE p.dipendente.id = :dipendenteID AND p.dataRichiesta = :dataRichiesta")
    boolean existsByDipendenteAndData(@Param("dipendenteID") UUID dipendenteID,
                                      @Param("dataRichiesta") LocalDate date);


}
