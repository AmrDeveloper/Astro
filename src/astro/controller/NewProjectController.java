package astro.controller;

import astro.utils.FileManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NewProjectController implements Initializable {

    @FXML private TextField projectNameText;
    @FXML private TextField projectPathText;

    @FXML private Button findPathButton;
    @FXML private Button createButton;
    @FXML private Button cancelButton;

    @FXML static NewProjectController projectController;

    private File projectPathFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        projectController = this;
        findPathButton.setOnAction(e->setProjectPathText());
        createButton.setOnAction(e->createNewProject());
        cancelButton.setOnAction(e->cancelButtonAction());
    }

    private void setProjectPathText(){
        projectPathFile = FileManager.openSourceDir("Select Project Path");
        if(Objects.nonNull(projectPathFile))projectPathText.setText(projectPathFile.getPath());
    }

    private void createNewProject(){
        String projectName = projectNameText.getText().trim();
        String projectPath = projectPathText.getText().trim();

        //TODO : replace set text with error dialog
        if(projectName.isEmpty()){
            projectNameText.setText("Invalid Project Name");
            return;
        }
        if(projectPath.isEmpty() || projectPathFile.canWrite()){
            projectPathText.setText("Invalid Project Path");
            return;
        }

        String projectFullPath = projectPath.concat(File.separator).concat(projectName);
        String projectSrcPath = projectPath.concat(File.separator).concat(projectName).concat(File.separator).concat("src");
        File projectDir = FileManager.createNewFolder(projectFullPath);
        File projectSrc = FileManager.createNewFolder(projectSrcPath);

        MainController.mainController.updateFilesTreeView(projectDir);

        cancelButtonAction();
    }

    private void cancelButtonAction(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
