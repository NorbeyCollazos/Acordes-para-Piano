package com.ncrdesarrollo.acordesmusicales.models;

public class Acordes {

    private int id;
    private String nombre;
    private String html;
    private String nota;
    private String posicion;

    public Acordes() {
    }

    public Acordes(int id, String nombre, String html, String nota, String posicion) {
        this.id = id;
        this.nombre = nombre;
        this.html = html;
        this.nota = nota;
        this.posicion = posicion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}
