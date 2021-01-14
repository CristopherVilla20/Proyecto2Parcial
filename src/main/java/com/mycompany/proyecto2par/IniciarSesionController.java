/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author User
 */
public class IniciarSesionController implements Initializable {


    //private Usuario user = null;

    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContraseña; 
    @FXML
    private Button btnIngresar;
    @FXML
    private Label lbmensajito;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void iniciarSesion(MouseEvent event) throws IOException {
        try {
            if (txtUsuario.getText().isEmpty() || txtContraseña.getText().isEmpty()) {
                throw new NullPointerException();
            }
            String correoUsuario = txtUsuario.getText();
            String contrasenia = txtContraseña.getText();
            ArrayList<Usuario> usuarios = UsuarioData.leerUsuarios();
            for (Usuario u : usuarios) {
                if (u.getCorreo().equals(correoUsuario) && u.getContrasenia().equals(contrasenia)) {
                    App.setUser(u);
                    //System.out.println(App.getUser()!=null);
                    if (u.getPrivilegio().equals("administrador")) {
                        App.setRoot("interfazAdministrador");
                    } else if (u.getPrivilegio().equals("mesero")) {              
                        App.setRoot("interfazMesero");
                    }
                }
            }
            if (App.getUser() == null) {
                lbmensajito.setText("Credenciales Invalidas.");
            }
        } catch (NullPointerException e) {
            lbmensajito.setText("Los campos no pueden estar vacíos.");
        }

    }


}