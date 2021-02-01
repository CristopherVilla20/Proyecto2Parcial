/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import static com.mycompany.proyecto2par.ComidaData.comidas;
import static com.mycompany.proyecto2par.ComidaData.ruta;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Stalin Garcia
 */
public class VentasData {
    
    static String ruta = "ventas.txt";
    static ArrayList<Venta> ventas = leerVentas();

    public static ArrayList<Venta> leerVentas()  {
        ArrayList<Venta> v = new ArrayList<>();
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
                    v.add(new Venta(LocalDate.parse(partes[0]), partes[1], partes[2],partes[3],partes[4],Double.parseDouble(partes[5])));
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
        return v;
    }
    public static void registrarVenta (Venta v) throws IOException {
        
        ventas.add(v);
        try{
            URL u = App.class.getResource(ruta);
            File file = new File(u.toURI());
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))){
                //Fecha;Numero_Mesa;Mesero;Cuenta;Cliente;Total
                String linea = v.getFecha()+";"+v.getNumMesa()+";"+v.getNombreMesero()+";"+v.getCuenta()+";"+v.getNomCliente()+";"+v.getTotal();
                bw.write(linea);
                bw.newLine();
            }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }  
    
}
