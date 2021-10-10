package com.ncrdesarrollo.acordesmusicales.models;

public class Escalas {

    private int id;
    private String nombre;
    private String html;
    private String nota;


    public Escalas() {
    }

    public Escalas(int id, String nombre, String html, String nota) {
        this.id = id;
        this.nombre = nombre;
        this.html = html;
        this.nota = nota;

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

}
