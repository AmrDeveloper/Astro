package astro.utils;

import astro.syntax.SyntaxUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TaskMatcher {

    private static final Pattern TODO_COMMENT_PATTERN = Pattern.compile(SyntaxUtils.TODO_SINGLE_COMMENT_PATTERN);
    private static final Pattern WARN_COMMENT_PATTERN = Pattern.compile(SyntaxUtils.WARN_SINGLE_COMMENT_PATTERN);

    public static Stream<String> getTodoTasks(String codeSource){
        List<String> todoTasks = new ArrayList<>();
        Matcher matcher = TODO_COMMENT_PATTERN.matcher(codeSource);
        while (matcher.find())
            todoTasks.add(matcher.group());
        return todoTasks.parallelStream();
    }

    public static Stream<String> getWarnTasks(String codeSource){
        List<String> warnTasks = new ArrayList<>();
        Matcher matcher = WARN_COMMENT_PATTERN.matcher(codeSource);
        while (matcher.find())
            warnTasks.add(matcher.group());
        return warnTasks.parallelStream();
    }
}
