package com.apiot.picviewer;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImageController {
    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
        String imagePath = "src/main/resources/com/apiot/picviewer/images/IMG_0889.jpg";
        File file = new File(imagePath);
        if (!file.exists()) {
            System.out.println("File doesn't exist: " + imagePath);
            return;
        }
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
    }
}