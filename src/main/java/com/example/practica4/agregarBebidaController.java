package com.example.practica4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class agregarBebidaController implements Initializable {
    int cantidad;
    private FileChooser flcArchivo;
    int id;
    String nombre,img;
    double precio,total;
    @FXML
    private MenuItem Opcion1,Opcion2,Opcion3,Opcion4,Opcion5,Opcion6,Opcion7,Opcion8,Opcion9,Opcion10;

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
        choiceOption();
        btn_guardar.setOnAction(e->GuardarBebida());
        btn_cancelar.setOnAction(e->Cancelar());
    }
    public void recibir_parametors(int id_tabla,String nombre_tabla,double precio_tabla,String img_tabla){
        id=id_tabla;
        nombre=nombre_tabla;
        precio=precio_tabla;
        img=img_tabla;
        //lbl_taco.setText(listaTaco.get(1).getNom_taco());
    }
    private void GuardarBebida(){
        System.out.println(id);
        System.out.println(nombre);
        System.out.println(precio);
        guardado();
        cerrar();
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
    private void AlertaAtras(){
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setContentText("Se cancelo");
        alert.showAndWait();
    }
    public void choiceOption(){
        Opcion1.setOnAction((e) -> {
            slpitBtnCantidad.setText("1");
            cantidad=1;
            meterValores();
            System.out.println("si");
        });
        Opcion2.setOnAction((e) -> {
            slpitBtnCantidad.setText("2");
            cantidad=2;
            meterValores();
            System.out.println("si");
        });
        Opcion3.setOnAction((e) -> {
            slpitBtnCantidad.setText("3");
            cantidad=3;
            meterValores();
            System.out.println("si");
        });
        Opcion4.setOnAction((e) -> {
            slpitBtnCantidad.setText("4");
            cantidad=4;
            meterValores();
            System.out.println("si");
        });
        Opcion5.setOnAction((e) -> {
            slpitBtnCantidad.setText("5");
            cantidad=5;
            meterValores();
            System.out.println("si");
        });
        Opcion6.setOnAction((e) -> {
            slpitBtnCantidad.setText("6");
            cantidad=6;
            meterValores();
            System.out.println("si");
        });
        Opcion7.setOnAction((e) -> {
            slpitBtnCantidad.setText("7");
            cantidad=7;
            meterValores();
            System.out.println("si");
        });
        Opcion8.setOnAction((e) -> {
            slpitBtnCantidad.setText("8");
            cantidad=8;
            meterValores();
            System.out.println("si");
        });
        Opcion9.setOnAction((e) -> {
            slpitBtnCantidad.setText("9");
            cantidad=9;
            meterValores();
            System.out.println("si");
        });
        Opcion10.setOnAction((e) -> {
            slpitBtnCantidad.setText("10");
            cantidad=10;
            meterValores();
            System.out.println("si");
        });
        slpitBtnCantidad.setOnAction((e)->{
            System.out.println("si se pico");
        });
    }
    private void meterValores(){
        lbl_taco.setText(nombre);
        lbl_precio.setText(""+precio);
        Image image = new Image("file:C:\\Users\\yorch\\IdeaProjects\\Practica4\\src\\main\\resources\\com\\example\\practica4\\img\\" + img);
        Imv_buscarImagen.setImage(image);
    }
    public void guardado(){
        Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Se registro La bebida");
        alert.setContentText("Se tomo la captura de la bebida\nPor favor escoga un taco o \nPase a Orden para pagar");
        alert.showAndWait();
        try {
            FXMLLoader l = new FXMLLoader();
            Parent root=l.load(getClass().getResource("hello-view.fxml").openStream());
            HelloController controller=(HelloController) l.getController();
            total=precio*cantidad;
            controller.recibirParametrosBebidas(id,cantidad,total);
            Scene scene = new Scene(root,620, 540);
            Stage stage=new Stage();
            scene.getStylesheets().add(getClass().getResource("css/styles.css").toExternalForm());
            stage.setTitle("Bienvenido");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public void cerrar(){
        Stage stage = (Stage) this.btn_guardar.getScene().getWindow();
        stage.close();
    }
}
