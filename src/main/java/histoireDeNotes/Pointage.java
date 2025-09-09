package histoireDeNotes;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Pointage {
    private LocalDate date;
    private TypeTravail type;
    private BigDecimal quota;
    private String description;
    private String couleur;

    public Pointage(LocalDate date, TypeTravail type, BigDecimal quota, String description, String couleur) {
        this.date = date;
        this.type = type;
        this.quota = quota;
        this.description = description;
        this.couleur = couleur;
    }

    public LocalDate getDate() { return date; }
    public TypeTravail getType() { return type; }
    public BigDecimal getQuota() { return quota; }
    public String getDescription() { return description; }
    public String getCouleur() { return couleur; }
}
