package gianni_bussoletti.beuu2s3g2ex.controllers;

import gianni_bussoletti.beuu2s3g2ex.payloads.LoginDTO;
import gianni_bussoletti.beuu2s3g2ex.payloads.LoginResponseDTO;
import gianni_bussoletti.beuu2s3g2ex.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthControllers {

    private final AuthService authService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body) {
        String token = this.authService.credentialControlAndGeneratorToken(body);
        return new LoginResponseDTO(token);
    }

}
