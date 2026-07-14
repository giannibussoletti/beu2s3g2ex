package gianni_bussoletti.beuu2s3g2ex.controllers;

import gianni_bussoletti.beuu2s3g2ex.entities.Dipendente;
import gianni_bussoletti.beuu2s3g2ex.services.DipendenteService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dipendenti")
@AllArgsConstructor
public class DipendenteControllers {

    private final DipendenteService dipendenteService;


    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Page<Dipendente> getAllDipendenti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "nome") String orderBy
    ) {
        return this.dipendenteService.findAll(page, size, orderBy);
    }
}
