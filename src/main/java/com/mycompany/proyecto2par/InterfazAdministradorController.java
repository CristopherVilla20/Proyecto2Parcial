/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Stalin Garcia
 */
public class InterfazAdministradorController implements Initializable {

    @FXML
    private Button btnConfirmacion;
    @FXML
    private TextField txtNombreIngreso;
    @FXML
    private TextField txtPrecioIngreso;
    @FXML
    private ComboBox<?> cbTipoIngreso;
    @FXML
    private Button btnAñadirPlato;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TextField txtNombrePlato;
    @FXML
    private TextField txtNombreNuevo;
    @FXML
    private TextField txtPrecioNuevo;
    @FXML
    private ComboBox<?> cbTipoNuevo;
    @FXML
    private Button btnModificarPlato;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void cerrarSesion(MouseEvent event) {
        App.setUser(null);
        App.setRoot("iniciarSesion");
    }

    @FXML
    private void AñadirPlato(MouseEvent event) {
    }

    @FXML
    private void Limpiar(MouseEvent event) {
    }

    @FXML
    private void ModificarPLato(MouseEvent event) {
    }
    
}
