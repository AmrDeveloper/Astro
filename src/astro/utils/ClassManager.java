package astro.utils;

import astro.constants.ClassKind;

public class ClassManager {

    private final static String CLASS_DEFAULT_FORMAT = "public class %s{\n\n}";
    private final static String INTERFACE_DEFAULT_FORMAT = "public interface %s{\n\n}";
    private final static String ENUM_DEFAULT_FORMAT = "public enum %s{\n\n}";
    private final static String ANNOTATION_DEFAULT_FORMAT = "public @interface %s{\n\n}";

    public static ClassKind getClassKind(String classType) {
        classType = classType.toLowerCase();
        switch (classType) {
            case "class":
                return ClassKind.CLASS;
            case "interface":
                return ClassKind.INTERFACE;
            case "enum":
                return ClassKind.ENUM;
            case "annotation":
                return ClassKind.ANNOTATION;
            default:
                return ClassKind.CLASS;
        }
    }

    public static String getDefaultValueText(String name, ClassKind kind) {
        switch (kind) {
            case CLASS:
                return String.format(CLASS_DEFAULT_FORMAT, name);
            case INTERFACE:
                return String.format(INTERFACE_DEFAULT_FORMAT, name);
            case ENUM:
                return String.format(ENUM_DEFAULT_FORMAT, name);
            case ANNOTATION:
                return String.format(ANNOTATION_DEFAULT_FORMAT, name);
        }
        return "";
    }
}
