package com.example.practica4.DB;

import com.example.practica4.Bebida;
import com.example.practica4.Orden;
import com.example.practica4.Taco;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaqueriaDAO {
    private MySQL mySQL;

    Connection conn;

    public TaqueriaDAO(Connection conn){this.conn = conn;}

    public ObservableList<Taco> mostrarTodo()  {
        ObservableList<Taco> lista = FXCollections.observableArrayList();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM taco");
            while (rs.next()){
                Taco p=new Taco();
                p.setId_taco(rs.getInt("id_taco"));
                p.setNom_taco(rs.getString("nom_taco").trim());
                System.out.println(rs.getString("nom_taco"));
                p.setPrecio_taco(rs.getDouble("precio_taco"));
                p.setImg_taco(rs.getString("img_taco").trim());
                lista.add(p);
            }
            rs.close();
            st.close();
        }catch (SQLException e){
            System.out.println(e);
        }

        return lista;
    }
    public ObservableList<Bebida> mostrarBebida()  {
        ObservableList<Bebida> lista = FXCollections.observableArrayList();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM bebida");
            while (rs.next()){
                Bebida p=new Bebida();
                p.setId_bebida(rs.getInt("id_bebida"));
                p.setNom_bebida(rs.getString("nom_bebida").trim());
                System.out.println(rs.getString("nom_bebida"));
                p.setPrecio_bebida(rs.getDouble("precio_bebida"));
                p.setImg_bebida(rs.getString("img_bebida").trim());
                lista.add(p);
            }
            rs.close();
            st.close();
        }catch (SQLException e){
            System.out.println(e);
        }

        return lista;
    }
    public ObservableList<Orden> mostrarOrden()  {
        ObservableList<Orden> lista = FXCollections.observableArrayList();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select t.nom_taco,t.precio_taco, b.nom_bebida,b.precio_bebida, total from orden o join taco t on o.id_taco =t.id_taco JOIN bebida b on o.id_bebida =b.id_bebida");
            while (rs.next()){
                Orden p=new Orden();
                p.setNom_taco(rs.getString("nom_taco").trim());
                p.setPrecio_taco(rs.getDouble("precio_taco"));
                p.setNom_bebida(rs.getString("nom_bebida").trim());
                p.setPrecio_bebida(rs.getDouble("precio_bebida"));
                System.out.println(rs.getString("nom_bebida"));
                p.setTotal(rs.getDouble("total"));
                lista.add(p);
            }
            rs.close();
            st.close();
        }catch (SQLException e){
            System.out.println(e);
        }

        return lista;
    }

    public void RegistrarTaco(Taco taco,Bebida bebida){
        try{
            String query= "INSERT INTO orden (id_taco,id_bebida,total,cantidad_tacos,cantidad_bebida) VALUES(?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1,taco.getId_taco());
            st.setInt(2,bebida.getId_bebida());
            st.setDouble(3,taco.getTotal_taco());
            st.setInt(4,taco.getCantidad_taco());
            st.setInt(5,bebida.getCantidad_bebida());
            System.out.println(st);
            st.execute();
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setTitle("Exito");
            alert.setContentText("Se registro con exito al usuario");
            alert.show();
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("a");
        }
    }
}
