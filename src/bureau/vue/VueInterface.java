package bureau.vue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface VueInterface<T, U> {

    int menu(String[] options);

    void displayMsg(String msg);

    default String getMsg(String invite, String regex) {
        String msg;
        do {
            System.out.println(invite);
            Scanner sc = new Scanner(System.in);
            msg = sc.nextLine();
        } while (!msg.matches(regex));
        return msg;
    }

    T create();

    void display(T obj);

    T update(T obj);

    U read();

    void affAll(List<T> lobj);
}
