package histoireDeNotes;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Prestataire extends Travailleur {


    public Prestataire(int id, String nom, String prenom, String email, String telephone,
                       BigDecimal tjmInitial, LocalDate dateInitiale) {
        super(id, nom, prenom, email, telephone);
        ajouterPromotion(new Promotion(dateInitiale, tjmInitial, "TJM initial"));
    }

    public BigDecimal calculerSalaire(LocalDate debut, LocalDate fin) {
        BigDecimal totalSalaire = BigDecimal.ZERO;

        for (Pointage pointage : getPointages()) {
            LocalDate dateTravail = pointage.getDate();

            // On vérifie si la date est dans la période demandée
            boolean dansLaPeriode =
                    (dateTravail.isEqual(debut) || dateTravail.isAfter(debut)) &&
                            (dateTravail.isEqual(fin) || dateTravail.isBefore(fin));

            if (dansLaPeriode) {
                // On ignore les absences
                if (pointage.getType() == TypeTravail.ABS_PAYEE ||
                        pointage.getType() == TypeTravail.ABS_NON_PAYEE) {
                    continue;
                }
                Promotion promo = promotionActiveA(dateTravail);

                BigDecimal tjm = (promo != null) ? promo.getValeur() : BigDecimal.ZERO;

                totalSalaire = totalSalaire.add(tjm.multiply(pointage.getQuota()));
            }
        }

        return totalSalaire.setScale(2, java.math.RoundingMode.HALF_UP);
    }
}
