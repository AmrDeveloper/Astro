package astro.executor;

import java.io.IOException;
import java.io.InputStream;

public class StreamConsumer extends Thread {

    private InputStream mInputStream;
    private IOException mExceptionIO;
    private StringBuilder mOutputBuilder;

    public StreamConsumer(InputStream stream) {
        mInputStream = stream;
    }

    public String getCodeResult() {
        return mOutputBuilder.toString();
    }

    public IOException getCodeException() {
        return mExceptionIO;
    }

    public boolean hasException() {
        return !(mExceptionIO == null);
    }

    @Override
    public void run() {
        mOutputBuilder = new StringBuilder();
        try {
            int input;
            while ((input = mInputStream.read()) != -1) {
                mOutputBuilder.append((char) input);
            }
        } catch (IOException ex) {
            mExceptionIO = ex;
        }
    }
}
