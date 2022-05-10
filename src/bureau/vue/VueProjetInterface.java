package bureau.vue;

import bureau.metier.Projet;

import java.time.LocalDate;
import java.util.ArrayList;

public interface VueProjetInterface extends VueInterface<Projet, Integer> {
    void affListOBJ(ArrayList<Projet> listObj);

    LocalDate getDate(String invite);
}
