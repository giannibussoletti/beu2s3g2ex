package gianni_bussoletti.beuu2s3g2ex.security;

import gianni_bussoletti.beuu2s3g2ex.entities.Dipendente;
import gianni_bussoletti.beuu2s3g2ex.exceptions.UnathorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenTools {
    private String secret;

    public TokenTools(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    public String tokenGenerator(Dipendente dipendente) {
        return Jwts.builder()
                .issuedAt(new Date((System.currentTimeMillis())))
                .expiration(new Date((System.currentTimeMillis()) + 1000 * 60 * 60 * 24))
                .subject(String.valueOf(dipendente.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }


    public void tokenVerify(String token) {
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception ex) {
            throw new UnathorizedException("Il token di login ha avuto un problema, per favore rieffetturare il login");
        }
    }
}
