package astro.utils;

import astro.ui.DialogUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class TerminalUtils {

    private static final String DEBUG_TAG = TerminalUtils.class.getSimpleName();
    private static final Logger debug = Logger.getLogger(DEBUG_TAG);

    public static void openTerminal() {
        if (OSValidator.isWindows()) {
            openWindowsTerminal();
        } else if (OSValidator.isUnix()) {
            openUnixTerminal();
        }
    }

    public static void openTerminalHere(File file) {
        if (OSValidator.isWindows()) {
            openWindowsTerminalHere(file);
        } else if (OSValidator.isUnix()) {
            openUnixTerminalHere(file);
        }
    }

    private static void openWindowsTerminal() {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("cmd.exe /c start", null);
        } catch (IOException e) {
            final String errorMessage = "Can't Launch Windows Terminal";
            //Debugging Console
            debug.warning(errorMessage);
            //Warning UI
            DialogUtils.createErrorDialog(DialogUtils.ERROR_DIALOG, null, errorMessage);
        }
    }

    /**
     * @param file : open windows terminal in file path
     */
    private static void openWindowsTerminalHere(File file) {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("cmd.exe /c start", null, file);
        } catch (IOException e) {
            final String errorMessage = "Can't Launch Windows Terminal Here";
            //Debugging Console
            debug.warning(errorMessage);
            //Warning UI
            DialogUtils.createErrorDialog(DialogUtils.ERROR_DIALOG, null, errorMessage);
        }
    }

    private static void openUnixTerminal() {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("bash -c start", null);
        } catch (IOException e) {
            final String errorMessage = "Can't Launch Unix Based Terminal";
            //Debugging Console
            debug.warning(errorMessage);
            //Warning UI
            DialogUtils.createErrorDialog(DialogUtils.ERROR_DIALOG, null, errorMessage);
        }
    }

    /**
     * @param file : open Unix terminal in file path
     */
    private static void openUnixTerminalHere(File file) {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("bash -c start", null, file);
        } catch (IOException e) {
            final String errorMessage = "Can't Launch Unix Based Terminal Here";
            //Debugging Console
            debug.warning(errorMessage);
            //Warning UI
            DialogUtils.createErrorDialog(DialogUtils.ERROR_DIALOG, null, errorMessage);
        }
    }
}
