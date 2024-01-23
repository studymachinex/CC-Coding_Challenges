import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ccwc {
    public static void main(String[] args) {
        String arg = args[0];
        String filePath = args[1];
        
        try {
            if (!fileFound(filePath)) {
                throw new FileNotFoundException("File " + getFileName(args[1]) + " not found");
            }

            processFile(arg, filePath);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    static void countBytesFromFile(String filePath) throws IOException {
        long byteLength = Files.size(getPath(filePath));
        System.out.println(byteLength + getFileName(filePath));
    }

    static void countLinesFromFile(String args) throws IOException {
        try (Stream<String> lines = Files.lines(getPath(args))) {
            long numberOfLines = lines.count();
            System.out.println(numberOfLines + getFileName(args));
            lines.close();
        }
    }

    static void countWordsFromFile(String args) throws IOException {
        String content = Files.readString(getPath(args));
        int wordCount = content.split("\\s+").length;
        System.out.println(wordCount + getFileName(args));
    }

    static void countCharactersFromFile(String args) throws IOException {
        String content = Files.readString(getPath(args));
        long charCount = content.length();
        System.out.println(charCount + getFileName(args));
    }

    static boolean fileFound(String args) {
        return Files.exists(getPath(args));
    }

    static Path getPath(String args) {
        return Paths.get(args);
    }

    static String getFileName(String args) {
        return " " + args;
    }
    
    private static void processFile(String arg, String filePath ) throws IOException {
        switch (arg) {
            case "-c":
                countBytesFromFile(filePath);
                break;
            case "-l":
                countLinesFromFile(filePath);
                break;
            case "-w":
                countWordsFromFile(filePath);
                break;
            case "-m":
                countCharactersFromFile(filePath);
                break;
            default:
                System.out.println("Invalid option: " +arg);
                break;
        }
    }
}
