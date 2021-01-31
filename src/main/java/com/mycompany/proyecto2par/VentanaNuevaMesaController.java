/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
/**
 * FXML Controller class
 *
 * @author User
 */
public class VentanaNuevaMesaController implements Initializable {

    private double x;
    
    private double y;
    
    @FXML
    private TextField txtCapacidadMesa;
    @FXML
    private TextField txtNumeroMesa;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnLimpiarAM;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @FXML
    private void agMesa(MouseEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("interfazAdministrador.fxml"));
            Parent root = fxmlLoader.load();
            InterfazAdministradorController iac = fxmlLoader.getController();
            UbicacionesMesas ub = new UbicacionesMesas(x,y);
            Mesa m = new Mesa(txtNumeroMesa.getText(),Integer.parseInt(txtCapacidadMesa.getText()),ub,false,35);
            MesaData.registrarMesa(m);
            Circle c = new Circle(35,Color.GREEN);
            Label lb1 = new Label(txtNumeroMesa.getText());
            StackPane sp = new StackPane(c,lb1);
            iac.getPanelSuelo2().getChildren().add(sp);
            //iac.ponerMesas(iac.getPanelSuelo());
            //iac.ponerMesas(iac.getPanelSuelo2());     
        }catch(Exception ex){
            ex.getStackTrace();
            System.out.println("algo pasa");
        }

    }

    class ActualizarMesa implements Runnable{

        @Override
        public void run() {
            //ArrayList<Mesa> = MesaData.cargarMesasArchivo();

        }
    }
    
    @FXML
    private void limpiarDatos(MouseEvent event) {
        txtCapacidadMesa.setText("");
        txtNumeroMesa.setText("");
    }

    
}
