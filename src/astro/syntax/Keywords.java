package astro.syntax;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

public class Keywords {

    /**
     * Get All Keywords from classes text file and split them to classes import path and keywords
     */
    public static void onKeywordsBind() {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("astro/res/files/classes.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Stream<String> lines = reader.lines();
        Object[] linesArray = lines.toArray();
        int wordLength = linesArray.length;
        for(int i = 0 ; i < wordLength ; i = i + 2){
            SyntaxUtils.KEYWORDS_lIST.add(linesArray[i].toString());
            SyntaxUtils.KEYWORDS_lIST.add(linesArray[i + 1].toString());
            SyntaxUtils.CLASSES_LIST.put(linesArray[i].toString(),linesArray[i + 1].toString());
        }
        SyntaxUtils.KEYWORDS_lIST.addAll(Arrays.asList(SyntaxUtils.KEYWORDS));
    }
}
