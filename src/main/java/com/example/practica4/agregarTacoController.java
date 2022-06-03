package com.example.practica4;

import com.example.practica4.DB.MySQL;
import com.example.practica4.DB.TaqueriaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class agregarTacoController implements Initializable {
    private FileChooser flcArchivo;
    int id;
    String nombre,img;
    double precio,total;

    @FXML
    private Label lbl_taco;

    @FXML
    private Label lbl_precio;

    @FXML
    private SplitMenuButton slpitBtnCantidad;

    @FXML
    private Button btn_guardar;

    @FXML
    private Button btn_cancelar;


    @FXML
    private ImageView Imv_buscarImagen;

    File imgFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_guardar.setOnAction(e->GuardarTaco());
        btn_cancelar.setOnAction(e->Cancelar());
    }
    public void recibir_parametors(int id_tabla,String nombre_tabla,double precio_tabla,String img_tabla){
        id=id_tabla;
        nombre=nombre_tabla;
        precio=precio_tabla;
        img=img_tabla;
        //lbl_taco.setText(listaTaco.get(1).getNom_taco());
    }
    private void GuardarTaco(){
        lbl_taco.setText(nombre);
        lbl_precio.setText(""+precio);
        Image image = new Image("file:C:\\Users\\yorch\\IdeaProjects\\Practica4\\src\\main\\resources\\com\\example\\practica4\\img\\" + img);
        Imv_buscarImagen.setImage(image);
        System.out.println(id);
        System.out.println(nombre);
        System.out.println(precio);
    }

    private void BuscarImagen() {

        flcArchivo = new FileChooser();
        flcArchivo.setTitle("Buscar imagen");
        flcArchivo.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        imgFile=flcArchivo.showOpenDialog(null);
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            Imv_buscarImagen.setImage(image);
            System.out.println(imgFile.getAbsolutePath());
        }

    }
    private void Cancelar(){
        try {
            AlertaAtras();
            Stage stage = (Stage) btn_cancelar.getScene().getWindow();
            FXMLLoader l = new FXMLLoader(HelloApplication.class.getResource("bebidaview.fxml"));
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
    private void AlertaAtras(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Se cancelo");
        alert.showAndWait();
    }
}
