package histoireDeNotes;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Prestataire extends Travailleur {

    public Prestataire(int id, String nom, String prenom, String email, String telephone,
                       BigDecimal tjmInitial, LocalDate dateInitiale) {
        super(id, nom, prenom, email, telephone);
        Promotion premierePromotion = new Promotion(dateInitiale, tjmInitial, "TJM initial");
        ajouterPromotion(premierePromotion);
    }

    public BigDecimal calculerSalaire(LocalDate debut, LocalDate fin) {
        BigDecimal totalSalaire = BigDecimal.ZERO;

        for (Pointage p : getPointages()) {
            LocalDate dateTravail = p.getDate();

            boolean apresDebut = dateTravail.isEqual(debut) || dateTravail.isAfter(debut);
            boolean avantFin   = dateTravail.isEqual(fin)   || dateTravail.isBefore(fin);

            if (apresDebut && avantFin) {
                if (p.getType() != TypeTravail.ABS_PAYEE && p.getType() != TypeTravail.ABS_NON_PAYEE) {
                    Promotion promo = promotionActiveA(dateTravail);

                    if (promo != null) {
                        BigDecimal tjm = promo.getValeur();
                        BigDecimal salaireJour = tjm.multiply(p.getQuota());
                        totalSalaire = totalSalaire.add(salaireJour);
                    }
                }
            }
        }

        return totalSalaire.setScale(2, java.math.RoundingMode.HALF_UP);
    }
}
