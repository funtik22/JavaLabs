import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an input file: ");
        String inputFile = scanner.nextLine();
        System.out.println("Enter an output file: ");
        String outputFile = scanner.nextLine();
        FileProcessor fileProcessor = new FileProcessor(inputFile, outputFile);
        fileProcessor.run();
    }
}