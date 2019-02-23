package astro.utils;

import astro.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

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
            //TODO : Show Invalid Message
        }
    }
}
