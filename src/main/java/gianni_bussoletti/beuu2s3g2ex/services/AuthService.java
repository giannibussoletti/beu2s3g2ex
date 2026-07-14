package gianni_bussoletti.beuu2s3g2ex.services;

import gianni_bussoletti.beuu2s3g2ex.entities.Dipendente;
import gianni_bussoletti.beuu2s3g2ex.exceptions.UnathorizedException;
import gianni_bussoletti.beuu2s3g2ex.payloads.LoginDTO;
import gianni_bussoletti.beuu2s3g2ex.security.TokenTools;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class AuthService {

    private final DipendenteService dipendenteService;
    private final TokenTools jwTokenController;
    private PasswordEncoder bcrypt;

    public String credentialControlAndGeneratorToken(@RequestBody LoginDTO body) {


        Dipendente found = this.dipendenteService.findByEmail(body.email());
        if (this.bcrypt.matches(body.password(), found.getPassword())) {
            return this.jwTokenController.tokenGenerator(found);
        } else throw new UnathorizedException("Le credenziali non sono valide");
    }
}
