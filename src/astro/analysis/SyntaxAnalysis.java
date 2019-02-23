package astro.analysis;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class SyntaxAnalysis {

    public String getClassModifiers(Class source) {
        return Modifier.toString(source.getModifiers());
    }

    public Field[] getDeclaredFields(Class source) {
        return source.getDeclaredFields();
    }

    public Method[] getDeclaredMethods(Class source) {
        return source.getDeclaredMethods();
    }

    public Class[] getDeclaredClasses(Class source) {
        return source.getDeclaredClasses();
    }

    public Class fileToClass(File sourceCode) throws MalformedURLException, ClassNotFoundException {
        URLClassLoader loader = new URLClassLoader(new URL[]{sourceCode.toURI().toURL()});
        return loader.loadClass(sourceCode.getName().replaceAll(".java", ""));
    }
}
