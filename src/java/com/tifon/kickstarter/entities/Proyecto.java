/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tifon.kickstarter.entities;

import java.io.Serializable;
import java.sql.Date;


/**
 *
 * @author mauricio
 */
public class Proyecto implements Serializable{
    
    public int id;
    public String titulo;
    public String imagen;
    public String descripcion;
    public Date plazoFinanciamiento;
    public int meta;
    public String rutaVideo;
    public int estado;
    public int idCategoria;
    public int idUsuario;
    
    public Proyecto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getPlazoFinanciamiento() {
        return plazoFinanciamiento;
    }

    public void setPlazoFinanciamiento(Date plazoFinanciamiento) {
        this.plazoFinanciamiento = plazoFinanciamiento;
    }

    public int getMeta() {
        return meta;
    }

    public void setMeta(int meta) {
        this.meta = meta;
    }

    public String getRutaVideo() {
        return rutaVideo;
    }

    public void setRutaVideo(String rutaVideo) {
        this.rutaVideo = rutaVideo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Proyecto{" + "id=" + id + ", titulo=" + titulo + ", imagen=" + imagen + ", descripcion=" + descripcion + ", plazoFinanciamiento=" + plazoFinanciamiento + ", meta=" + meta + ", rutaVideo=" + rutaVideo + ", estado=" + estado + ", idCategoria=" + idCategoria + ", idUsuario=" + idUsuario + '}';
    }
    
    
    
    
    
}
