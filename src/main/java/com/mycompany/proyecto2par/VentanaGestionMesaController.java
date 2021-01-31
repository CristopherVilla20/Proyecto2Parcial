/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class VentanaGestionMesaController implements Initializable {

    private StackPane spMesa;
    
    private Mesa mesa;
    
    @FXML
    private Button btnModificarMesa;
    @FXML
    private Button btnEliminarMesa;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setSpMesa(StackPane mesa) {
        this.spMesa = mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public StackPane getSpMesa() {
        return spMesa;
    }

    public Mesa getMesa() {
        return mesa;
    }
    
    
    @FXML
    private void modificarMesa(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("modificador_mesa.fxml"));            
            Parent root = fxmlLoader.load();
            Modificador_mesaController mmc = fxmlLoader.getController();
            mmc.setMesa(mesa);          
            Scene sc = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(sc);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void eliminarMesa(MouseEvent event) throws IOException {
        try {
            //Object MouseEvent = event.getSource();
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("interfazAdministrador.fxml"));
            Parent root = fxmlLoader.load();
            InterfazAdministradorController iac = fxmlLoader.getController();
            //for(Mesa m : MesaData.mesas){              
                //if(m.getNumeroMesa().equals(mesa.getNumeroMesa())){
                    //setMesa(m);
              
            for (Node node : iac.getPanelSuelo2().getChildren()){
                if(node instanceof StackPane){
                    StackPane st = (StackPane) node;
                    if(st.equals(spMesa)){
                        System.out.println("hola");                        
                        boolean b = iac.getPanelSuelo2().getChildren().remove(st);
                        System.out.println(b);
                        iac.getPanelSuelo().getChildren().remove(st);
                    }
                    
                }
            }        
            MesaData.eliminarMesa(mesa);
            
        } catch (URISyntaxException ex) {
            System.out.println("que tiro flaco, cansado?");
            ex.printStackTrace();
        }
    
    }
    
}
