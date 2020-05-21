package mitov.christian;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

public class Main {
    private static final String NEW_PATH = "C:\\Users\\mitov\\Documents\\CD4";
    private static final String OLD_PATH = "C:\\Users\\mitov\\Documents\\CD2";
    private static final String EXTENSION = ".mp3";
    private static boolean[] isTaken;
    private static Random r = new Random();

    public static void main(String[] args) throws IOException {

        File[] allFiles = getAllFiles();
        isTaken = new boolean[allFiles.length];
        Files.createDirectory(Paths.get(NEW_PATH));
        Arrays.stream(allFiles)
                .forEach(Main::moveFile);
        System.out.println("Success");
    }

    private static void moveFile(File file) {
        int chosenNumber = getChosenNumber();
        file.renameTo(new File(NEW_PATH + "\\" + chosenNumber + "." + file.getName()));
    }

    private static int getChosenNumber() {
        while (true) {

            int nextInt = r.nextInt(isTaken.length);
            if (!isTaken[nextInt]) {
                isTaken[nextInt] = true;
                return nextInt;
            }
        }
    }

    private static File[] getAllFiles() {
        File dir = new File(Main.OLD_PATH);

        return dir.listFiles((dir1, filename) -> filename.endsWith(Main.EXTENSION));
    }
}
