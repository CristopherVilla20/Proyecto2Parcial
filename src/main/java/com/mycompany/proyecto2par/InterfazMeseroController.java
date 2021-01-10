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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

/**
 * FXML Controller class
 *
 * @author ULYSSES CUEVA
 */
public class InterfazMeseroController implements Initializable {

    @FXML
    private Label lbMesero;
    @FXML
    private Circle mesa3;
    @FXML
    private Circle mesa6;
    @FXML
    private Ellipse mesa13;
    @FXML
    private Button btnCerrarSesion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbMesero.setText("Mesero: "+App.getUser().getNombre());    
    
    }    

    @FXML
    private void atenderMesa(MouseEvent event) {
       App.setRoot("pedidos");
        
    }

    @FXML
    private void cerrarSesion(MouseEvent event) {
        App.setUser(null);
        App.setRoot("iniciarSesion");
        //System.out.println(App.getUser()==null);
    }

    
}
