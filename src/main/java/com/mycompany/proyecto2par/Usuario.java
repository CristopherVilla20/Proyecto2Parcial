/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

/**
 *
 * @author User
 */
public class Usuario {
    private String correo;
    private String contrasenia;
    private String privilegio;

    public Usuario(String privilegio, String correo, String contrasenia) {
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.privilegio = privilegio;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getPrivilegio() {
        return privilegio;
    }

    public void setPrivilegio(String privilegio) {
        this.privilegio = privilegio;
    }
    
    
    
}
