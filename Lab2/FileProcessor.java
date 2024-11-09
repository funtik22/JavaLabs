import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileProcessor{

    private final File inputFile;
    private final File outputFile;

    public FileProcessor(){
        inputFile = new File("data/inputFile.txt");
        outputFile = new File("data/outputFile.txt");
    }

    public FileProcessor(String inputFileName, String outputFileName){
        inputFile = new File(inputFileName);
        outputFile = new File(outputFileName);
    }

    public FileProcessor(File inputFile, File outputFile){
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    private void checkOutputFile(boolean createFile){
        if(!outputFile.exists() && createFile){
            try{
                if(outputFile.getParentFile().mkdirs() && outputFile.createNewFile()){
                    System.out.println("Created new file: " + outputFile.getPath());
                }
            }
            catch (IOException e){
                System.out.println("Error when created file: " + outputFile.getPath());
                Runtime.getRuntime().exit(1);
            }
        }
        if(!outputFile.isFile()){
            System.out.println("The " + outputFile.getPath() + " isn't a file");
            Runtime.getRuntime().exit(1);
        }
        if(!outputFile.canWrite()){
            System.out.println("There isn't permissions to write the file: " + outputFile.getPath());
            Runtime.getRuntime().exit(1);
        }
    }

    private void checkInputFile(){
        if(!inputFile.isFile()){
            System.out.println("The " + inputFile.getPath() + " isn't a file");
            Runtime.getRuntime().exit(1);
        }
        if(!inputFile.canRead()){
            System.out.println("There isn't permissions to read the file: " + inputFile.getPath());
            Runtime.getRuntime().exit(1);
        }
    }

    private HashMap<Character, Integer> readFile(){
        HashMap<Character, Integer> dict = new HashMap<>();
        try(FileReader fileReader = new FileReader(inputFile)) {
            int charCode;
            char c;
            while ((charCode=fileReader.read()) != -1) {
                c = (char)charCode;
                if (Character.toLowerCase(c)>='a' && Character.toLowerCase(c)<='z') {
                    dict.put(c, dict.getOrDefault(c, 0)+1);
                }
            }
        }
        catch (FileNotFoundException notFindError){
            System.out.println("The " + inputFile.getPath() + " doesn't exist");
            Runtime.getRuntime().exit(1);
        }
        catch (IOException e){
            System.out.println("Error when read the file: " + inputFile.getPath());
            Runtime.getRuntime().exit(1);
        }
        return dict;
    }

    private void writeFile(HashMap<Character, Integer> dict){
        try(FileWriter fileWriter = new FileWriter(outputFile)){
            for(Map.Entry<Character, Integer> entry: dict.entrySet()){
                fileWriter.write(entry.getKey()+": "+entry.getValue() + "\n");
            }
        }
        catch (FileNotFoundException notFindError){
            System.out.println("The " + outputFile.getPath() + " doesn't exist");
            Runtime.getRuntime().exit(1);
        }
        catch (IOException e){
            System.out.println("Error when read the file: " + outputFile.getPath());
            Runtime.getRuntime().exit(1);
        }
    }

    public void run(){
        checkInputFile();
        checkOutputFile(true);
        HashMap<Character, Integer> dict = readFile();
        writeFile(dict);
        System.out.println("Successful");
    }
}