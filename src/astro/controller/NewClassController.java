package astro.controller;

import astro.constants.ClassKind;
import astro.constants.Extension;
import astro.ui.DialogUtils;
import astro.utils.ClassManager;
import astro.utils.FileManager;
import astro.utils.Intent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class NewClassController implements Initializable {

    @FXML private TextField classNameText;
    @FXML private ComboBox<String> kindComboBox;
    @FXML private Button createButton;
    @FXML private Button cancelButton;
    @FXML static NewClassController classController;

    private String directoryPath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        classController = this;
        bindComboBoxTypes();
        getValuesFromMainView();

        createButton.setOnAction(e -> createButtonAction());
        cancelButton.setOnAction(e -> cancelButtonAction());
    }

    private void getValuesFromMainView() {
        Intent intent = Intent.getIntent();
        directoryPath = intent.getStringValue("CLASS_PATH","");
    }

    private void bindComboBoxTypes() {
        kindComboBox.getItems().addAll("Class", "Interface", "Enum", "Annotation");
        kindComboBox.getSelectionModel().select(0);
    }

    private void createButtonAction() {
        String classType = kindComboBox.getSelectionModel().getSelectedItem();
        String className = classNameText.getText();

        if(className.isEmpty()){
            String errorMessage = "Insert Class name first";
            DialogUtils.createErrorDialog(DialogUtils.ERROR_DIALOG,null,errorMessage);
            return;
        }

        String fullClassName = (className.endsWith(Extension.JAVA)) ? className : className.concat(Extension.JAVA);
        String newClassPath = directoryPath.concat(File.separator).concat(fullClassName);

        File source = FileManager.createNewFile(newClassPath);
        ClassKind classKind = ClassManager.getClassKind(classType);
        FileManager.updateContent(source, ClassManager.getDefaultValueText(className, classKind));

        cancelButtonAction();
    }

    private void cancelButtonAction() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public static NewClassController getInstance(){
        return classController;
    }
}
