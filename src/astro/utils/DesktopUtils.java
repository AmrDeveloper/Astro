package astro.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class DesktopUtils {

    /**
     * @param directory : The Path To Open it in new window
     */
    public static void openFolderExplore(File directory){
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(directory);
        } catch (IllegalArgumentException iae) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
