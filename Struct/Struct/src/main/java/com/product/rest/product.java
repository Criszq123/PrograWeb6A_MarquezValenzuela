package com.product.rest;

public class product {
    private int id;
    private String name;
    private String categoria;
    private String precio;

    public product(int id, String name, String categoria, String precio) {
        this.id = id;
        this.name = name;
        this.categoria = categoria;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
