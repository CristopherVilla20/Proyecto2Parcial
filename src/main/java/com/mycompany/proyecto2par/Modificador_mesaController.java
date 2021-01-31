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

    private Mesa mesa;  
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
    
    //SETTERS
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    
    //METODOS DEL CONTROLADOR
    @FXML
    private void modificarDatos(MouseEvent event) throws IOException {
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ventanaGestionMesa.fxml"));
        Parent root = fxmlLoader.load();
        VentanaGestionMesaController vgmc = fxmlLoader.getController();
        mesa = vgmc.getMesa();
*/
        try{
            System.out.println(mesa);
            String numero = txtNumeroMesa.getText();
            String capacidad = txtCapacidadMesa.getText();
        
            //Label lb = (Label) spMesa.getChildren().get(1);
            //String nuMesa = lb.getText();
            //System.out.println(nuMesa);                            
            
            for(Mesa m : MesaData.mesas){              
                if(m.getNumeroMesa().equals(mesa.getNumeroMesa())){
                    //setMesa(m);
                    MesaData.eliminarMesa(m);
                    m.setCapacidad(Integer.parseInt(capacidad));
                    m.setNumeroMesa(numero);
                    MesaData.registrarMesa(m);
                    
                }
                
            }
          
        }
        catch(Exception ex){
            System.out.println(mesa);
            ex.printStackTrace();
        }
        
        
        
    }

    
    @FXML
    private void limpiarDatos(MouseEvent evetxtNumeroMesant) {
        txtNumeroMesa.clear();
        txtCapacidadMesa.clear(); 
    }

    

  

}
