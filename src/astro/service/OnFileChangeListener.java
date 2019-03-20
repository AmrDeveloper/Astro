package astro.service;

import java.io.File;

public interface OnFileChangeListener {

    void onCreate(File file);

    void onModify(File file);

    void onDelete(File file);

}
