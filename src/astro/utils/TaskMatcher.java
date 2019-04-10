package astro.utils;

import astro.model.Task;
import astro.syntax.SyntaxUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class TaskMatcher {

    private static final Pattern TODO_COMMENT_PATTERN = Pattern.compile(SyntaxUtils.TODO_SINGLE_COMMENT_PATTERN);
    private static final Pattern WARN_COMMENT_PATTERN = Pattern.compile(SyntaxUtils.WARN_SINGLE_COMMENT_PATTERN);

    public static Stream<Task> getTodoTasks(String codeSource){
        List<Task> todoTasks = new ArrayList<>();
        Matcher matcher = TODO_COMMENT_PATTERN.matcher(codeSource);
        while (matcher.find())
            todoTasks.add(new Task(matcher.start(),matcher.end(),matcher.group()));
        return todoTasks.parallelStream();
    }

    public static Stream<Task> getWarnTasks(String codeSource){
        List<Task> warnTasks = new ArrayList<>();
        Matcher matcher = WARN_COMMENT_PATTERN.matcher(codeSource);
        while (matcher.find())
            warnTasks.add(new Task(matcher.start(),matcher.end(),matcher.group()));
        return warnTasks.parallelStream();
    }
}
