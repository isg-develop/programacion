package com.example.laptop.interfaz;

/**
 * Created by Karen on 08/11/2015.
 */
public class Titular {
    private String titulo, descripcion, imagen;
    private int img;

    public Titular(){

    }
    public Titular(String titulo,String descripcion, int img){
        this.titulo=titulo;
        this.descripcion= descripcion;
        this.img=img;

    }
    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo=titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getImg() {
        return img;
    }
}
