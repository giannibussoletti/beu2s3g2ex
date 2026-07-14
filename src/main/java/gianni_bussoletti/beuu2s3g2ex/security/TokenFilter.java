package gianni_bussoletti.beuu2s3g2ex.security;

import gianni_bussoletti.beuu2s3g2ex.exceptions.UnathorizedException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
@AllArgsConstructor
public class TokenFilter extends OncePerRequestFilter {
    private TokenTools tokenTools;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer "))
            throw new UnathorizedException("Il token è mancante o non valido, ricontrolla di aver inserito il token corretto con 'Bearer ' davanti");

        String token = header.replace("Bearer ", "");
        this.tokenTools.tokenVerify(token);
        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
