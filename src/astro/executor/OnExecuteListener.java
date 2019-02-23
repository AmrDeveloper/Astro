package astro.executor;

public interface OnExecuteListener {

    void onCompileFailure();

    void onExecutorFailure();

    void onExecutorSuccess(String result);

}
