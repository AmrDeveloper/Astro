package astro.controller;

import astro.utils.FileManager;
import astro.utils.Intent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class NewFileController implements Initializable {

    @FXML private TextField fileNameText;
    @FXML private Button createButton;
    @FXML private Button cancelButton;
    @FXML static NewFileController fileController;

    private String directoryPath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileController = this;
        getValuesFromMainView();

        createButton.setOnAction(e -> createButtonAction());
        cancelButton.setOnAction(e -> cancelButtonAction());
    }

    private void getValuesFromMainView() {
        Intent intent = Intent.getIntent();
        directoryPath = intent.getStringValue("CLASS_PATH", "");
    }

    private void createButtonAction() {
        String className = fileNameText.getText();
        if(!className.isEmpty()){
            String newClassPath = directoryPath.concat(File.separator).concat(className);
            System.out.println(newClassPath);
            File source = FileManager.createNewFile(newClassPath);
            cancelButtonAction();
        }else{
            fileNameText.setText("Insert File name first");
        }
    }

    private void cancelButtonAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
