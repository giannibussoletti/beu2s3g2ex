package gianni_bussoletti.beuu2s3g2ex.payloads;

import java.time.LocalDateTime;
import java.util.UUID;

public record DipendenteResponseDTO(String msg, UUID id, LocalDateTime createdAt) {
}
