/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tifon.kickstarter.beans;

import com.tifon.kickstarter.entities.Donacion;
import com.tifon.kickstarter.entities.Proyecto;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.imageio.ImageIO;
import org.tyranos.freezer.Table;

/**
 *
 * @author mauricio
 */
@ManagedBean(name = "ProjectLoader", eager = true)
@ApplicationScoped
public class LoadProjectsBean {
    
    Table<Proyecto> ProyectoDAO;
    Table<Donacion> DonacionDAO;
    @PostConstruct
    public void init(){
        try {
            ProyectoDAO = new Table(new org.tyranos.freezer.DBConnectionMySQL("192.168.0.12","root","asdf","kickfounding"),new Proyecto());
            DonacionDAO = new Table(new org.tyranos.freezer.DBConnectionMySQL("192.168.0.12","root","asdf","kickfounding"),new Donacion());
        } catch (SQLException ex) {
            Logger.getLogger(LoadProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Proyecto> getListaProyectos(){
        try {
            return ProyectoDAO.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(LoadProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LoadProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LoadProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public Proyecto getSimpleProyecto(){
        try {
            	Map<String,String> params = 
		FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
                
            
            String id = params.get("idProject");
            return ProyectoDAO.Select("id", 0, Integer.parseInt(id)).get(0);
        } catch (SQLException ex) {
            Logger.getLogger(LoadProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LoadProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LoadProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
        public double getTotalFinanciado(){
        try {
            	Map<String,String> params = 
		FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
                
            
            String id = params.get("idProject");
            double total = 0;
            for(Donacion d: DonacionDAO.Select("idProyecto", 0, Integer.parseInt(id))){
                total += d.monto;
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(LoadProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LoadProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LoadProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
        
            public int getPorcentajeFinanciado(){
        try {
            	Map<String,String> params = 
		FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
                
            
            String id = params.get("idProject");
            double total = 0;
            for(Donacion d: DonacionDAO.Select("idProyecto", 0, Integer.parseInt(id))){
                total += d.monto;
            }
            double meta = ProyectoDAO.Select("id", 0, Integer.parseInt(id)).get(0).meta;
            return  (int) ((total * 100) / meta);
        } catch (SQLException ex) {
            Logger.getLogger(LoadProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(LoadProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(LoadProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    
    private byte[] loadImage(String path){
        try {
            File imgPath = new File(path);
            BufferedImage bufferedImage = ImageIO.read(imgPath);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            return baos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(LoadProjectsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
