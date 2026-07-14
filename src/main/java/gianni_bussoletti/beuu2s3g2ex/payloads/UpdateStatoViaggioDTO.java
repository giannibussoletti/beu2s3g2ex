package gianni_bussoletti.beuu2s3g2ex.payloads;

import jakarta.validation.constraints.NotBlank;

public record UpdateStatoViaggioDTO(
        @NotBlank(message = "Il campo non può essere lasciato vuoto")
        String statoViaggio) {
}
