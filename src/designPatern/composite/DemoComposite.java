package designPatern.composite;

public class DemoComposite {
    public static void main(String[] args) {
        GroupeProjet gp1 = new GroupeProjet(1, "Proximus Fibre");
        GroupeProjet gp2 = new GroupeProjet(2, "Web C#");
        Projet p1 = new Projet(3, "Fibre Mons", 1200000);
        Projet p2 = new Projet(4, "Fibre Tournai", 670000);
        Projet p3 = new Projet(5, "Fibre Ath", 450000);
        Projet p4 = new Projet(6, "Web Maintenance", 20000);
        Projet p5 = new Projet(7, "Web BD", 30000);

        gp1.getSousProjetSet().add(gp2);
        gp1.getSousProjetSet().add(p1);
        gp1.getSousProjetSet().add(p2);
        gp1.getSousProjetSet().add(p3);
        gp2.getSousProjetSet().add(p4);
        gp2.getSousProjetSet().add(p5);

        System.out.println(gp1);

    }
}
