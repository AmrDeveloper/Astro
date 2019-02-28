package astro.syntax;

import org.fxmisc.richtext.CodeArea;

public class PunctuationComplete {

    //Curly braces
    private static final char OPEN_CURLY_BRACES = '{';
    private static final char CLOSE_CURLY_BRACES = '}';

    //Square Brackets
    private static final char OPEN_SQUARE_BRACKETS = '[';
    private static final char CLOSE_SQUARE_BRACKETS = ']';

    //Round Brackets
    private static final char OPEN_ROUND_BRACKETS = '(';
    private static final char CLOSE_ROUND_BRACKETS = ')';

    //Quotes
    private static final char SINGLE_QUOTE = '\'';
    private static final char DOUBLE_QUOTE = '\"';

    /**
     * Check what is the type of this bracket and and insert the close type of it
     * @param codeArea : Code TextArea to get and insert text
     * @param code : the bracket
     * @param position : position of bracket, to move mouse cursor to it after insert
     */
    public static void onPunctuationComplete(CodeArea codeArea, char code, int position) {
        switch (code) {
            case OPEN_CURLY_BRACES: {
                codeArea.insertText(position,  String.valueOf(CLOSE_CURLY_BRACES));
                codeArea.moveTo(position);
                break;
            }
            case OPEN_SQUARE_BRACKETS: {
                codeArea.insertText(position,  String.valueOf(CLOSE_SQUARE_BRACKETS));
                codeArea.moveTo(position);
                break;
            }
            case OPEN_ROUND_BRACKETS: {
                codeArea.insertText(position, String.valueOf(CLOSE_ROUND_BRACKETS));
                codeArea.moveTo(position);
                break;
            }
            case SINGLE_QUOTE: {
                codeArea.insertText(position, String.valueOf(SINGLE_QUOTE));
                codeArea.moveTo(position);
                break;
            }
            case DOUBLE_QUOTE: {
                codeArea.insertText(position, String.valueOf(DOUBLE_QUOTE));
                codeArea.moveTo(position);
                break;
            }
        }
    }
}
