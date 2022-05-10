package bureau.vue;

import bureau.metier.Projet;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface VueProjetInterface extends VueInterface<Projet, Integer> {
    void affListOBJ(List listObj);

    LocalDate getDate(String invite);
}
