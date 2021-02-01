/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FinalizarOrdenController implements Initializable {

    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtNumeroCuenta;
    @FXML
    private TextField txtNombreCliente;
    @FXML
    private Label lbTotalReporteFinal;

    public Label getLbTotalReporteFinal() {
        return lbTotalReporteFinal;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Se registra la venta en el documento 
     * @param event 
     */
    @FXML
    private void confirmarOrden(MouseEvent event) {
        try {
            FXMLLoader fxml = new FXMLLoader(App.class.getResource("pedidos.fxml"));
            Parent root = fxml.load();
            PedidosController  pc= fxml.getController();
            LocalDate fecha = LocalDate.now();
            String numeroMesa = pc.getNumeroMesa();
            String mesero = App.getUser().getNombre();
            String cuenta = txtNumeroCuenta.getText();
            String cliente = txtNombreCliente.getText();
            double total = pc.getSumatoriatotal();
            
            Venta v = new Venta(fecha,numeroMesa,mesero,cuenta,cliente,total);
            VentasData.registrarVenta(v);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * cierra la ventana "FinalizarOrden"
     * @param event 
     */
    @FXML
    private void cancelarOrden(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}
