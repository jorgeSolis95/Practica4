package com.example.practica4;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class Taco {
    int id_taco;
    String nom_taco;
    double precio_taco;
    String img_taco;

    public int getId_taco() {
        System.out.println("conchetumadre");
        return id_taco;
    }

    public void setId_taco(int id_taco) {
        this.id_taco = id_taco;
    }

    public String getNom_taco() {
        return nom_taco;
    }

    public void setNom_taco(String nom_taco) {
        this.nom_taco = nom_taco;
    }

    public double getPrecio_taco() {
        return precio_taco;
    }

    public void setPrecio_taco(double precio_taco) {
        this.precio_taco = precio_taco;
    }

    public String getImg_taco() {
        return img_taco;
    }

    public void setImg_taco(String img_taco) {
        this.img_taco = img_taco;
    }
}
