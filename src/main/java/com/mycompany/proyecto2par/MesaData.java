/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stalin Garcia
 */
public class MesaData {
    
    static String ruta = "mesas.txt";
    static ArrayList<Mesa> mesas = cargarMesasArchivo() ;
    
    
    public static ArrayList<Mesa> cargarMesasArchivo() {
        ArrayList<Mesa> mesas = new ArrayList<>();
        //Usamos la clase BufferedReader para leer archivos de texto
        try {
            URL u = App.class.getResource(ruta);
            File file = new File(u.toURI());
            try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
                String linea;
                //leemos linea a linea hasta llegar la final del archivo
                bf.readLine();
                while ((linea = bf.readLine()) != null) {
                    String[] partes = linea.split(",");
                    String[] ubi = partes[2].split(":");
                    UbicacionesMesas ubicacion = new UbicacionesMesas(Double.valueOf(ubi[0]),Double.valueOf(ubi[1]));
                    Mesa mesa = new Mesa(partes[0],Integer.parseInt(partes[1]),ubicacion,Boolean.parseBoolean(partes[3]),Integer.parseInt(partes[4]));             
                    mesas.add(mesa);
                }
              
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
                throw ex;
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                throw ex;
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return mesas;
    }
    
    
    public static void registrarMesa(Mesa m) throws IOException, URISyntaxException  {
        
        mesas.add(m);
        URL u = App.class.getResource(ruta);
        File file = new File(u.toURI());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))){
            String linea = m.getNumeroMesa()+","+m.getCapacidad()+","+m.getUbicacion()+","+m.getEstado()+","+m.getTamanio();
            
            bw.write(linea);
            bw.newLine();
            
        }
    }  
    
    public static void eliminarMesa(Mesa m) throws URISyntaxException, IOException {

        URL u = App.class.getResource(ruta);
        File file = new File(u.toURI());
        //"src/main/resources/com/mycompany/proyecto2par/mesas.txt"
        //System.out.println(mesas);
        mesas.remove(m);
        //System.out.println(mesas);
        
        
        try ( BufferedWriter bwr = new BufferedWriter(new FileWriter(file, false));) {
            bwr.write("Numero,Capacidad,Ubicacion,Estado,Tamanio");
            bwr.newLine();
            for (Mesa mesa : mesas) {
                String linea = mesa.getNumeroMesa() + "," + mesa.getCapacidad() + "," + mesa.getUbicacion() + "," + mesa.getEstado() + "," + mesa.getTamanio();
                bwr.write(linea);
                bwr.newLine();
            }

        }
    }
    
   
}
