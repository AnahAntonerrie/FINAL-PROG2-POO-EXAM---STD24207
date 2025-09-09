package histoireDeNotes;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Salarie extends Travailleur {
    public Salarie(int id, String nom, String prenom, String email, String telephone,
                   BigDecimal salaireInitial, LocalDate dateInitiale) {
        super(id, nom, prenom, email, telephone);
        ajouterPromotion(new Promotion(dateInitiale, salaireInitial, "Salaire initial"));
    }

}

