/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.time.LocalDate;

/**
 *
 * @author Stalin Garcia
 */
public class Venta {
    
    private LocalDate fecha;
    private String numMesa;
    private String nombreMesero;
    private String cuenta;
    private String nomCliente;
    private double total;

    public Venta(LocalDate fecha, String numMesa, String nombreMesero, String cuenta, String nomCliente, double total) {
        this.fecha = fecha;
        this.numMesa = numMesa;
        this.nombreMesero = nombreMesero;
        this.cuenta = cuenta;
        this.nomCliente = nomCliente;
        this.total = total;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(String numMesa) {
        this.numMesa = numMesa;
    }

    public String getNombreMesero() {
        return nombreMesero;
    }

    public void setNombreMesero(String nombreMesero) {
        this.nombreMesero = nombreMesero;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
    
}
