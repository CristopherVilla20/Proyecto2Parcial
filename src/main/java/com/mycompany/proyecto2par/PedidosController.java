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

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author User
 */
public class PedidosController implements Initializable {


    @FXML
    private Label lbNombreMesa;
    @FXML
    private Label lbTotal;
    @FXML
    private Button btnFinalizarOrden;
    @FXML
    private Button btnRegresar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void finalizarOrden(MouseEvent event) {
    }

    @FXML
    private void regresar(MouseEvent event) {
        App.setRoot("interfazMesero");
    }
    
}
