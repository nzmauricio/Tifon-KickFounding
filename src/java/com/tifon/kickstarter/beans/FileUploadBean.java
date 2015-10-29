package com.tifon.kickstarter.beans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author mauricio
 */
@SessionScoped
@ManagedBean(name = "FileUploadBean", eager = true)
public class FileUploadBean implements Serializable {

    private Part Archivo;
    private String FileContent;
    private String FilePath;

    public void upload() {
        try {
//      FileContent = new Scanner(Archivo.getInputStream())
//          .useDelimiter("\\A").next();
//        System.out.println(FileContent + " uploaded");
            
            InputStream input = Archivo.getInputStream();
            Files.copy(input, new File(Archivo.hashCode() + ".uploaded").toPath());
            FilePath = Archivo.hashCode() + ".uploaded";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Part getArchivo() {
        return Archivo;
    }

    public void setArchivo(Part file) {
        this.Archivo = file;
    }

    public String getFileContent() {
        return FileContent;
    }

    public void setFileContent(String FileContent) {
        this.FileContent = FileContent;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String FilePath) {
        this.FilePath = FilePath;
    }

}
