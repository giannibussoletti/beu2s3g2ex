package gianni_bussoletti.beuu2s3g2ex.entities;

import gianni_bussoletti.beuu2s3g2ex.enums.MezzoViaggio;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
@ToString
@Table(name = "prenotazioni")
@NoArgsConstructor
public class Prenotazione {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Dipendente dipendente;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Viaggio viaggio;

    @Column(nullable = false)
    private String alloggio;

    @Enumerated(EnumType.STRING)
    @Column(name = "mezzo_viaggio", nullable = false)
    private MezzoViaggio mezzoViaggio;

    @Column(name = "numero_biglietto", nullable = false)
    private int numeroBiglietto;

    @Column(name = "data_richiesta", nullable = false)
    private LocalDate dataRichiesta;

    public Prenotazione(Dipendente dipendente, Viaggio viaggio, String alloggio, MezzoViaggio mezzoViaggio, int numeroBiglietto, LocalDate dataRichiesta) {
        this.dipendente = dipendente;
        this.viaggio = viaggio;
        this.alloggio = alloggio;
        this.mezzoViaggio = mezzoViaggio;
        this.numeroBiglietto = numeroBiglietto;
        this.dataRichiesta = dataRichiesta;
    }
}
