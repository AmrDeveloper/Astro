package astro.service;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProjectWatcher extends Thread {

    private String projectPath;
    private AtomicBoolean isWatcherRunning;
    private OnFileChangeListener mFileChangeListener;

    public ProjectWatcher(String path, OnFileChangeListener listener) {
        this.projectPath = path;
        this.mFileChangeListener = listener;
        this.isWatcherRunning = new AtomicBoolean(true);
    }

    private boolean isWatcherRunning() {
        return isWatcherRunning.get();
    }

    public void stopWatcher() {
        isWatcherRunning.set(false);
    }

    private synchronized void onProjectChangeListener(String path, OnFileChangeListener listener) throws IOException {
        final WatchService watcherService = FileSystems.getDefault().newWatchService();
        Path directory = Paths.get(path);
        WatchKey watchKey = directory.register(watcherService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
                , StandardWatchEventKinds.OVERFLOW);

        while (isWatcherRunning()) {
            for (WatchEvent<?> event : watchKey.pollEvents()) {
                Path file = directory.resolve((Path) event.context());
                if (event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                    listener.onCreate(file.toFile());
                } else if (event.kind().equals(StandardWatchEventKinds.ENTRY_DELETE)) {
                    listener.onDelete(file.toFile());
                } else if (event.kind().equals(StandardWatchEventKinds.ENTRY_MODIFY)) {
                    listener.onModify(file.toFile());
                }
            }
        }
    }

    @Override
    public void run() {
        try {
            onProjectChangeListener(projectPath, mFileChangeListener);
        } catch (IOException e) {
            stopWatcher();
        }
    }
}
