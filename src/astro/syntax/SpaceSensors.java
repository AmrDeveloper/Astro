package astro.syntax;

import org.fxmisc.richtext.CodeArea;

public class SpaceSensors {

    private static final char SEMICOLON = ';';
    private static final char OPEN_CURLY_BRACES = '{';
    private static final char CLOSE_CURLY_BRACES = '}';

    public static void onWhiteSpaceSensors(CodeArea codeArea) {
        int position = codeArea.getCaretPosition();
        if (isValidPosition(codeArea, position)) {
            char lastChar = codeArea.getText(position - 2, position).charAt(0);
            char nextChar = codeArea.getText(position, position + 1).charAt(0);
            if (lastChar == OPEN_CURLY_BRACES && nextChar == CLOSE_CURLY_BRACES) {
                addSpaceBetweenCurlyBraces(codeArea, position);
            } else if (lastChar == CLOSE_CURLY_BRACES) {
                addSpaceAfterCloseCurlyBraces(codeArea, position);
            } else if (lastChar == SEMICOLON) {
                addSpaceAfterSemiColon(codeArea, position);
            }
        }
    }

    private static void addSpaceBetweenCurlyBraces(CodeArea codeArea, int position) {
        String currentLine = codeArea.getText(codeArea.getCurrentParagraph() - 1);
        int spaceNumber = currentLine.length() - currentLine.trim().length();
        int fullSpaceNumber = spaceNumber + 4;
        int newCursorPosition = position + fullSpaceNumber;
        codeArea.insertText(position, String.format("%" + fullSpaceNumber + "s%n", ""));
        if (spaceNumber != 0) {
            StringBuilder spaceTxt = new StringBuilder();
            for (int i = 0; i < spaceNumber; i++) spaceTxt.append(" ");
            codeArea.replaceText(newCursorPosition + 1, newCursorPosition + 1, spaceTxt.toString());
        }
        codeArea.moveTo(newCursorPosition);
    }

    private static void addSpaceAfterCloseCurlyBraces(CodeArea codeArea, int position) {
        String currentLine = codeArea.getText(codeArea.getCurrentParagraph() - 1);
        int spaceNumber = currentLine.lastIndexOf(CLOSE_CURLY_BRACES);
        if (spaceNumber > 0) {
            codeArea.insertText(position, String.format("%" + spaceNumber + "s%n", ""));
            codeArea.moveTo(position + spaceNumber);
        }
    }

    private static void addSpaceAfterSemiColon(CodeArea codeArea, int position) {
        String currentLine = codeArea.getText(codeArea.getCurrentParagraph() - 1);
        int spaceNumber = currentLine.length() - currentLine.trim().length();
        if (spaceNumber > 0) {
            codeArea.insertText(position, String.format("%" + spaceNumber + "s%n", ""));
            codeArea.moveTo(position + spaceNumber);
        }
    }

    /**
     * @param codeArea : Code View
     * @param position : Current Caret Position in code file
     * @return : true if new position is valid
     */
    private static boolean isValidPosition(CodeArea codeArea, int position) {
        return position > 1 && position < codeArea.getLength();
    }
}
