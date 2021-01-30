/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    
    @FXML
    private void modificarMesa(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("modificador_mesa.fxml"));
            Parent root = fxmlLoader.load();
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
    private void eliminarMesa(MouseEvent event) {
        /*
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("interfazAdministrador.fxml"));
        Parent root = fxmlLoader.load();
        InterfazAdministradorController iac = fxmlLoader.getController();
        //iac.getPanelSuelo().getChildren().remove(mesa);
        //iac.getPanelSuelo2().getChildren().remove(mesa);
        List<Mesa> mesas = MesaData.cargarMesasArchivo();
        //mesas.remove(mesa);
*/    
    }
    
}
