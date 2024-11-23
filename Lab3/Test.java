import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter FIO:");
        String FIO = scanner.nextLine();
        System.out.println("Enter date of birth (in format: 10.12.1993)");
        String DateBirth = scanner.nextLine();

        User user = new User(FIO, DateBirth);
        System.out.println(user);
    }
}
