package com.example.m2_pam2_lajusega.models;

public class NotaModel {
    private int _id;
    private String titulo;
    private String contenido;
    private String frase;
    private String fbId;

    public NotaModel() {
    }

    public NotaModel(String titulo, String contenido, String frase) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.frase = frase;

    }

    public NotaModel(int _id, String titulo, String contenido, String frase, String fbId) {
        this._id = _id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.frase = frase;
        this.fbId = fbId;
    }

    @Override
    public String toString() {
        return "NotaModel{" +
                "_id=" + _id +
                ", titulo='" + titulo + '\'' +
                ", frase ='" + frase + '\'' +
                ", contenido='" + contenido + '\'' +
                ", fbid='" + fbId + '\'' +
                '}';
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFrase() {
        return frase;
    }

    public void setFrase(String frase) {
        this.frase = frase;
    }


    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getFbId() { return fbId;
    }
}
