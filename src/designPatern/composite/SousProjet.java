package designPatern.composite;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class SousProjet {
    private int idpj;

    public SousProjet(int idpj) {
        this.idpj = idpj;
    }

    public int getIdpj() {
        return idpj;
    }

    public abstract float prixTotal();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SousProjet that = (SousProjet) o;
        return idpj == that.idpj;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idpj);
    }
}
