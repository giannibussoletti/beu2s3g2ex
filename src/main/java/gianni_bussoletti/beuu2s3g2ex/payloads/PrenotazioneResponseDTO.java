package gianni_bussoletti.beuu2s3g2ex.payloads;

import java.time.LocalDateTime;
import java.util.UUID;

public record PrenotazioneResponseDTO(UUID id, String message, LocalDateTime createdAt) {
}
