package com.example.gallery;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

public class ImageGalleryApp extends Application {

    private final String[] imagePaths = {
            "/1.jpg",
            "/3.jpg",
            "/2.jpg",
            "/4.jpg",
            "5.jpg",
            "6.jpg",
            "7.jpg",
            "8.jpg",
            "9.jpg",
            "10.jpg",
            "11.jpg",
            "12.jpg",
    };

    private ImageView fullImageView;
    private GridPane thumbnailGrid;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles.css")).toExternalForm());

        thumbnailGrid = createThumbnailGrid();
        fullImageView = new ImageView();
        fullImageView.setId("fullImageView");
        fullImageView.setFitWidth(600);
        fullImageView.setFitHeight(400);
        fullImageView.setPreserveRatio(true);
        fullImageView.setSmooth(true);
        fullImageView.setCache(true);

        Button clearButton = new Button("Clear");
        clearButton.setOnAction(e -> clearFullImageView());

        BorderPane.setAlignment(clearButton, javafx.geometry.Pos.CENTER);
        root.setTop(clearButton);
        root.setCenter(fullImageView);
        root.setLeft(thumbnailGrid);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Image Gallery");
        primaryStage.show();
    }

    private GridPane createThumbnailGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        for (int i = 0; i < imagePaths.length; i++) {
            ImageView thumbnail = createThumbnail(imagePaths[i]);
            int row = i / 3;
            int col = i % 3;
            gridPane.add(thumbnail, col, row);
        }

        return gridPane;
    }

    private ImageView createThumbnail(String imagePath) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.getStyleClass().add("thumbnail");
        imageView.setFitWidth(150);
        imageView.setFitHeight(100);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setOnMouseClicked(e -> showFullImage(imagePath));
        return imageView;
    }

    private void showFullImage(String imagePath) {
        Image image = new Image(imagePath);
        fullImageView.setImage(image);
    }

    private void clearFullImageView() {
        fullImageView.setImage(null);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
