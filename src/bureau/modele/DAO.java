package bureau.modele;

import java.util.List;

public interface DAO<T> {
    T create(T newObj);

    boolean delete(T delRech);

    T read(T reachRech);

    T update(T upRech);

    List<T> readAll();

}
