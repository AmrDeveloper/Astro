package astro.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class TerminalUtils {

    private static final String DEBUG_TAG = TerminalUtils.class.getSimpleName();
    private static final Logger debug = Logger.getLogger(DEBUG_TAG);

    public static int openTerminal() {
        if (OSValidator.isWindows()) {
            return openWindowsTerminal();
        } else if (OSValidator.isUnix()) {
            return openUnixTerminal();
        }
        return -1;
    }

    public static int openTerminalHere(File file) {
        if (OSValidator.isWindows()) {
            return openWindowsTerminalHere(file);
        } else if (OSValidator.isUnix()) {
            return openUnixTerminalHere(file);
        }
        return -1;
    }

    /**
     * @return : 0 if windows terminal is opened without errors
     */
    private static int openWindowsTerminal() {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("cmd.exe /c start", null);
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }

    /**
     * @param file : open windows terminal in file path
     * @return : 0 if terminal is opened without errors
     */
    private static int openWindowsTerminalHere(File file) {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("cmd.exe /c start", null, file);
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }

    /**
     * @return : 0 if Unix terminal is opened without errors
     */
    private static int openUnixTerminal() {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("bash -c start", null);
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }

    /**
     * @param file : open Unix terminal in file path
     * @return : 0 if terminal is opened without errors
     */
    private static int openUnixTerminalHere(File file) {
        try {
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("bash -c start", null, file);
            return 0;
        } catch (IOException e) {
            return 1;
        }
    }
}
