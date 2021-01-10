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
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author Stalin Garcia
 */
public class MenuData {
    
    static String ruta = "platos.txt";

    public static ArrayList<Menu> leerMenus() throws IOException {
        ArrayList<Menu> m = new ArrayList<>();
        //Usamos la clase BufferedReader para leer archivos de texto
        try {
            URL u = App.class.getResource(ruta);
            File file = new File(u.toURI());
            try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
                String linea;
                //leemos linea a linea hasta llegar la final del archivo
                bf.readLine();
                while ((linea = bf.readLine()) != null) {
                    //dividir la en partes 
                    String[] partes = linea.split(";");
                    m.add(new Menu(partes[0], partes[1], Double.parseDouble(partes[2])));
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
        return m;
    }
    
    public static void registrarMenu(Menu m) throws IOException {

        try{
            URL u = App.class.getResource(ruta);
            File file = new File(u.toURI());
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))){
                String linea = m.getTipo()+";"+m.getNombre()+";"+m.getPrecio();
                bw.write(linea);
                bw.newLine();
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }  
   
}
