/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tifon.kickstarter.beans;

import com.tifon.kickstarter.entities.Proyecto;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.tyranos.freezer.DBConnection;
import org.tyranos.freezer.DBConnectionMySQL;
import org.tyranos.freezer.Table;

/**
 *
 * @author mauricio
 */
@ManagedBean(name = "ProyectoBean")
@javax.faces.bean.ViewScoped
public class SaveProjectBean implements Serializable {

    private static final long serialVersionUID = 321L;
    private Proyecto proyectoNuevo;
    private String Test;
    private Date fechaTemporal;
    private Table<Proyecto> ProyectoDTO;

    private Part ImageArchivo;
    private Part VideoArchivo;
    private String VideoPath;
    private String ImagePath;
    
    public SaveProjectBean() {
 

    }

    public void uploadImage() {
        try {
//      FileContent = new Scanner(Archivo.getInputStream())
//          .useDelimiter("\\A").next();
//        System.out.println(FileContent + " uploaded");
            
            InputStream input = ImageArchivo.getInputStream();
            Files.copy(input, new File("/tmp/" + ImageArchivo.hashCode() + ".jpg").toPath());
            ImagePath = ImageArchivo.hashCode() + ".jpg";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
      public void uploadVideo() {
        try {
//      FileContent = new Scanner(Archivo.getInputStream())
//          .useDelimiter("\\A").next();
//        System.out.println(FileContent + " uploaded");
            if(VideoArchivo != null){
            InputStream input = VideoArchivo.getInputStream();
            Files.copy(input, new File("/tmp/" + VideoArchivo.hashCode() + ".mp4").toPath());
            VideoPath = VideoArchivo.hashCode() + ".mp4";
            }
        } catch (IOException e) {
            
        }
    }


    public void save() {

        try {
            System.out.println(proyectoNuevo);
            FacesContext context = FacesContext.getCurrentInstance();

            String id = context.getExternalContext().getRequestParameterMap().get("idUsuario");
            System.out.println(id);
            proyectoNuevo.setPlazoFinanciamiento(new java.sql.Date(fechaTemporal.getTime()));
            proyectoNuevo.setImagen(ImagePath);
            proyectoNuevo.setIdUsuario(Integer.parseInt(id));
            proyectoNuevo.setRutaVideo(VideoPath);
            this.ProyectoDTO = new Table(new org.tyranos.freezer.DBConnectionMySQL("192.168.0.12","root","asdf","kickfounding"),new Proyecto());
            ProyectoDTO.Insert(proyectoNuevo);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(SaveProjectBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SaveProjectBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SaveProjectBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @PostConstruct
    public void init() {
        try {
            proyectoNuevo = new Proyecto();
            System.out.println("mauricio");
            ProyectoDTO = new Table(new DBConnectionMySQL("192.168.0.12","root","asdf","kickfounding"),new Proyecto());
        } catch (SQLException ex) {
            Logger.getLogger(SaveProjectBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getVideoPath() {
        return VideoPath;
    }

    public void setVideoPath(String VideoPath) {
        this.VideoPath = VideoPath;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String ImagePath) {
        this.ImagePath = ImagePath;
    }

    
    public Date getFechaTemporal() {
        return fechaTemporal;
    }

    public void setFechaTemporal(Date fechaTemporal) {
        this.fechaTemporal = fechaTemporal;
    }

    public Part getImageArchivo() {
        return ImageArchivo;
    }

    public void setImageArchivo(Part ImageArchivo) {
        this.ImageArchivo = ImageArchivo;
    }

    public Part getVideoArchivo() {
        return VideoArchivo;
    }

    public void setVideoArchivo(Part VideoArchivo) {
        this.VideoArchivo = VideoArchivo;
    }
    
 

    public String getTest() {
        return Test;
    }

    public void setTest(String test) {
        this.Test = test;
    }

    public Proyecto getProyectoNuevo() {
        return proyectoNuevo;
    }

    public void setProyectoNuevo(Proyecto proyectoNuevo) {
        this.proyectoNuevo = proyectoNuevo;
    }


}
