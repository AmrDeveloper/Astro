package astro.service;

import astro.model.Source;
import astro.utils.FileCrawler;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;

public class FileService {

    private static final FileCrawler crawler = new FileCrawler();

    public static ProjectWatcher setFileService(TreeView<Source> filesTreeView, File projectDir) {
        ProjectWatcher watcherService = new ProjectWatcher(projectDir.getPath(), new OnFileChangeListener() {
            @Override
            public void onCreate(File file) {
                Source source = new Source(file);
                File parent = source.getFile().getParentFile();
                TreeItem<Source> root = filesTreeView.getTreeItem(0);
                if (root.getValue().getPath().equals(parent.getPath())) {
                    root.getChildren().add(new FileCrawler().getSourceTreeItem(source));
                } else {
                    root.getChildren().parallelStream().forEach(treeItem -> {
                        String itemPath = treeItem.getValue().getPath();
                        if (itemPath.equals(parent.getPath())) {
                            treeItem.getChildren().add(new FileCrawler().getSourceTreeItem(source));
                            filesTreeView.refresh();
                        }
                    });
                }
            }

            @Override
            public void onModify(File file) {
                TreeItem<Source> root = filesTreeView.getTreeItem(0);
                root.getChildren().parallelStream().forEach(treeItem -> {
                    if (treeItem.getValue().getPath().equals(file.getPath())) {
                        if (file.isDirectory()) {
                            Node graphic = treeItem.getGraphic();
                            TreeItem newItem = crawler.getFilesForDirectory(file);
                            newItem.setGraphic(graphic);
                            newItem.setExpanded(treeItem.isExpanded());

                            int nodeIndex = root.getChildren().indexOf(treeItem);
                            root.getChildren().remove(nodeIndex);
                            root.getChildren().add(nodeIndex, newItem);
                        }
                    }
                });
            }

            @Override
            public void onDelete(File file) {
                TreeItem<Source> root = filesTreeView.getTreeItem(0);
                String sourceParent = file.getPath();
                if (root.getValue().getPath().equals(file.getPath())) {
                    filesTreeView.setRoot(null);
                } else {
                    if (root != null) {
                        root.getChildren().parallelStream().forEach(treeItem -> {
                            String itemPath = treeItem.getValue().getPath();
                            if (itemPath.equals(sourceParent)) {
                                Platform.runLater(() -> {
                                    root.getChildren().removeAll(treeItem);
                                });
                            }
                        });
                    }
                }
            }
        });
        watcherService.start();
        return watcherService;
    }
}
