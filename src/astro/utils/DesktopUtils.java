package astro.utils;

import astro.ui.DialogUtils;

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
        } catch (IllegalArgumentException | IOException e) {
            String errorMessage = "File Not Found";
            DialogUtils.createErrorDialog(DialogUtils.ERROR_DIALOG,null,errorMessage);
        }
    }
}
