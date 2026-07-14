package gianni_bussoletti.beuu2s3g2ex.services;

import gianni_bussoletti.beuu2s3g2ex.entities.Dipendente;
import gianni_bussoletti.beuu2s3g2ex.exceptions.UnathorizedException;
import gianni_bussoletti.beuu2s3g2ex.payloads.LoginDTO;
import gianni_bussoletti.beuu2s3g2ex.security.TokenTools;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class AuthService {

    private final DipendenteService dipendenteService;
    private final TokenTools jwTokenController;

    public String credentialControlAndGeneratorToken(@RequestBody LoginDTO body) {


        Dipendente found = this.dipendenteService.findByEmail(body.email());
        if (found.getPassword().equals(body.password())) {
            return this.jwTokenController.tokenGenerator(found);
        } else throw new UnathorizedException("Le credenziali non sono valide");
    }
}
