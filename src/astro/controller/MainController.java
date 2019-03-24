package astro.controller;

import astro.analysis.SyntaxAnalysis;
import astro.constants.Icons;
import astro.model.Source;
import astro.service.FileService;
import astro.service.ProjectWatcher;
import astro.ui.DialogUtils;
import astro.utils.*;
import com.google.common.io.Files;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

import java.io.File;
import java.io.IOException;

import java.net.URL;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MainController implements Initializable {

    //FX Views
    @FXML private TextArea resultTextArea;
    @FXML private TabPane codeAreaLayout;
    @FXML private SplitPane mainSplitPane;
    @FXML private SplitPane codeSplitPane;

    @FXML private ListView<Source> openedFilesList;
    @FXML private TreeView<Source> filesTreeView;
    @FXML private TreeView<String> analysisTreeView;

    //New Menu Items
    @FXML private MenuItem newFileMenuItem;
    @FXML  private MenuItem newClassMenuItem1;
    @FXML  private MenuItem newProjectMenuItem;

    //File Menu Items
    @FXML private MenuItem openFileMenuItem;
    @FXML private MenuItem openFolderMenuItem;
    @FXML private MenuItem closeMenuItem;
    @FXML private MenuItem exitMenuItem;

    //View Menu Items
    @FXML private MenuItem showFilesMenuAction;
    @FXML private MenuItem showResultMenuAction;

    //Controllers
    @FXML static MainController mainController;

    private Logger debugger;
    private ProjectWatcher projectWatcherService;
    private SyntaxAnalysis syntaxAnalysis;
    private ExecutorService executorService;

    private boolean isFilesPaneVisible = true;
    private boolean isResultPaneVisible = true;

    private static final int FILE_PANE_INDEX = 0;
    private static final int RESULT_PANE_INDEX = 0;
    private static final String DEBUG_TAG = MainController.class.getSimpleName();
    private static final int THREAD_AVAILABLE_NUMBER = Runtime.getRuntime().availableProcessors();
    private final Image PROJECT_DIR = new Image(getClass().getResourceAsStream("/astro/res/icons/folder/main_folder.png"));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainController = this;
        executorService = Executors.newFixedThreadPool(THREAD_AVAILABLE_NUMBER);
        debugger = Logger.getLogger(DEBUG_TAG);

        onMenuItemsActions();
        openedListSettings();

        resultTextArea.setEditable(false);

        codeAreaLayout.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        codeAreaLayout.setOnDragDropped(this::onCodeLayoutDragDropped);
        codeAreaLayout.setOnDragOver(this::onCodeLayoutDragOver);
        codeAreaLayout.getSelectionModel().selectedItemProperty().addListener(onTabSelectChangeListener);

        filesTreeView.getSelectionModel().selectedItemProperty().addListener(onFileSelectChangeListener);
    }

    private void openedListSettings() {
        openedFilesList.setEditable(false);
        openedFilesList.setOnMouseClicked(event -> onListItemClicked());
    }

    private void onMenuItemsActions() {
        //File Menu
        openFileMenuItem.setOnAction(event -> onOpenFileMenuAction());
        openFolderMenuItem.setOnAction(event -> onOpenFolderMenuAction());
        closeMenuItem.setOnAction(event -> onCloseMenuAction());
        exitMenuItem.setOnAction(event -> onExitMenuAction());

        //New SubMenu
        newFileMenuItem.setOnAction(event -> showNewFileDialog());
        newClassMenuItem1.setOnAction(event -> showNewClassDialog());
        newProjectMenuItem.setOnAction(event -> showProjectFileDialog());

        //View Menu
        showFilesMenuAction.setOnAction(event -> onShowFilesMenuAction());
        showResultMenuAction.setOnAction(event -> onShowResultMenuAction());
    }

    private void showNewFileDialog() {
        if (Objects.nonNull(filesTreeView.getTreeItem(0))) {
            int directoryIndex = filesTreeView.getSelectionModel().getSelectedIndex();
            if (directoryIndex != -1) {
                TreeItem<Source> directory = filesTreeView.getSelectionModel().getSelectedItem();
                Source dirSource = directory.getValue();
                File dirFile = dirSource.getFile();
                String dirPath = "";
                if (dirFile.isDirectory() && dirFile.canWrite()) {
                    dirPath = dirFile.getPath();
                } else if (dirFile.getParentFile().canWrite()) {
                    dirPath = dirFile.getParentFile().getPath();
                }
                Intent intent = Intent.getIntent();
                intent.addStringValue("CLASS_PATH", dirPath);
                String title = "New File";
                String viewPath = "/astro/views/new_file.fxml";
                String stylePath = "/astro/styles/new_create_style.css";
                intent.showAnotherView(viewPath, title, stylePath);
            } else {
                String warnMessage = "Please Select Directory First";

                //For Debugging
                debugger.warning(warnMessage);

                //For User
                DialogUtils.createWarningDialog(DialogUtils.WARNING_DIALOG, null, warnMessage);
            }
        } else {
            String warnMessage = "Please Create or Open Project First";

            //For Debugging
            debugger.warning(warnMessage);

            //For User
            DialogUtils.createWarningDialog(DialogUtils.WARNING_DIALOG, null, warnMessage);
        }
    }

    private void showNewClassDialog() {
        if (Objects.nonNull(filesTreeView.getTreeItem(0))) {
            int directoryIndex = filesTreeView.getSelectionModel().getSelectedIndex();
            if (directoryIndex != -1) {
                TreeItem<Source> directory = filesTreeView.getSelectionModel().getSelectedItem();
                Source dirSource = directory.getValue();
                File dirFile = dirSource.getFile();
                String dirPath = "";
                if (dirFile.isDirectory() && dirFile.canWrite()) {
                    dirPath = dirFile.getPath();
                } else if (dirFile.getParentFile().canWrite()) {
                    dirPath = dirFile.getParentFile().getPath();
                }
                Intent intent = Intent.getIntent();
                intent.addStringValue("CLASS_PATH", dirPath);
                String title = "New Java Class";
                String viewPath = "/astro/views/new_class.fxml";
                String stylePath = "/astro/styles/new_create_style.css";
                intent.showAnotherView(viewPath, title, stylePath);
            } else {
                String warnMessage = "Please Select Directory First";

                //For Debugging
                debugger.warning(warnMessage);

                //For User
                DialogUtils.createWarningDialog(DialogUtils.WARNING_DIALOG, null, warnMessage);
            }
        } else {
            String warnMessage = "Please Create or Open Project First";

            //For Debugging
            debugger.warning(warnMessage);

            //For User
            DialogUtils.createWarningDialog(DialogUtils.WARNING_DIALOG, null, warnMessage);
        }
    }

    private void showProjectFileDialog() {
        String title = "Create New Project";
        String viewPath = "/astro/views/new_project.fxml";
        String stylePath = "/astro/styles/new_create_style.css";

        Intent intent = Intent.getIntent();
        intent.showAnotherView(viewPath, title, stylePath);
    }

    private void onCodeLayoutDragDropped(DragEvent event) {
        List<File> currentDropped = event.getDragboard().getFiles();
        List<Source> sourceStream = openedFilesList.getItems()
                .parallelStream()
                .collect(Collectors.toList());
        for (File file : currentDropped) {
            if (sourceStream.indexOf(new Source(file)) == -1) {
                String fileName = file.getName();
                if (fileName.endsWith(".java"))
                    executorService.execute(() -> openSourceInTab(file));
                else if (fileName.endsWith(".txt"))
                    executorService.execute(() -> openTextInTab(file));
                else if (fileName.endsWith(".md"))
                    executorService.execute(() -> openTextInTab(file));
            }
        }
    }

    private void onCodeLayoutDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
    }

    private void onOpenFileMenuAction() {
        File outputFile = FileManager.openSourceFile("Open Java File");
        if (outputFile != null)
            if (outputFile.getName().endsWith(".java"))
                executorService.execute(() -> openSourceInTab(outputFile));
            else if (outputFile.getName().endsWith(".txt"))
                executorService.execute(() -> openTextInTab(outputFile));
            else if (outputFile.getName().endsWith(".md"))
                executorService.execute(() -> openTextInTab(outputFile));
    }

    private void onOpenFolderMenuAction() {
        File sourceFolder = FileManager.openSourceDir("Open Project Folder");
        if (Objects.nonNull(sourceFolder)) updateFilesTreeView(sourceFolder);
    }

    private void onCloseMenuAction() {
        openedFilesList.getItems().clear();
        codeAreaLayout.getTabs().clear();
        resultTextArea.clear();
        filesTreeView.setRoot(null);
        if (Objects.nonNull(projectWatcherService)) projectWatcherService.stopWatcher();
    }

    private void onExitMenuAction() {
        Platform.exit();
        System.exit(0);
    }

    private void onShowFilesMenuAction() {
        if (isFilesPaneVisible) {
            mainSplitPane.setDividerPosition(FILE_PANE_INDEX, 0);
            showResultMenuAction.setText("Show Output");
            isFilesPaneVisible = false;
        } else {
            mainSplitPane.setDividerPosition(FILE_PANE_INDEX, .18);
            showResultMenuAction.setText("Hide Files");
            isFilesPaneVisible = true;
        }
    }

    private void onShowResultMenuAction() {
        if (isResultPaneVisible) {
            codeSplitPane.setDividerPosition(RESULT_PANE_INDEX, 1);
            showResultMenuAction.setText("Show Output");
            isResultPaneVisible = false;
        } else {
            codeSplitPane.setDividerPosition(RESULT_PANE_INDEX, .82);
            showResultMenuAction.setText("Hide Output");
            isResultPaneVisible = true;
        }
    }

    private void onListItemClicked() {
        SingleSelectionModel<Tab> selectionModel = codeAreaLayout.getSelectionModel();
        ObservableList<Source> selectedItems = openedFilesList.getSelectionModel().getSelectedItems();
        Source sourceFile = selectedItems.get(0);
        final int currentTabNumber = codeAreaLayout.getTabs().size();
        for (int i = 0; i < currentTabNumber; i++) {
            if (sourceFile.getName().equals(codeAreaLayout.getTabs().get(i).getText())) {
                selectionModel.select(i);
            }
        }
    }

    private void openSourceInTab(File sourceFile) {
        Tab javaTab = new Tab(sourceFile.getName());
        javaTab.setUserData(sourceFile.getPath());
        javaTab.setOnClosed(event -> onTabCloseAction(javaTab));
        javaTab.setGraphic(ImageUtils.buildImageView(Icons.codeIconImage));

        CodeArea codeTextArea = new CodeArea();
        EditorController editorController1 = new EditorController(codeTextArea, resultTextArea);
        editorController1.editorSettings();

        try {
            StringBuilder code = new StringBuilder();
            Files.readLines(sourceFile, Charset.defaultCharset()).forEach(s -> code.append(s).append("\n"));
            codeTextArea.replaceText(0, 0, code.toString());
            javaTab.setContent(new VirtualizedScrollPane<>(codeTextArea));
            //Update On UI Thread
            Platform.runLater(() -> {
                codeAreaLayout.getTabs().add(javaTab);
                openedFilesList.getItems().add(new Source(sourceFile));
            });
            editorController1.updateSourceFile(sourceFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openTextInTab(File textFile) {
        Tab textTab = new Tab(textFile.getName());
        textTab.setUserData(textFile.getPath());
        textTab.setOnClosed(event -> onTabCloseAction(textTab));
        textTab.setGraphic(ImageUtils.buildImageView(Icons.textIconImage));

        try {
            TextArea textArea = new TextArea();
            StringBuilder code = new StringBuilder();
            Files.readLines(textFile, Charset.defaultCharset()).forEach(s -> code.append(s).append("\n"));
            textArea.replaceText(0, 0, code.toString());
            textTab.setContent(textArea);
            //Update On UI Thread
            Platform.runLater(() -> {
                codeAreaLayout.getTabs().add(textTab);
                openedFilesList.getItems().add(new Source(textFile));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onTabCloseAction(Tab tab) {
        final int fileListItemCount = openedFilesList.getItems().size();
        for (int i = 0; i < fileListItemCount; i++) {
            if (openedFilesList.getItems().get(i).getName().equals(tab.getText())) {
                openedFilesList.getItems().remove(i);
                openedFilesList.refresh();
                break;
            }
        }
    }

    private ChangeListener<Tab> onTabSelectChangeListener = (observable, oldValue, newValue) -> {
        if (Objects.nonNull(newValue)) {
            if (newValue.getText().endsWith(".java")) {

            }
        }
    };

    private ChangeListener<TreeItem> onFileSelectChangeListener = (observable, oldValue, newValue) -> {
        if (Objects.nonNull(newValue)) {
            if (newValue.getValue().toString().endsWith(".java")) {
                Source javaSource = (Source) newValue.getValue();
                openSourceInTab(javaSource.getFile());
            } else if (newValue.getValue().toString().endsWith(".txt")) {
                Source textSource = (Source) newValue.getValue();
                openTextInTab(textSource.getFile());
            }
        }
    };

    void updateFilesTreeView(File sourceFolder) {
        if (Objects.nonNull(sourceFolder)) {
            if (projectWatcherService != null) {
                projectWatcherService.stopWatcher();
            }
            FileCrawler crawler = new FileCrawler();
            TreeItem<Source> sourceTreeItem = crawler.getFilesForDirectory(sourceFolder);
            sourceTreeItem.setGraphic(new ImageView(PROJECT_DIR));
            executorService.submit(() -> Platform.runLater(() -> filesTreeView.setRoot(sourceTreeItem)));
            projectWatcherService = FileService.setFileService(filesTreeView, sourceFolder);
        }
    }
}
