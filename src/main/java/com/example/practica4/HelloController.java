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
import javafx.scene.Parent;
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
    TaqueriaDAO taqueriaDAO=new TaqueriaDAO(MySQL.getConnection());
    int ordenTaco=0,ordenBebida=0,idTaco,cantTaco,idBebida,cantBebida;
    double totalTaco,totalBebida,total;

    @FXML
    private Label welcomeText;
    @FXML
    private Button btn_pagar,btn_taco, btn_bebida,btn_orden;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    protected void onTacoButtonClick(){
        System.out.println("cartel MMP");

        System.out.println(idTaco+" cant "+cantTaco+" "+totalTaco);
        ordenTaco++;
        try {
            FXMLLoader l = new FXMLLoader();
            Parent root=l.load(getClass().getResource("taco-view.fxml").openStream());
            TacoController controller=(TacoController) l.getController();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(idTaco+" si "+cantTaco+" "+totalTaco);
        btn_taco.setOnAction(e -> onTacoButtonClick());
        btn_bebida.setOnAction(e-> onBebidaButtonClick());
        btn_orden.setOnAction(e->onOrdenButtonClick());
        btn_pagar.setOnAction(e->onPagar());
    }

    private void onPagar() {
        Orden orden=new Orden();
        Taco taco=new Taco();
        Bebida bebida=new Bebida();
        taco.setId_taco(idTaco);
        taco.setTotal_taco(total);
        taco.setCantidad_taco(cantTaco);
        bebida.setId_bebida(idBebida);
        bebida.setCantidad_bebida(cantBebida);
        //taqueriaDAO.RegistrarTaco(taco,bebida);
    }
    public void recibirParametrosTacos(int id,int cantidad,double total){
        idTaco=id;
        cantTaco=cantidad;
        totalTaco=total;
    }
    public void recibirParametrosBebidas(int id,int cantidad,double total){
        idBebida=id;
        cantBebida=cantidad;
        totalBebida=total;
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