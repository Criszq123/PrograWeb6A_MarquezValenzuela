package com.orden.rest;

public class Orden {

    private int id;
    private String lista[];
    private int idUser;

    public Orden(int id, String[] lista, int idUser) {
        this.id = id;
        this.lista = lista;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getLista() {
        return lista;
    }

    public void setLista(String[] lista) {
        this.lista = lista;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
