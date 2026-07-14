package gianni_bussoletti.beuu2s3g2ex.controllers;

import gianni_bussoletti.beuu2s3g2ex.entities.Dipendente;
import gianni_bussoletti.beuu2s3g2ex.exceptions.ValidationException;
import gianni_bussoletti.beuu2s3g2ex.payloads.DipendenteDTO;
import gianni_bussoletti.beuu2s3g2ex.payloads.DipendenteResponseDTO;
import gianni_bussoletti.beuu2s3g2ex.services.DipendenteService;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/dipendenti")
@AllArgsConstructor
public class DipendenteControllers {


    private final DipendenteService dipendenteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERADMIN'")
    public DipendenteResponseDTO save(@RequestBody @Validated DipendenteDTO payload, BindingResult validation) {
        if (validation.hasErrors()) {
            List<String> errorsMessage = validation.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            throw new ValidationException(errorsMessage);
        }

        Dipendente save = this.dipendenteService.save(payload);
        return new DipendenteResponseDTO("Dipendente aggiunto al Database", save.getId(), LocalDateTime.now());
    }

    @GetMapping("/me")
    public Dipendente getOwnProfile(@AuthenticationPrincipal Dipendente authDipendente) {
        return authDipendente;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPERADMIN')")
    public Page<Dipendente> getAllDipendenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "nome") String orderBy
    ) {
        return this.dipendenteService.findAll(page, size, orderBy);
    }
}
