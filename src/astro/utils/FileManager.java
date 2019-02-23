package astro.utils;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    @Nullable
    public static File createNewFile(String filePath) {
        try {
            File newFolder = new File(filePath);
            newFolder.createNewFile();
            return newFolder;
        } catch (IOException e) {
            return null;
        }
    }

    @Nullable
    public static File createNewFolder(String filePath) {
        File newFolder = new File(filePath);
        newFolder.mkdir();
        return newFolder;
    }

    @Nullable
    public static File openSourceFile(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        return fileChooser.showOpenDialog(null);
    }

    @Nullable
    public static File openSourceDir(String title) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle(title);
        return chooser.showDialog(null);
    }

    @Nullable
    public static File saveSourceFile(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        return fileChooser.showSaveDialog(null);
    }

    public static void updateContent(File file, String content) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }

    public static void deleteFile(File file) {
        file.deleteOnExit();
    }
}
