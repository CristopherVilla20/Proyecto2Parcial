/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class UsuarioData {

    static String ruta = "usuarios.txt";

    public static ArrayList<Usuario> leerUsuarios() throws IOException {
        ArrayList<Usuario> us = new ArrayList<>();
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
                    us.add(new Usuario(partes[0], partes[1], partes[2], partes[3]));
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
        return us;
    }
}
