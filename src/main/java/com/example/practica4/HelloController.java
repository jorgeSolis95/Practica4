package com.example.practica4;

import com.example.practica4.DB.MySQL;
import com.example.practica4.DB.TaqueriaDAO;
import com.example.practica4.HelloApplication;
import com.example.practica4.Taco;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private FileChooser flcArchivo;
    public File file;
    private Image imgAbrir;
    private ImageView imvAbrir;
    ObservableList<Taco>listaTaco= FXCollections.observableArrayList();

    @FXML
    private Label welcomeText;
    @FXML
    private Button btn_agregar,btn_eliminar,btn_modificar,btn_taco, btn_bebida,btn_orden;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    protected void onTacoButtonClick(){
        System.out.println("cartel MMP");
        try {
            Stage stage = (Stage) btn_taco.getScene().getWindow();
            FXMLLoader l = new FXMLLoader(HelloApplication.class.getResource("taco-view.fxml"));
            Scene scene = new Scene(l.load(),700,600);
            scene.getStylesheets().add(getClass().getResource("css/styles.css").toExternalForm());
            stage.setResizable(false);
            stage.setTitle("Bienvenido");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_taco.setOnAction(e -> onTacoButtonClick());
        btn_bebida.setOnAction(e-> onBebidaButtonClick());
        btn_orden.setOnAction(e->onOrdenButtonClick());


    }

    private void onOrdenButtonClick() {
        try {
            Stage stage = (Stage) btn_bebida.getScene().getWindow();
            FXMLLoader l = new FXMLLoader(HelloApplication.class.getResource("orden-view.fxml"));
            Scene scene = new Scene(l.load(),700,600);
            scene.getStylesheets().add(getClass().getResource("css/styles.css").toExternalForm());
            stage.setResizable(false);
            stage.setTitle("Bienvenido");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    private void onBebidaButtonClick() {
        try {
            Stage stage = (Stage) btn_bebida.getScene().getWindow();
            FXMLLoader l = new FXMLLoader(HelloApplication.class.getResource("bebida-view.fxml"));
            Scene scene = new Scene(l.load(),700,600);
            scene.getStylesheets().add(getClass().getResource("css/styles.css").toExternalForm());
            stage.setResizable(false);
            stage.setTitle("Bienvenido");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}