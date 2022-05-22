package designPatern.observer;

import sun.util.resources.LocaleData;

import java.time.LocalDate;

public class DemoObserver {
    public static void main(String[] args) {
        LocalDate tmp = LocalDate.of(2020, 12, 21);
        Projet p1 = new Projet("Proximus Fibre", tmp);
        Employe e1 = new Employe(1, "Jean", "Bernard", "86757", "0474/474747", "Jean@gmail.com");
        Employe e2 = new Employe(1, "Stephaner", "Paul", "98794", "0474/303030", "Paul@gmail.com");

        p1.addObserver(e1);
        p1.addObserver(e2);

        tmp = LocalDate.of(2022, 12, 21);
        p1.setDateFin(tmp);
    }
}
