package com.example.practica4;

public class Orden {
    int id_orden,id_taco,id_bebida,cantidad_taco,cantidad_bebida;
    String nom_taco,nom_bebida;
    double precio_bebida,precio_taco,total;

    public int getId_taco() {
        return id_taco;
    }

    public void setId_taco(int id_taco) {
        this.id_taco = id_taco;
    }

    public int getId_bebida() {
        return id_bebida;
    }

    public void setId_bebida(int id_bebida) {
        this.id_bebida = id_bebida;
    }

    public double getPrecio_bebida() {
        return precio_bebida;
    }

    public void setPrecio_bebida(double precio_bebida) {
        this.precio_bebida = precio_bebida;
    }

    public double getPrecio_taco() {
        return precio_taco;
    }

    public void setPrecio_taco(double precio_taco) {
        this.precio_taco = precio_taco;
    }

    public String getNom_taco() {
        return nom_taco;
    }

    public void setNom_taco(String nom_taco) {
        this.nom_taco = nom_taco;
    }

    public String getNom_bebida() {
        return nom_bebida;
    }

    public void setNom_bebida(String nom_bebida) {
        this.nom_bebida = nom_bebida;
    }

    public int getCantidad_taco() {
        return cantidad_taco;
    }

    public void setCantidad_taco(int cantidad_taco) {
        this.cantidad_taco = cantidad_taco;
    }

    public int getCantidad_bebida() {
        return cantidad_bebida;
    }

    public void setCantidad_bebida(int cantidad_bebida) {
        this.cantidad_bebida = cantidad_bebida;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
