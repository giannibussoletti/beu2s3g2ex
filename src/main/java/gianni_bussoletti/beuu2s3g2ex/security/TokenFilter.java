package gianni_bussoletti.beuu2s3g2ex.security;

import gianni_bussoletti.beuu2s3g2ex.entities.Dipendente;
import gianni_bussoletti.beuu2s3g2ex.exceptions.UnathorizedException;
import gianni_bussoletti.beuu2s3g2ex.services.DipendenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;


@Component
@AllArgsConstructor
public class TokenFilter extends OncePerRequestFilter {
    private TokenTools tokenTools;
    private DipendenteService dipendenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer "))
            throw new UnathorizedException("Il token è mancante o non valido, ricontrolla di aver inserito il token corretto con 'Bearer ' davanti");

        String token = header.replace("Bearer ", "");
        this.tokenTools.tokenVerify(token);

        UUID id = this.tokenTools.extractId(token);
        Dipendente authDipendente = this.dipendenteService.findById(id);
        Authentication authentication = new UsernamePasswordAuthenticationToken(authDipendente, null, authDipendente.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);


        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
