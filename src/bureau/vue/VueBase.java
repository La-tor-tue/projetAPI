package bureau.vue;

import java.util.Scanner;

public class VueBase implements VueBaseInterface {


    @Override
    public int menu(String[] options) {
        Scanner sc = new Scanner(System.in);
        do {
            StringBuilder sb = new StringBuilder(50);
            int i = 0;
            for (String option : options) sb.append((++i) + "." + option + "\n");
            int ch = Integer.parseInt(getMsg("choix", "[0-9]"));
            if (ch >= 1 && ch <= options.length) {
                return ch;
            }
            System.out.println("choix invalide");
        } while (true);
    }

    @Override
    public void displayMsg(String msg) {
        System.out.println(msg);
    }

    @Override
    public String getMsg(String invite, String regex) {
        String msg;
        do {
            System.out.println(invite);
            Scanner sc = new Scanner(System.in);
            msg = sc.nextLine();
        } while (!msg.matches(regex));
        return msg;
    }

}
