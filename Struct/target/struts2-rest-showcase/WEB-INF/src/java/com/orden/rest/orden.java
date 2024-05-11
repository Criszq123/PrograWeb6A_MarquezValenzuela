package com.orden.rest;

import java.util.List;

public class Orden {

    private int id;

    private List<String> productos;

    private int idUser;

    public Orden(int id, List<String> productos, int idUser) {
        this.id = id;
        this.productos = productos;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getProductos() {
        return productos;
    }

    public void setProductos(List<String> productos) {
        this.productos = productos;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
