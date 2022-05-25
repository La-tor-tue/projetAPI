package bureau.vue;

import javax.swing.*;

public class VueBaseGraphique implements VueBaseInterface {
    @Override
    public int menu(String[] options) {
        int ch;
        JTextField choiceField = new JTextField(5);
        StringBuilder sb = new StringBuilder(50);
        int i = 0;
        for (String option : options) sb.append((++i) + "." + option + "\n");
        sb.append("choix:");
        do {
            ch = Integer.parseInt(JOptionPane.showInputDialog(sb.toString()));//TODO gérer encodages invalides
            if (ch >= 1 && ch <= options.length) {
                return ch;
            }
        }
        while (true);
    }

    @Override
    public void displayMsg(String msg) {
        JOptionPane.showConfirmDialog(null, msg, "information", JOptionPane.DEFAULT_OPTION);

    }

    @Override
    public String getMsg(String invite, String regex) {
        String rep;
        do {
            rep = JOptionPane.showInputDialog(null, invite, "question", JOptionPane.DEFAULT_OPTION);
        } while (!rep.matches(regex));
        return rep;
    }
}
