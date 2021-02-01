/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import static com.mycompany.proyecto2par.InterfazAdministradorController.agregando;
import static com.mycompany.proyecto2par.InterfazAdministradorController.spNuevaMesa;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
    
    /**
     * modifica los datos de la mesa seleccionada
     * @param event
     * @throws IOException 
     */
    @FXML
    private void modificarDatos(MouseEvent event) throws IOException {
        
        try{
            System.out.println(mesa);
            String numero = txtNumeroMesa.getText();
            String capacidad = txtCapacidadMesa.getText();
            for(Mesa m : MesaData.mesas){              
                if(m.getNumeroMesa().equals(mesa.getNumeroMesa())){                    
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
    /**
     * Limpia los textfield de la ventana "modificador_mesa"
     */
    private void limpiarDatos(MouseEvent evetxtNumeroMesant) {
        txtNumeroMesa.clear();
        txtCapacidadMesa.clear(); 
    }

    
    

  

}
