package gianni_bussoletti.beuu2s3g2ex.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @NotBlank(message = "Il campo username non può essere lasciato vuoto")
        @Size(min = 5, max = 20, message = "L'username deve essere fra i 5 e i 20 caratteri massimo")
        String username,
        @NotBlank(message = "Il campo nome non può essere lasciato vuoto")
        @Size(min = 2, max = 30, message = "Il nome deve essere fra i 2 e i 30 caratteri massimo")
        String nome,
        @NotBlank(message = "Il campo cognome non può essere lasciato vuoto")
        @Size(min = 2, max = 30, message = "Il cognome deve essere fra i 2 e i 30 caratteri massimo")
        String cognome,
        @NotBlank(message = "L'email non può essere vuota")
        @Email(message = "L'email non rispetta i requisiti minimi")
        String email,
        @NotBlank(message = "La password non può essere vuota")
        @Size(min = 8, message = "La password deve avere almeno 8 caratteri")
        String password

) {

}
