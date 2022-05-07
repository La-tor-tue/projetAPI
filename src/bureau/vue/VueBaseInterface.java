package bureau.vue;

public interface VueBaseInterface {
    int menu(String[] options);

    void displayMsg(String msg);

    String getMsg(String invite, String regex);


}
