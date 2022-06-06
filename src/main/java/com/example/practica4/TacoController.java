package com.example.practica4;


import com.example.practica4.DB.MySQL;
import com.example.practica4.DB.TaqueriaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.ResourceBundle;

public class TacoController implements Initializable {
    private TaqueriaDAO taqueriaDAO = new TaqueriaDAO(MySQL.getConnection());
    ObservableList<Taco> listaTaco= FXCollections.observableArrayList();
    ArrayList <String> orden =new ArrayList<>();

    int cantidad=0;
    int id_tabla;
    String nomre_tabla,img_tabla;
    double precio_tabla;

    @FXML
    private Button btn_atras;

    @FXML
    private TableView tabla;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_atras.setOnAction(e->atras());
        listarTaco();
        initTable();
        dobleClick();
        System.out.println("llegue aqui "+listaTaco.get(0).getPrecio_taco());
        System.out.println("Llegue aca"+id_tabla);
    }
    public void dobleClick(){
        tabla.setRowFactory( tv -> {
            TableRow<Taco> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Taco rowData = row.getItem();
                    id_tabla=rowData.getId_taco();
                    nomre_tabla=rowData.getNom_taco();
                    precio_tabla= rowData.getPrecio_taco();
                    img_tabla= rowData.getImg_taco();
                    System.out.println(rowData.getNom_taco()+" id: "+id_tabla);
                    seleccionaTaco();
                    cerrar();
                }
            });
            return row ;
        });
    }
    public void atras(){
        try {
            Stage stage = (Stage) btn_atras.getScene().getWindow();
            FXMLLoader l = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
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

    public void initTable(){
        TableColumn<Taco,Integer> id = new TableColumn<>("id");
        TableColumn<Taco,String> taco = new TableColumn<>("nombre");
        TableColumn<Taco,Double> precio = new TableColumn<>("Precio taco");
        TableColumn<Taco,String> img = new TableColumn<>("Foto");
        id.setCellValueFactory(new PropertyValueFactory<>("id_taco"));
        taco.setCellValueFactory(new PropertyValueFactory<>("nom_taco"));
        precio.setCellValueFactory(new PropertyValueFactory<>("precio_taco"));
        img.setCellValueFactory(new PropertyValueFactory<>("img_taco"));
        tabla.getColumns().addAll(id,taco,precio,img);
        tabla.setItems(listaTaco);
    }

    public void seleccionaTaco(){
        try {
            FXMLLoader l = new FXMLLoader();
            Parent root=l.load(getClass().getResource("agregarTaco-view.fxml").openStream());
            agregarTacoController controller=(agregarTacoController) l.getController();
            controller.recibir_parametors(id_tabla,nomre_tabla,precio_tabla,img_tabla);
            Scene scene = new Scene(root);
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
        Stage stage = (Stage) this.btn_atras.getScene().getWindow();
        stage.close();
    }

    public void listarTaco() {
        listaTaco = taqueriaDAO.mostrarTodo();
    }
}
