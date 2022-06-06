package com.example.practica4;


import com.example.practica4.DB.MySQL;
import com.example.practica4.DB.TaqueriaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BebidaView implements Initializable {
    private TaqueriaDAO taqueriaDAO = new TaqueriaDAO(MySQL.getConnection());
    ObservableList<Bebida> listaBebida= FXCollections.observableArrayList();
    ArrayList <String> orden =new ArrayList<>();

    int cantidad=0;
    int id_tabla;
    String nomre_tabla,img_tabla;
    double precio_tabla;

    private Button btn_agregar=new Button();

    @FXML
    private Button btn_atras;

    @FXML
    private TableView tabla;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btn_atras.setOnAction(e->atras());
        listarbebida();
        initTable();
        dobleClick();
        System.out.println("llegue aqui "+listaBebida.get(0).getPrecio_bebida());
        System.out.println("Llegue aca"+id_tabla);
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

    public void dobleClick(){
        tabla.setRowFactory( tv -> {
            TableRow<Bebida> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Bebida rowData = row.getItem();
                    id_tabla=rowData.getId_bebida();
                    nomre_tabla=rowData.getNom_bebida();
                    precio_tabla= rowData.getPrecio_bebida();
                    img_tabla= rowData.getImg_bebida();
                    System.out.println(rowData.getNom_bebida()+" id: "+id_tabla);
                    seleccionabebida();
                    cerrar();
                }
            });
            return row ;
        });
    }

    private void cerrar() {
        Stage stage = (Stage) this.btn_atras.getScene().getWindow();
        stage.close();
    }

    public void initTable(){
        TableColumn<Bebida,Integer> id = new TableColumn<>("id");
        TableColumn<Bebida,String> bebida = new TableColumn<>("nombre");
        TableColumn<Bebida,Double> precio = new TableColumn<>("Precio bebida");
        TableColumn<Bebida,String> img = new TableColumn<>("Foto");
        id.setCellValueFactory(new PropertyValueFactory<>("id_bebida"));
        bebida.setCellValueFactory(new PropertyValueFactory<>("nom_bebida"));
        precio.setCellValueFactory(new PropertyValueFactory<>("precio_bebida"));
        img.setCellValueFactory(new PropertyValueFactory<>("img_bebida"));
        tabla.getColumns().addAll(id,bebida,precio,img);
        tabla.setItems(listaBebida);
    }

    public void seleccionabebida(){
        try {
            FXMLLoader l = new FXMLLoader();
            Parent root=l.load(getClass().getResource("agregarbebida-view.fxml").openStream());
            agregarBebidaController controller=(agregarBebidaController) l.getController();
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

    public void listarbebida() {
        listaBebida = taqueriaDAO.mostrarBebida();
    }
}
