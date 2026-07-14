package gianni_bussoletti.beuu2s3g2ex.exceptions;

import gianni_bussoletti.beuu2s3g2ex.payloads.ErrorsDTO;
import gianni_bussoletti.beuu2s3g2ex.payloads.ErrorsWithListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
public class ErrorHanders {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsWithListDTO handleValidationErrors(ValidationException ex) {
        return new ErrorsWithListDTO(ex.getMessage(), LocalDateTime.now(), ex.getErrorsList());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleNotFound(NotFoundException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }


    @ExceptionHandler(EmailExistsInDBException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleEmailExists(EmailExistsInDBException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleUsernameExists(UsernameAlreadyExistsException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(StatoViaggioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handleStatoViaggio(StatoViaggioException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(MezzoViaggioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handlerMezzoViaggio(Exception ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(DataOccupataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO handlerMezzoViaggio(DataOccupataException ex) {
        return new ErrorsDTO(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO handlerGeneralException(Exception ex) {
        System.out.println(Arrays.toString(ex.getStackTrace()));
        return new ErrorsDTO("C'è stato un errore nel server", LocalDateTime.now());
    }


}
