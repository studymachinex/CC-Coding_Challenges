import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ccwc {
    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                System.out.println("Usage: java ccwc -[c|l|w|m] <file>");
                return;
            }
            if (!fileFound(args[1])) {
                throw new FileNotFoundException("File " + getFileName(args[1]) + " not found");
            }

            processFile(args);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    static void countBytesFromFile(String filePath) throws IOException {
        Path path = getPath(filePath);
        long byteLength = Files.size(path);
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
        Path path = getPath(args);
        return Files.exists(path);
    }

    static Path getPath(String args) {
        return Paths.get(args);
    }

    static String getFileName(String args) {
        return " " + args;
    }
    
    private static void processFile(String[] args) throws IOException {
        switch (args[0]) {
            case "-c":
                countBytesFromFile(args[1]);
                break;
            case "-l":
                countLinesFromFile(args[1]);
                break;
            case "-w":
                countWordsFromFile(args[1]);
                break;
            case "-m":
                countCharactersFromFile(args[1]);
                break;
            default:
                System.out.println("Invalid option: " + args[0]);
                break;
        }
    }
}
