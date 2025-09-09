package histoireDeNotes;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Promotion {
    private LocalDate date;
    private BigDecimal valeur;
    private String raison;

    public Promotion(LocalDate date, BigDecimal valeur, String raison) {
        this.date = date;
        this.valeur = valeur;
        this.raison = raison;
    }

    public LocalDate getDate() { return date; }
    public BigDecimal getValeur() { return valeur; }
    public String getRaison() { return raison; }
}
