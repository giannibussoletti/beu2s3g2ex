package gianni_bussoletti.beuu2s3g2ex.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ViaggioDTO(
        @NotBlank(message = "La destinazione non può essere vuota")
        String destinazione,
        @Future(message = "Il giorno del viaggio deve essere seguente a quello di oggi")
        LocalDate dataViaggio,
        @NotBlank(message = "Lo stato del viaggio non può essere vuoto")
        String statoViaggio) {
}
