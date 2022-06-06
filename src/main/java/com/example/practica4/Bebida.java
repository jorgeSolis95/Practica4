package com.example.practica4;

public class Bebida {
    int id_bebida,cantidad_bebida;
    String nom_bebida;
    double precio_bebida;
    String img_bebida;

    public int getId_bebida() {
        System.out.println("conchetumadre");
        return id_bebida;
    }

    public int getCantidad_bebida() {
        return cantidad_bebida;
    }

    public void setCantidad_bebida(int cantidad_bebida) {
        this.cantidad_bebida = cantidad_bebida;
    }

    public void setId_bebida(int id_bebida) {
        this.id_bebida = id_bebida;
    }

    public String getNom_bebida() {
        return nom_bebida;
    }

    public void setNom_bebida(String nom_bebida) {
        this.nom_bebida = nom_bebida;
    }

    public double getPrecio_bebida() {
        return precio_bebida;
    }

    public void setPrecio_bebida(double precio_bebida) {
        this.precio_bebida = precio_bebida;
    }

    public String getImg_bebida() {
        return img_bebida;
    }

    public void setImg_bebida(String img_bebida) {
        this.img_bebida = img_bebida;
    }
}
