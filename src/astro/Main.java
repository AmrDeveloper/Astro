package astro;

import astro.syntax.Keywords;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import astro.constants.Astro;

public class Main extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/main_view.fxml"));
        Scene scene = new Scene(root, Astro.APP_WIDTH, Astro.APP_HEIGHT);
        scene.getStylesheets().add("astro/styles/editor_style.css");
        scene.getStylesheets().add("astro/styles/tab_pane_style.css");
        scene.getStylesheets().add("astro/styles/menu_style.css");
        scene.getStylesheets().add("astro/styles/result_area_style.css");
        scene.getStylesheets().add("astro/styles/list_style.css");

        primaryStage.getIcons().add(Astro.APP_ICON);
        primaryStage.setTitle(Astro.APP_NAME);
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();

        Keywords.onKeywordsBind();
        mainStage = primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
