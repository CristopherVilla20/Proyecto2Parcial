/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Comida {
    private String nombre;
    private double precio;
    private String tipo;
    private String imagen;

    public Comida(String nombre, double precio,String tipo, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public static List<Comida> cargarComidasArchivo(String nombre_archivo) throws IOException{
        
        String ruta = "comidas.txt";
        List<Comida> comidas = new ArrayList<>();
        try(InputStream input = App.class.getResource(ruta).openStream();
                BufferedReader bf = new BufferedReader(
                                    new InputStreamReader(input,"UTF-8"))){
            String linea;
            bf.readLine();
            while((linea = bf.readLine())!=null){
                System.out.println(linea);
                String[] p = linea.split(",");
                Comida comida = new Comida(p[0],Double.parseDouble(p[1]),p[2],p[3]);
                System.out.println("hola");
                comidas.add(comida);
            }         
        }  catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return comidas;
    }
}
