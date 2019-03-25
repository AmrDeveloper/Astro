package astro.ui;

import astro.utils.Action;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class DialogUtils {

    public static final String CONFIRM_DIALOG = "Confirm Dialog";
    public static final String INFO_DIALOG = "Information Dialog";
    public static final String WARNING_DIALOG = "Warning Dialog";
    public static final String ERROR_DIALOG = "Error Dialog";

    public static void createInformationDialog(String title, String header, String content) {
        Alert infoDialog = new Alert(Alert.AlertType.INFORMATION);
        infoDialog.setTitle(title);
        infoDialog.setHeaderText(header);
        infoDialog.setContentText(content);
        infoDialog.showAndWait();
    }

    public static void createWarningDialog(String title, String header, String content){
        Alert warnDialog = new Alert(Alert.AlertType.WARNING);
        warnDialog.setTitle(title);
        warnDialog.setHeaderText(header);
        warnDialog.setContentText(content);
        warnDialog.showAndWait();
    }

    public static void createErrorDialog(String title, String header, String content){
        Alert errorDialog = new Alert(Alert.AlertType.ERROR);
        errorDialog.setTitle(title);
        errorDialog.setHeaderText(header);
        errorDialog.setContentText(content);
        errorDialog.showAndWait();
    }

    public static void createConfirmDialog(String title, String header, String content, Action action){
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle(title);
        confirmDialog.setHeaderText(header);
        confirmDialog.setContentText(content);

        Optional<ButtonType> result = confirmDialog.showAndWait();
        if (result.get() == ButtonType.OK){
            // User Chose OK
            action.apply();
        } else {
            //user chose CANCEL or closed the dialog

        }
    }
}

