package bureau.vue;

import java.util.ArrayList;
import java.util.List;

public interface VueInterface<T, U> {

    int menu(String[] options);

    void displayMsg(String msg);

    String getMsg(String invite);

    T create();

    void display(T obj);

    T update(T obj);

    U read();

    void affAll(ArrayList<T> lobj);
}
