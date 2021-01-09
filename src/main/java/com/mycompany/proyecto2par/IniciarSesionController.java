/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
/**
 * FXML Controller class
 *
 * @author User
 */
public class IniciarSesionController implements Initializable {


    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtContraseña;
    @FXML
    private Button btnIngresar;
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
    private void iniciarSesion(ActionEvent event) {
        if(!txtUsuario.getText().isEmpty() && !txtContraseña.getText().isEmpty()){
            String usuario = txtUsuario.getText();
            String contraseña = txtContraseña.getText();
            
            
            
        }
        
    }

}
