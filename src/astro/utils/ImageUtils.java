package astro.utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageUtils {

    public static final int ICON_SIZE = 16;

    public static ImageView buildImageView(Image image) {
        ImageView imageView = new ImageView();
        imageView.setFitHeight(ICON_SIZE);
        imageView.setFitWidth(ICON_SIZE);
        imageView.setSmooth(true);
        imageView.setImage(image);
        return imageView;
    }
}
