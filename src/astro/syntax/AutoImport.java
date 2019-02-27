package astro.syntax;

import org.fxmisc.richtext.CodeArea;

public class AutoImport {

    private static final String IMPORT_PACKAGE_FORMAT = "import %s;\n";

    public static void importClassPackage(CodeArea codeArea, int position,String packagePath) {
        String formattedPackage = String.format(IMPORT_PACKAGE_FORMAT,packagePath);
        if (!codeArea.getText().contains(formattedPackage)) {
            int impIndex = codeArea.getText().indexOf("import");
            codeArea.insertText((impIndex == -1) ? 0 : impIndex, formattedPackage);
            int cursorPosition = formattedPackage.length() + position;
            codeArea.moveTo(cursorPosition);
        }
    }
}
