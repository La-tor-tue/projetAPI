package bureau.modele;

import bureau.metier.Projet;

import java.util.ArrayList;

public interface DAO<T> {
    T create(T newObj);

    boolean delete(T delRech);

    T read(T reachRech);

    T update(T upRech);

    ArrayList<T> readAll();

}
