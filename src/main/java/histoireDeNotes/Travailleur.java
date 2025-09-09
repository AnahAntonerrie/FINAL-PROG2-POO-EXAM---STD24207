package histoireDeNotes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Travailleur {
    private int identifiant;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;

    protected final List<Promotion> listePromotions = new ArrayList<>();
    protected final List<Pointage> listePointages = new ArrayList<>();

    public Travailleur(int identifiant, String nom, String prenom, String email, String telephone) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
    }

    public void ajouterPromotion(Promotion promotion) {
        listePromotions.add(promotion);
    }

    public void ajouterPointage(Pointage pointage) {
        listePointages.add(pointage);
    }

    public boolean pointageRouge(LocalDate date) {
        BigDecimal total = BigDecimal.ZERO;

        for (Pointage pointage : listePointages) {
            if (pointage.getDate().equals(date)) {
                BigDecimal quota = pointage.getQuota();

                if (quota.compareTo(BigDecimal.ZERO) <= 0) {
                    return false;
                }
                if (quota.compareTo(BigDecimal.ONE) > 0) {
                    return false;
                }

                total = total.add(quota);
            }
        }

        return total.compareTo(BigDecimal.ONE) == 0;
    }

    public BigDecimal getDaysRed(LocalDate debut, LocalDate fin) {
        BigDecimal total = BigDecimal.ZERO;

        for (Pointage pointage : listePointages) {
            LocalDate dateTravail = pointage.getDate();

            boolean estDansPeriode =
                    (dateTravail.isEqual(debut) || dateTravail.isAfter(debut)) &&
                            (dateTravail.isEqual(fin)   || dateTravail.isBefore(fin));

            if (estDansPeriode) {
                boolean estAbsence =
                        pointage.getType() == TypeTravail.ABS_PAYEE ||
                                pointage.getType() == TypeTravail.ABS_NON_PAYEE;

                if (!estAbsence) {
                    total = total.add(pointage.getQuota());
                }
            }
        }

        return total;
    }

    protected Promotion promotionActiveA(LocalDate date) {
        Promotion dernierePromotion = null;

        for (Promotion promotion : listePromotions) {
            if (!promotion.getDate().isAfter(date)) {
                if (dernierePromotion == null || promotion.getDate().isAfter(dernierePromotion.getDate())) {
                    dernierePromotion = promotion;
                }
            }
        }

        return dernierePromotion;
    }

    public int getIdentifiant() { return identifiant; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public String getTelephone() { return telephone; }

    public List<Pointage> getPointages() {
        return listePointages;
    }

    public List<Promotion> getPromotions() {
        return listePromotions;
    }
}

