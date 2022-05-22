package designPatern.builder;

public class DemoBuilder {
    public static void main(String[] args) {
        try {
            Employe emp1 = new Employe.EmployeBuilder(1, "Jean", "Bernard", "45323").setMail("jean@gmail.com").setTel("0474/474747").build();
            System.out.println(emp1);
        } catch (Exception e) {
            System.out.println("Erreur " + e);
        }
        try {
            Employe emp1 = new Employe.EmployeBuilder(1, "Jean", "Bernard", "45323").setMail("jean@gmail.com").setTel("0474/474747").build();
            System.out.println(emp1);
        } catch (Exception e) {
            System.out.println("Erreur " + e);
        }
    }
}
