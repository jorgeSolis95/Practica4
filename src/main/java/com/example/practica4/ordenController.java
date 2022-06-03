package com.example.practica4;

import com.example.practica4.DB.MySQL;
import com.example.practica4.DB.TaqueriaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ordenController implements Initializable {
    private TaqueriaDAO taqueriaDAO = new TaqueriaDAO(MySQL.getConnection());
    ObservableList<Orden> listaOrden= FXCollections.observableArrayList();
    ArrayList<String> orden =new ArrayList<>();

    int cantidad=0;
    int id_tabla;
    String nombre_tabla,img_tabla;
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
            TableRow<Orden> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Orden rowData = row.getItem();
                    id_tabla=rowData.getId_orden();
                    nombre_tabla=rowData.getNom_bebida();
                    precio_tabla= rowData.getPrecio_bebida();
                    img_tabla= rowData.getNom_taco();
                    System.out.println(rowData.getNom_bebida()+" id: "+id_tabla);
                    seleccionaOrden();
                }
            });
            return row ;
        });
    }

    public void initTable(){
        TableColumn<Orden,Integer> id = new TableColumn<>("id");
        TableColumn<Orden, String>taco=new TableColumn<>("Taco");
        TableColumn<Orden, String>precioTaco=new TableColumn<>("Precio taco");
        TableColumn<Orden,String> bebida = new TableColumn<>("Bebida");
        TableColumn<Orden,Double> precioBebida = new TableColumn<>("Precio bebida");
        TableColumn<Orden,Double> total = new TableColumn<>("Total");
        id.setCellValueFactory(new PropertyValueFactory<>("id_orden"));
        taco.setCellValueFactory(new PropertyValueFactory<>("nom_taco"));
        precioTaco.setCellValueFactory(new PropertyValueFactory<>("precio_taco"));
        bebida.setCellValueFactory(new PropertyValueFactory<>("nom_bebida"));
        precioBebida.setCellValueFactory(new PropertyValueFactory<>("precio_bebida"));
        total.setCellValueFactory(new PropertyValueFactory<>("total"));
        tabla.getColumns().addAll(taco,precioTaco,bebida,precioBebida,total);
        tabla.setItems(listaOrden);
    }

    public void seleccionaOrden(){
        try {
            FXMLLoader l = new FXMLLoader();
            Parent root=l.load(getClass().getResource("agregarbebida-view.fxml").openStream());
            agregarBebidaController controller=(agregarBebidaController) l.getController();
            controller.recibir_parametors(id_tabla,nombre_tabla,precio_tabla,img_tabla);
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
        listaOrden = taqueriaDAO.mostrarOrden();
    }
}
