package gianni_bussoletti.beuu2s3g2ex.controllers;

import gianni_bussoletti.beuu2s3g2ex.entities.Dipendente;
import gianni_bussoletti.beuu2s3g2ex.exceptions.ValidationException;
import gianni_bussoletti.beuu2s3g2ex.payloads.DipendenteDTO;
import gianni_bussoletti.beuu2s3g2ex.payloads.DipendenteResponseDTO;
import gianni_bussoletti.beuu2s3g2ex.payloads.LoginDTO;
import gianni_bussoletti.beuu2s3g2ex.payloads.LoginResponseDTO;
import gianni_bussoletti.beuu2s3g2ex.services.AuthService;
import gianni_bussoletti.beuu2s3g2ex.services.DipendenteService;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthControllers {

    private final AuthService authService;
    private final DipendenteService dipendenteService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body) {
        String token = this.authService.credentialControlAndGeneratorToken(body);
        return new LoginResponseDTO(token);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DipendenteResponseDTO save(@RequestBody @Validated DipendenteDTO payload, BindingResult validation) {
        if (validation.hasErrors()) {
            List<String> errorsMessage = validation.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            throw new ValidationException(errorsMessage);
        }

        Dipendente save = this.dipendenteService.save(payload);
        return new DipendenteResponseDTO("Dipendente aggiunto al Database", save.getId(), LocalDateTime.now());
    }
}
