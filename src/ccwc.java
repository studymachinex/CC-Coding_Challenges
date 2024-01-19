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
            if (!fileFound(args)) {
                throw new FileNotFoundException("File " + getFileName(args) + " not found");
            }

            processFile(args);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    static void argumentC(String[] args) throws IOException {
        Path path = getPath(args);
        long byteLength = Files.size(path);
        System.out.println(byteLength + getFileName(args));
    }

    static void argumentL(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(getPath(args))) {
            long numberOfLines = lines.count();
            System.out.println(numberOfLines + getFileName(args));
        }
    }

    static void argumentW(String[] args) throws IOException {
        String content = Files.readString(getPath(args));
        int wordCount = content.split("\\s+").length;
        System.out.println(wordCount + getFileName(args));
    }

    static void argumentM(String[] args) throws IOException {
        String content = Files.readString(getPath(args));
        long charCount = content.length();
        System.out.println(charCount + getFileName(args));
    }

    static boolean fileFound(String[] args) {
        Path path = getPath(args);
        return Files.exists(path);
    }

    static Path getPath(String[] args) {
        return Paths.get(args[1]);
    }

    static String getFileName(String[] args) {
        return " " + args[1];
    }
    
    private static void processFile(String[] args) throws IOException {
        switch (args[0]) {
            case "-c":
                argumentC(args);
                break;
            case "-l":
                argumentL(args);
                break;
            case "-w":
                argumentW(args);
                break;
            case "-m":
                argumentM(args);
                break;
            default:
                System.out.println("Invalid option: " + args[0]);
                break;
        }
    }
}
