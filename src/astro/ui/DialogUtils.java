package astro.ui;

import javafx.scene.control.Alert;

public class DialogUtils {

    public static final String INFO_DIALOG = "Information Dialog";
    public static final String WARNING_DIALOG = "Warning Dialog";
    public static final String ERRORDIALOG = "Error Dialog";

    public static void createInformationDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void createWarningDIalog(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void createErrorDIalog(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
