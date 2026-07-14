package gianni_bussoletti.beuu2s3g2ex.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @Email(message = "L'email non rispetta i requisiti necessari")
        @NotBlank(message = "il campo non può essere lasciato vuoto")
        String email,
        @NotBlank(message = "Il campo non può essere lasciato vuoto")
        String password) {
}
