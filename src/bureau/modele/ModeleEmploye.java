package bureau.modele;

import bureau.metier.Employe;

import java.util.ArrayList;
import java.util.List;

public class ModeleEmploye implements DAOEmploye {

    private List<Employe> employes = new ArrayList<>();

    @Override
    public Employe create(Employe newE) {
        if (employes.contains(newE)) return null;
        employes.add(newE);
        return newE;
    }

    @Override
    public boolean delete(Employe delE) {
        Employe e = read(delE);
        if (e != null) { //AGGREGATION FORTE
            employes.remove(e);
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
    public List<Employe> readAll() {
        return employes;
    }


}
