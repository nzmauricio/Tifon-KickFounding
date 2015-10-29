/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tifon.kickstarter.beans;

import com.tifon.kickstarter.entities.Usuario;
import com.tifon.kickstarter.entities.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.tyranos.freezer.Table;

/**
 *
 * @author mauricio
 */
@ManagedBean(name = "UsuarioBean")
@SessionScoped
public class UsuarioBean implements Serializable{
        private Table<Usuario> UsuarioDAO;
        private Usuario CurrentUser;
        private boolean logeado;
    
  @PostConstruct
    public void init(){
        try {
            UsuarioDAO = new Table(new org.tyranos.freezer.DBConnectionMySQL("192.168.0.12","root","asdf","kickfounding"),new Usuario());
            CurrentUser = new Usuario();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Usuario> getListaUsuario(){
        try {
            return UsuarioDAO.selectAll();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }
    
    public void validar() throws IOException{
            try {
                ArrayList<Usuario> consulta = UsuarioDAO.Select("Select Usuario.* from Usuario where nombre = '" + CurrentUser.nombre + "' and password = '" + CurrentUser.password + "';");
                if(consulta.isEmpty()){
                    logeado = false;
                    CurrentUser = new Usuario();
                    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                } else {
                    logeado = true;
                    CurrentUser = consulta.get(0);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                    System.out.println(logeado);
                }  
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void registrar() throws IOException{
            try {
                UsuarioDAO.Insert(CurrentUser);
                logeado = true;
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    public Usuario getCurrentUser() {
        return CurrentUser;
    }

    public void setCurrentUser(Usuario CurrentUser) {
        this.CurrentUser = CurrentUser;
    }

    public boolean isLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }
    
    
}
