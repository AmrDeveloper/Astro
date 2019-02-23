package astro.utils;

public class OSValidator {

    /**
     * Get Operating System name
     */
    private static String OS = System.getProperty("os.name").toLowerCase();

    /**
     * @return : true if current OS is Windows
     */
    public static boolean isWindows() {
        return OS.contains("win");
    }

    /**
     * @return : true if current OS is Mac
     */
    public static boolean isMac() {
        return OS.contains("mac");
    }

    /**
     * @return : true if current OS is Unix or Linux
     */
    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }
}
