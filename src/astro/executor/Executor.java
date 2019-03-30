package astro.executor;

import astro.utils.FileManager;

import java.io.File;
import java.io.IOException;

public class Executor {

    //TODO : Make Executor support 2 types of run (with / without lib folder)

    private String codeResult;

    /**
     * @param javaCode : Java File Code to Compile and Execute it
     */
    void executeJava(File javaCode, OnExecuteListener listener) {
        try {
            int compileState = codeCompile(javaCode);
            if (compileState == 0) {
                int executeState = codeRun(javaCode);
                if (executeState == 0)
                    listener.onExecutorSuccess(codeResult);
                else
                    listener.onExecutorFailure();
            } else
                listener.onCompileFailure();
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        File byteCode = new File(javaCode.getPath().replaceAll(".java", ".class"));
        FileManager.deleteFile(byteCode);
    }

    /**
     * @param javaFile : Java File Code to Compile it to byte code file
     * @return : state to check if file compile is done or not
     */
    private int codeCompile(File javaFile) throws InterruptedException, IOException {
        String javaClassName = javaFile.getName();
        String javaClassPath = javaFile.getParentFile().toString();

        ProcessBuilder pb = new ProcessBuilder("javac", javaClassName);
        pb.redirectError();
        pb.directory(new File(javaClassPath));
        Process p = pb.start();
        StreamConsumer consumer = new StreamConsumer(p.getInputStream());
        consumer.start();
        int result = p.waitFor();
        consumer.join();
        return result;
    }

    /**
     * @param javaCode : ByteCode file to execute it
     * @return : state to check if execute is done or not
     */
    private int codeRun(File javaCode) throws InterruptedException, IOException {
        String javaCodeName = javaCode.getName().replaceAll(".java", "");
        File javaCodePath = javaCode.getParentFile();

        ProcessBuilder pb = new ProcessBuilder("java", javaCodeName);
        pb.redirectError();

        pb.directory(javaCodePath);
        Process p = pb.start();
        StreamConsumer consumer = new StreamConsumer(p.getInputStream());
        consumer.start();
        int result = p.waitFor();
        consumer.join();
        codeResult = consumer.getCodeResult();
        return result;
    }
}
