/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    private ComboBox<String> cbTipoIngreso;
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
    private ComboBox<String> cbTipoNuevo;
    @FXML
    private Button btnModificarPlato;
    @FXML
    private VBox vBoxPanelIngreso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbTipoIngreso.getItems().add("Bebida");
        cbTipoIngreso.getItems().add("Postre");
        
        cbTipoNuevo.getItems().add("Bebida");
        cbTipoNuevo.getItems().add("Postre");
    }    

    @FXML
    private void cerrarSesion(MouseEvent event) {
        App.setUser(null);
        App.setRoot("iniciarSesion");
        
    }

    @FXML
    private void AñadirPlato(MouseEvent event) throws IOException {
        ArrayList<Menu> listaMenu = MenuData.leerMenus();
        System.out.println(listaMenu);
        String nombreP = txtNombreIngreso.getText();
        Double precioP = Double.parseDouble(txtPrecioIngreso.getText());
        String tipoP = cbTipoIngreso.getValue();
        Menu m = new Menu(tipoP, nombreP, precioP);
        if (listaMenu.contains(m)) {
            vBoxPanelIngreso.getChildren().add(new Label("El Plato ya se encuentra en el Menú."));

            /*
            HBox h = new HBox(new Label("El Plato ya se encuentra en el Menú."));
            Stage stage = new Stage();
            stage.setScene(new Scene(h));
            stage.show();
             */
        }
        else{
        MenuData.registrarMenu(m);
        System.out.println("hola");
        }

    }

    @FXML
    private void Limpiar(MouseEvent event) {
    }

    @FXML
    private void ModificarPLato(MouseEvent event) {
    }
    
}
