/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tifon.kickstarter.beans;

import com.tifon.kickstarter.entities.Categoria;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.tyranos.freezer.Table;

/**
 *
 * @author mauricio
 */
@ManagedBean(name = "CategoriaBean")
@SessionScoped
public class CategoriaBean {
    
    
    private Table<Categoria> CategoriaDAO;
    
    @PostConstruct
    public void init(){
        try {
            CategoriaDAO = new Table(new org.tyranos.freezer.DBConnectionMySQL("192.168.0.12","root","asdf","kickfounding"),new Categoria());
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Categoria> getListaCategoria(){
        try {
            return CategoriaDAO.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CategoriaBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CategoriaBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }
}
