package gianni_bussoletti.beuu2s3g2ex.services;

import gianni_bussoletti.beuu2s3g2ex.entities.Dipendente;
import gianni_bussoletti.beuu2s3g2ex.exceptions.EmailExistsInDBException;
import gianni_bussoletti.beuu2s3g2ex.exceptions.NotFoundException;
import gianni_bussoletti.beuu2s3g2ex.exceptions.UsernameAlreadyExistsException;
import gianni_bussoletti.beuu2s3g2ex.payloads.DipendenteDTO;
import gianni_bussoletti.beuu2s3g2ex.repositories.DipendeteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class DipendenteService {

    private DipendeteRepository dipendeteRepository;

    //    SAVING POST REQUEST
    public Dipendente save(DipendenteDTO payload) {
        if (this.dipendeteRepository.existsByEmail(payload.email()))
            throw new EmailExistsInDBException("L'email è già presente nel Database");
        if (this.dipendeteRepository.existsByUsername(payload.username()))
            throw new UsernameAlreadyExistsException("Questo username esiste già nel Database");

        Dipendente newDipendente = new Dipendente(payload.username(), payload.nome(), payload.cognome(), payload.email(), payload.password());
        return this.dipendeteRepository.save(newDipendente);

    }

    //    GET RESPONSE ALL DIPENDENTI
    public Page<Dipendente> findAll(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.dipendeteRepository.findAll(pageable);
    }

    public Dipendente findById(UUID id) {
        Dipendente found = this.dipendeteRepository.findDipendenteById(id);
        if (found != null) return found;
        else throw new NotFoundException("Dipendente non trovato");

    }

    public Dipendente findByEmail(String email) {
        return this.dipendeteRepository.findDipendenteByEmail(email).orElseThrow(() -> new NotFoundException("nessun dipendente con questa email trovato"));
    }

}
