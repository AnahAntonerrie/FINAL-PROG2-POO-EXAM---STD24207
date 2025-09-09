import histoireDeNotes.Pointage;
import histoireDeNotes.Prestataire;
import histoireDeNotes.TypeTravail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PrestataireTest {

    @Test
    public void testPointageRouge_exactementUnJour() {
        Prestataire pr = new Prestataire(
                1, "Anah", "Andrianah", "adrianah@hei.mail", "0340000000",
                new BigDecimal("100.00"), LocalDate.of(2025,1,1)
        );

        LocalDate jour = LocalDate.of(2025, 9, 1);
        pr.ajouterPointage(new Pointage(jour, TypeTravail.ENSEIGNEMENT,  new BigDecimal("0.5"), "Cours matin", "#FF0000"));
        pr.ajouterPointage(new Pointage(jour, TypeTravail.ADMINISTRATION, new BigDecimal("0.5"), "Admin aprem", "#FF0000"));

        Assertions.assertTrue(pr.pointageRouge(jour));
    }

    @Test
    public void testGetDaysRed_toutSimple() {
        Prestataire pr = new Prestataire(
                2, "Anah", "Adrianah", "adrianah@hei.mail", "0340600141",
                new BigDecimal("100.00"), LocalDate.of(2025,1,1)
        );

        pr.ajouterPointage(new Pointage(LocalDate.of(2025,5,15), TypeTravail.COMMUNICATION, new BigDecimal("1.0"), "Com", "#00FF00"));
        pr.ajouterPointage(new Pointage(LocalDate.of(2025,6,10), TypeTravail.RD,            new BigDecimal("1.0"), "R&D", "#00FF00"));

        BigDecimal days = pr.getDaysRed(LocalDate.of(2025,5,1), LocalDate.of(2025,6,30));

        Assertions.assertEquals(0, days.compareTo(new BigDecimal("2.0")));
    }

    @Test
    public void testCalculerSalaire_sansPromotion() {
        Prestataire pr = new Prestataire(
                3, "Mika", "Andry", "mika@hei.mail", "0340101302",
                new BigDecimal("100.00"), LocalDate.of(2025,1,1)
        );

        pr.ajouterPointage(new Pointage(LocalDate.of(2025,5,15), TypeTravail.COMMUNICATION, new BigDecimal("1.0"), "Com", "#00FF00"));
        pr.ajouterPointage(new Pointage(LocalDate.of(2025,6,10), TypeTravail.RD,            new BigDecimal("1.0"), "R&D", "#00FF00"));

        BigDecimal salaire = pr.calculerSalaire(LocalDate.of(2025,5,1), LocalDate.of(2025,6,30));
        Assertions.assertEquals(0, salaire.compareTo(new BigDecimal("200.00")));
    }
}
