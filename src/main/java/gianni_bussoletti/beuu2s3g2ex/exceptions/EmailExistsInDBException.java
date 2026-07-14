package gianni_bussoletti.beuu2s3g2ex.exceptions;

public class EmailExistsInDBException extends RuntimeException {
    public EmailExistsInDBException(String message) {
        super(message);
    }
}
