package gianni_bussoletti.beuu2s3g2ex.payloads;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record PrenotazioniDTO(
        @NotNull(message = "L'id del dipendente non può essere lasciato vuoto")
        UUID dipendente,
        @NotNull(message = "L'id della prenotazione non può essere lasciato vuoto")
        UUID viaggio,
        @NotBlank(message = "L'alloggio non può essere lasciato vuoto")
        @Size(min = 5, max = 40, message = "Il nome dell'alloggio deve essere compreso fra 5 e 40 caratteri")
        String alloggio,
        @NotBlank(message = "Il mezzo di viaggio non può essere lasciato vuoto")
        String mezzoViaggio,
        @NotNull(message = "Il biglietto deve avere un numero identificato")
        @PositiveOrZero(message = "Il numero del biglietto non può essere un valore negativo")
        int numeroBiglietto,
        @NotNull(message = "La data di richiesta non può essere vuota")
        @PastOrPresent(message = "La data deve essere di oggi o dei giorni passati")
        LocalDate dataRichiesta

) {
}
