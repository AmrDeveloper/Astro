package astro.utils;

import astro.constants.Extension;
import astro.model.Source;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Objects;

public class FileCrawler {

    //Programming Languages Files Icons
    private Image JAVA_FILE = new Image(getClass().getResourceAsStream("/astro/res/icons/lang/java_icon.png"));
    private Image HTML_FILE = new Image(getClass().getResourceAsStream("/astro/res/icons/lang/html_icon.png"));
    private Image CSS_FILE = new Image(getClass().getResourceAsStream("/astro/res/icons/lang/css_icon.png"));
    private Image JS_FILE = new Image(getClass().getResourceAsStream("/astro/res/icons/lang/js_icon.png"));

    //Folder Icons
    private Image NORMAL_DIR = new Image(getClass().getResourceAsStream("/astro/res/icons/folder/folder_icon.png"));
    private Image DOCS_DIR = new Image(getClass().getResourceAsStream("/astro/res/icons/folder/docs_folder.png"));
    private Image AUDIO_DIR = new Image(getClass().getResourceAsStream("/astro/res/icons/folder/audio_folder.png"));
    private Image IMAGES_DIR = new Image(getClass().getResourceAsStream("/astro/res/icons/folder/images_folder.png"));
    private Image VIDEOS_DIR = new Image(getClass().getResourceAsStream("/astro/res/icons/folder/videos_folder.png"));
    private Image GIT_DIR = new Image(getClass().getResourceAsStream("/astro/res/icons/folder/git_folder.png"));

    //Normal Files Icons
    private Image TEXT_FILE = new Image(getClass().getResourceAsStream("/astro/res/icons/file/text_file.png"));
    private Image ZIP_FILE = new Image(getClass().getResourceAsStream("/astro/res/icons/file/zip_file.png"));
    private Image PDF_FILE = new Image(getClass().getResourceAsStream("/astro/res/icons/file/pdf_file.png"));


    /**
     * @param directory : Directory to get all files on it
     * @return : return TreeItem of Source Model Class files in directory
     */
    synchronized public TreeItem<Source> getFilesForDirectory(File directory) {
        TreeItem<Source> root = new TreeItem<>(new Source(directory));
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                if (file.getName().endsWith(".git")) {
                    root.getChildren().add(new TreeItem<>(new Source(file), new ImageView(GIT_DIR)));
                } else if (file.getName().equals("icons")) {
                    root.getChildren().add(new TreeItem<>(new Source(file), new ImageView(IMAGES_DIR)));
                } else if (file.getName().equals("audio")) {
                    root.getChildren().add(new TreeItem<>(new Source(file), new ImageView(AUDIO_DIR)));
                } else if (file.getName().equals("videos")) {
                    root.getChildren().add(new TreeItem<>(new Source(file), new ImageView(VIDEOS_DIR)));
                } else if (file.getName().equals("docs")) {
                    root.getChildren().add(new TreeItem<>(new Source(file), new ImageView(DOCS_DIR)));
                } else {
                    TreeItem<Source> treeItem = getFilesForDirectory(file);
                    treeItem.setGraphic(new ImageView(NORMAL_DIR));
                    root.getChildren().add(treeItem);
                }
            } else {
                if (file.getName().endsWith(Extension.JAVA)) {
                    root.getChildren().add(new TreeItem<>(new Source(file), new ImageView(JAVA_FILE)));
                } else if (file.getName().endsWith(Extension.HTML)) {
                    root.getChildren().add(new TreeItem<>(new Source(file), new ImageView(HTML_FILE)));
                } else if (file.getName().endsWith(Extension.CSS)) {
                    root.getChildren().add(new TreeItem<>(new Source(file), new ImageView(CSS_FILE)));
                } else if (file.getName().endsWith(Extension.JS)) {
                    root.getChildren().add(new TreeItem<>(new Source(file), new ImageView(JS_FILE)));
                } else if (file.getName().endsWith(Extension.PDF)) {
                    root.getChildren().add(new TreeItem<>(new Source(file), new ImageView(PDF_FILE)));
                } else if (file.getName().endsWith(Extension.ZIP)) {
                    root.getChildren().add(new TreeItem<>(new Source(file), new ImageView(ZIP_FILE)));
                } else {
                    root.getChildren().add(new TreeItem<>(new Source(file), new ImageView(TEXT_FILE)));
                }
            }
        }
        return root;
    }

    /**
     * @param source : Get Source File
     * @return : return TreeItem for Source File With Icon
     */
    public TreeItem<Source> getSourceTreeItem(Source source) {
        if (source.getFile().isDirectory()) {
            if (source.getName().endsWith(".git")) {
                return new TreeItem<>(source, new ImageView(GIT_DIR));
            } else if (source.getName().equals("icons")) {
                return new TreeItem<>(source, new ImageView(IMAGES_DIR));
            } else if (source.getName().equals("audio")) {
                return new TreeItem<>(source, new ImageView(AUDIO_DIR));
            } else if (source.getName().equals("videos")) {
                return new TreeItem<>(source, new ImageView(VIDEOS_DIR));
            } else if (source.getName().equals("docs")) {
                return new TreeItem<>(source, new ImageView(DOCS_DIR));
            } else {
                return new TreeItem<>(source, new ImageView(NORMAL_DIR));
            }
        } else {
            if (source.getName().endsWith(Extension.JAVA)) {
                return new TreeItem<>(source, new ImageView(JAVA_FILE));
            } else if (source.getName().endsWith(Extension.HTML)) {
                return new TreeItem<>(source, new ImageView(HTML_FILE));
            } else if (source.getName().endsWith(Extension.CSS)) {
                return new TreeItem<>(source, new ImageView(CSS_FILE));
            } else if (source.getName().endsWith(Extension.JS)) {
                return new TreeItem<>(source, new ImageView(JS_FILE));
            } else if (source.getName().endsWith(Extension.PDF)) {
                return new TreeItem<>(source, new ImageView(PDF_FILE));
            } else if (source.getName().endsWith(Extension.ZIP)) {
                return new TreeItem<>(source, new ImageView(ZIP_FILE));
            } else {
                return new TreeItem<>(source, new ImageView(TEXT_FILE));
            }
        }
    }
}
