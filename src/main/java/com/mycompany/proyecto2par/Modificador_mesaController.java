/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
/**
 * FXML Controller class
 *
 * @author ULYSSES CUEVA
 */
public class Modificador_mesaController implements Initializable {


    @FXML
    private TextField txtNumeroMesa;
    @FXML
    private TextField txtCapacidadMesa;
    @FXML
    private Button btnModificarNumero;
    @FXML
    private Button btnModificarLimpiar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private void modificarDatos(MouseEvent event) throws IOException {
        String numero = txtNumeroMesa.getText();
        String capacidad = txtCapacidadMesa.getText();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ventanaGestionMesa.fxml"));
        InterfazAdministradorController iac = fxmlLoader.getController();
        try {
            
        Label lb = (Label)iac.getSpMesa().getChildren().get(1); 
       
        List<Mesa> lmesa = Mesa.cargarMesasArchivo("mesas.txt");
        for (Mesa m : lmesa){
            if (m.getNumeroMesa().equals(lb.getText())){
                m.setNumeroMesa(numero);
                m.setCapacidad(Integer.parseInt(capacidad));
            }
        }
        
        
        }catch (IndexOutOfBoundsException e){
        e.printStackTrace();
        }
        
        
    }
    
    @FXML
    private void limpiarDatos(MouseEvent evetxtNumeroMesant) {
        txtNumeroMesa.clear();
        txtCapacidadMesa.clear(); 
    }

}
