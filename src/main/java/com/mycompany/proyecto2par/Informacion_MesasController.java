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

import javafx.scene.control.Label;
/**
 * FXML Controller class
 *
 * @author ULYSSES CUEVA
 */
public class Informacion_MesasController implements Initializable {


    @FXML
    private Label lbnumeroMesa;
    @FXML
    private Label lbcapacidadMesa;
    @FXML
    private Label lbestadoMesa;
    @FXML
    private Label lbnombreMesero;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public Label getLbnumeroMesa() {
        return lbnumeroMesa;
    }

    public Label getLbcapacidadMesa() {
        return lbcapacidadMesa;
    }

    public Label getLbestadoMesa() {
        return lbestadoMesa;
    }

    public Label getLbnombreMesero() {
        return lbnombreMesero;
    }
    
    
}
