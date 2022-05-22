package bureau.modele;

import bureau.metier.Discipline;
import java.util.ArrayList;

public class ModeleDiscipline implements DAODiscipline {

    private ArrayList<Discipline> disciplines = new ArrayList<>();

    @Override
    public Discipline create(Discipline newD) {
        if (disciplines.contains(newD)) return null;
        disciplines.add(newD);
        return newD;
    }

    @Override
    public boolean delete(Discipline delD) {
        Discipline d = read(delD);
        if (d != null && d.getListEmploye().isEmpty()) {
            disciplines.remove(d);
            return true;
        } else return false;
    }

    @Override
    public Discipline read(Discipline rechD) {
        int idDis = rechD.getIdDis();
        for (Discipline d : disciplines) {
            if (d.getIdDis() == idDis)
                return d;
        }
        return null;
    }

    @Override
    public Discipline update(Discipline upD) {
        Discipline d = read(upD);
        if (d == null) return null;

        d.setDesc(upD.getDesc());
        d.setNom(upD.getNom());
        d.setListEmploye(upD.getListEmploye());
        return d;
    }

    @Override
    public ArrayList<Discipline> readAll() {
        return disciplines;
    }


}
