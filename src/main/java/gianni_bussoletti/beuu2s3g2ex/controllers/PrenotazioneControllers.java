package gianni_bussoletti.beuu2s3g2ex.controllers;

import gianni_bussoletti.beuu2s3g2ex.entities.Prenotazione;
import gianni_bussoletti.beuu2s3g2ex.exceptions.ValidationException;
import gianni_bussoletti.beuu2s3g2ex.payloads.PrenotazioneResponseDTO;
import gianni_bussoletti.beuu2s3g2ex.payloads.PrenotazioniDTO;
import gianni_bussoletti.beuu2s3g2ex.services.PrenotazioneService;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
@AllArgsConstructor
public class PrenotazioneControllers {
    private PrenotazioneService prenotazioneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERADMIN'")
    public PrenotazioneResponseDTO saveNewPrenotazione(@RequestBody @Validated PrenotazioniDTO payload, BindingResult validation) {
        if (validation.hasErrors()) {
            List<String> validationErrors = validation.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            throw new ValidationException(validationErrors);
        }

        Prenotazione newPrenotazione = this.prenotazioneService.saveNewPrenotazione(payload);
        return new PrenotazioneResponseDTO(newPrenotazione.getId(), "Prenotazione Aggiunta correttamente", LocalDateTime.now());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERADMIN')")
    public Page<Prenotazione> findAllPrenotazioni(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "alloggio") String orderBy
    ) {
        return this.prenotazioneService.findAll(page, size, orderBy);
    }
}
