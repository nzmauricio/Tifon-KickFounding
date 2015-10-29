/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tifon.kickstarter.beans;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author mauricio
 */
@ManagedBean(name = "ImageLoader")
@ApplicationScoped
public class ImageLoaderBean {
    
    public StreamedContent getImage(){
           FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            // So, we're rendering the view. Return a stub StreamedContent so that it will generate right URL.
            return new DefaultStreamedContent();
        }
        else {
               try {
                   // So, browser is requesting the image. Get ID value from actual request param.
                   String path = context.getExternalContext().getRequestParameterMap().get("path");
                   
                   
                   File fnew=new File(path);
                   BufferedImage originalImage=ImageIO.read(fnew);
                   ByteArrayOutputStream baos=new ByteArrayOutputStream();
                   ImageIO.write(originalImage, "jpg", baos );
                   
                   
                   
                   return new DefaultStreamedContent(new ByteArrayInputStream(baos.toByteArray()));
               } catch (IOException ex) {
                   Logger.getLogger(ImageLoaderBean.class.getName()).log(Level.SEVERE, null, ex);
               }
        }
        return null;
        
    }
}
