package com.apiot.picviewer;

import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageController {

    @FXML
    private TreeView<String> treeView;
    @FXML
    private ImageView imageView;

    private final String userHome = System.getProperty("user.home");
    private final File picturesDir = new File(userHome, "Pictures");

    @FXML
    public void initialize() {
        File rootDir = (picturesDir.exists() && picturesDir.isDirectory()) ? picturesDir : new File(userHome);
        if (rootDir.equals(new File(userHome))) {
            System.out.println("Directory 'Pictures' doesn't exist. Using home directory.");
        }

        TreeItem<String> rootItem = createTree(rootDir);
        treeView.setRoot(rootItem);

        treeView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                File file = new File(getFullPath(newVal));
                if (file.isFile()) {
                    displayImage(file);
                }
            }
        });
    }

    private TreeItem<String> createTree(File dir) {
        TreeItem<String> item = new TreeItem<>(dir.getName());
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                item.getChildren().add(createTree(file));
            }
        }
        return item;
    }

    private String getFullPath(TreeItem<String> item) {
        List<String> pathElements = new ArrayList<>();
        while (item != null) {
            pathElements.add(0, item.getValue());
            item = item.getParent();
        }
        return userHome + File.separator + String.join(File.separator, pathElements);
    }

    private void displayImage(File file) {
        imageView.setImage(new Image(file.toURI().toString()));
    }
}