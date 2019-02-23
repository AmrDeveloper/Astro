package astro.syntax;

import javafx.scene.control.IndexRange;
import org.fxmisc.richtext.CodeArea;

public class AutoCommenter {

    public static void commentCode(CodeArea codeArea){
        IndexRange indexRange = codeArea.getSelection();
        String selectedText = codeArea.getSelectedText();
        String[] lines = selectedText.split("\n");

        StringBuilder output = new StringBuilder();
        for (String line : lines) {
            if (line.contains("//") || line.contains("/*") || line.contains("*/")) {
                output.append(line).append("\n");
            } else {
                output.append("//").append(line).append("\n");
            }
        }
        codeArea.replaceText(indexRange.getStart(), indexRange.getEnd(), output.toString());
    }

    public static void unCommentCode(CodeArea codeArea){
        IndexRange indexRange = codeArea.getSelection();
        String selectedText = codeArea.getSelectedText();
        String[] lines = selectedText.split("\n");
        StringBuilder output = new StringBuilder();
        for (String line : lines) {
            if (line.contains("//") || line.contains("/*") || line.contains("*/")) {
                output.append(line.replaceFirst("//|/\\*|\\*/", "")).append("\n");
            } else {
                output.append(line).append("\n");
            }
        }
        codeArea.replaceText(indexRange.getStart(), indexRange.getEnd(), output.toString());
    }
}
