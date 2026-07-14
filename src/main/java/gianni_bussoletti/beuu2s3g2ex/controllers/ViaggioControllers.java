package gianni_bussoletti.beuu2s3g2ex.controllers;

import gianni_bussoletti.beuu2s3g2ex.entities.Viaggio;
import gianni_bussoletti.beuu2s3g2ex.exceptions.ValidationException;
import gianni_bussoletti.beuu2s3g2ex.payloads.UpdateStatoViaggioDTO;
import gianni_bussoletti.beuu2s3g2ex.payloads.ViaggioDTO;
import gianni_bussoletti.beuu2s3g2ex.payloads.ViaggioResponseDTO;
import gianni_bussoletti.beuu2s3g2ex.services.ViaggioService;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
@AllArgsConstructor
public class ViaggioControllers {

    private ViaggioService viaggioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ViaggioResponseDTO saveViaggio(@RequestBody @Validated ViaggioDTO payload, BindingResult validation) {
        if (validation.hasErrors()) {
            List<String> validationErrors = validation.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            throw new ValidationException(validationErrors);
        }
        Viaggio newViaggio = this.viaggioService.saveNewViaggio(payload);
        return new ViaggioResponseDTO("Viaggio salvato correttamemente", newViaggio.getId(), LocalDateTime.now());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Page<Viaggio> findAllViaggi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "destinazione") String orderBy) {
        return this.viaggioService.findAllViaggi(page, size, orderBy);
    }

    @PatchMapping("/{statoViaggio}/stato")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatoViaggio(@PathVariable UUID statoViaggio, @RequestBody @Validated UpdateStatoViaggioDTO payload) {
        this.viaggioService.findByIdAndUpdate(statoViaggio, payload.statoViaggio());
    }

}
