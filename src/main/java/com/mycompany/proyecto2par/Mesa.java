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
public class Mesa {
    private String numeroMesa;
    private int capacidad;
    private UbicacionesMesas ubicacion;
    private boolean estado;
    private int tamanio;

    public Mesa(String numeroMesa, int capacidad, UbicacionesMesas ubicacion, boolean estado,int tamanio) {
        this.numeroMesa = numeroMesa;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.estado = estado;
        this.tamanio = tamanio;
        
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public int getTamanio() {
        return tamanio;
    }

    public String getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public UbicacionesMesas getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionesMesas ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public static List<Mesa> cargarMesasArchivo(String nombre_archivo) throws IOException{
        
        String ruta = "mesas.txt";
        List<Mesa> mesas = new ArrayList<>();
        try(InputStream input = App.class.getResource(ruta).openStream();
                BufferedReader bf = new BufferedReader(
                                    new InputStreamReader(input,"UTF-8"))){
            String linea;
            bf.readLine();
            while((linea = bf.readLine())!=null){
                String[] p = linea.split(",");
                String[] u = p[2].split(":");
                UbicacionesMesas ubicacion = new UbicacionesMesas(Double.valueOf(u[0]),Double.valueOf(u[1]));
                Mesa mesa = new Mesa(p[0],Integer.parseInt(p[1]),ubicacion,Boolean.parseBoolean(p[3]),Integer.parseInt(p[4]));             
                mesas.add(mesa);
            }         
        }  catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return mesas;
    }
}
