package bureau.modele;

import bureau.metier.Discipline;
import bureau.metier.Employe;
import java.util.ArrayList;

public class ModeleEmploye implements DAOEmploye {

    private ArrayList<Employe> employes = new ArrayList<>();

    @Override
    public Employe create(Employe newE) {
        if (employes.contains(newE)) return null;
        employes.add(newE);
        Discipline tmp = newE.getExpertise();
        tmp.getListEmploye().add(newE);
        return newE;
    }

    @Override
    public boolean delete(Employe delE) {
        Employe e = read(delE);
        if (e != null) {
            employes.remove(e);
            e.getExpertise().getListEmploye().remove(e);
            return true;
        } else return false;
    }

    @Override
    public Employe read(Employe rechE) {
        int idE = rechE.getIdEmp();
        for (Employe e : employes) {
            if (e.getIdEmp() == idE) return e;
        }
        return null;
    }

    @Override
    public Employe update(Employe upE) {
        Employe e = read(upE);
        if (e == null) return null;
        e.setMatricule(upE.getMatricule());
        e.setExpertise(upE.getExpertise());
        e.setMail(upE.getMail());
        e.setNom(upE.getNom());
        e.setPrenom(upE.getPrenom());
        e.setTel(upE.getTel());
        return e;
    }

    @Override
    public ArrayList<Employe> readAll() {
        return employes;
    }


}
