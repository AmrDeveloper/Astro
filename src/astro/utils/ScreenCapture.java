package astro.utils;

import astro.Main;
import astro.ui.DialogUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Logger;

public class ScreenCapture {

    /**
     * Take ScreenShot for Editor Screen
     */
    public static void captureScreenShot() {
        try {
            Robot robot = new Robot();
            int xPosition = (int) Main.mainStage.getX();
            int yPosition = (int) Main.mainStage.getY();
            int width = (int) Main.mainStage.getWidth();
            int height = (int) Main.mainStage.getHeight();
            Rectangle screenRectangle = new Rectangle(xPosition, yPosition, width, height);
            BufferedImage bufferedImage = robot.createScreenCapture(screenRectangle);
            File image = FileManager.saveSourceFile("Save ScreenShot Image");
            ImageIO.write(bufferedImage, "png", image);
        } catch (Exception ex) {
            String errorMessage = "Can't Capture Screen";
            //Debugging Warning
            Logger.getLogger(ScreenCapture.class.getSimpleName()).warning(errorMessage);
            //Debugging For UI
            DialogUtils.createErrorDialog(DialogUtils.ERROR_DIALOG,null,errorMessage);
        }
    }
}
