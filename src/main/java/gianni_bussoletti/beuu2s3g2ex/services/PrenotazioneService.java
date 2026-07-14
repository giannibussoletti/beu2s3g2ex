package gianni_bussoletti.beuu2s3g2ex.services;

import gianni_bussoletti.beuu2s3g2ex.entities.Dipendente;
import gianni_bussoletti.beuu2s3g2ex.entities.Prenotazione;
import gianni_bussoletti.beuu2s3g2ex.entities.Viaggio;
import gianni_bussoletti.beuu2s3g2ex.enums.MezzoViaggio;
import gianni_bussoletti.beuu2s3g2ex.exceptions.DataOccupataException;
import gianni_bussoletti.beuu2s3g2ex.exceptions.MezzoViaggioException;
import gianni_bussoletti.beuu2s3g2ex.payloads.PrenotazioniDTO;
import gianni_bussoletti.beuu2s3g2ex.repositories.PrenotazioneRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PrenotazioneService {
    private PrenotazioneRepository prenotazioneRepository;
    private DipendenteService dipendenteService;
    private ViaggioService viaggioService;

    public Prenotazione saveNewPrenotazione(PrenotazioniDTO payload) {
        Dipendente findDipendente = this.dipendenteService.findById(payload.dipendente());
        Viaggio findViaggio = this.viaggioService.findById(payload.viaggio());
        boolean dipendenteAndData = this.prenotazioneRepository.existsByDipendenteAndData(findDipendente.getId(), payload.dataRichiesta());
        if (dipendenteAndData)
            throw new DataOccupataException("Il dipendente ha già prenotato in un viaggio in questa giornata");

        MezzoViaggio mezzoViaggio = null;
        switch (payload.mezzoViaggio().toLowerCase()) {
            case "treno" -> mezzoViaggio = MezzoViaggio.TRENO;
            case "aereo" -> mezzoViaggio = MezzoViaggio.AEREO;
            case "bus" -> mezzoViaggio = MezzoViaggio.BUS;
            case "nave" -> mezzoViaggio = MezzoViaggio.NAVE;
            case "macchina" -> mezzoViaggio = MezzoViaggio.MACCHINA;
            default -> throw new MezzoViaggioException("La lista dei mezzi validi è \n" +
                    "treno\n" +
                    "aereo\n" +
                    "bus\n" +
                    "nave\n" +
                    "macchina");
        }
        Prenotazione newPrenotazione = new Prenotazione(findDipendente, findViaggio, payload.alloggio(), mezzoViaggio, payload.numeroBiglietto(), payload.dataRichiesta());
        this.prenotazioneRepository.save(newPrenotazione);
        return newPrenotazione;
    }

    public Page<Prenotazione> findAll(int page, int size, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return this.prenotazioneRepository.findAll(pageable);
    }

    public boolean findIfDateExists(UUID id, LocalDate date) {
        return this.prenotazioneRepository.existsByDipendenteAndData(id, date);
    }

}
