/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author Stalin Garcia
 */
public class InterfazAdministradorController implements Initializable {

    @FXML
    private Button btnConfirmacion;
    @FXML
    private Pane panelSuelo;
    @FXML
    private Pane panelSuelo2;
    @FXML
    private FlowPane panelComidas;
    @FXML
    private TextField txtNombreAgregar;
    @FXML
    private TextField txtPrecioAgregar;
    @FXML
    private TextField txtRutaAgregar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnLimpiarA;
    @FXML
    private TextField txtNombreModi;
    @FXML
    private TextField txtNuevoNombreModi;
    @FXML
    private TextField txtPrecioModi;
    @FXML
    private TextField txtRutaImgModi;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnLimpiarM;
    @FXML
    private ComboBox<String> cbTipoComidaA;
    @FXML
    private ComboBox<String> cbTipoComidaM;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        ponerMesas(panelSuelo);
        ponerMesas(panelSuelo2);
        cbTipoComidaA.getItems().addAll("Postre","Bebida");
        cbTipoComidaM.getItems().addAll("Postre","Bebida");
        
        try {
            System.out.println("antes de leer");
            List<Comida> comidas = Comida.cargarComidasArchivo("comidas.txt");
            System.out.println("lee");
            for(Comida c: comidas){
                VBox contenedor = new VBox();
                InputStream inputImg = App.class.getResource(c.getImagen()).openStream();
                System.out.println("si abre");
                ImageView imgv = new ImageView(new Image(inputImg));
                contenedor.getChildren().add(imgv);
                Label lb1 = new Label(c.getNombre());
                Label lb2 = new Label("$ " +String.valueOf(c.getPrecio()));
                contenedor.getChildren().addAll(lb1,lb2);
                panelComidas.getChildren().add(contenedor);
            }
            
            
        } catch (IOException ex) {
            System.out.println("Aqui es");
            ex.printStackTrace();
        }
        
        
    }    

    @FXML
    private void cerrarSesion(MouseEvent event) {
        App.setUser(null);
        App.setRoot("iniciarSesion");
        
    }

    
    public void ponerMesas(Pane panel){
        try {
            List<Mesa> mesas = Mesa.cargarMesasArchivo("mesas.txt");
            for(Mesa m: mesas){
                Circle c = new Circle();
                //true esta ocupada
                if(m.isEstado()){
                    c = new Circle(30,Color.RED);
                }else{
                    c = new Circle(30,Color.GREEN);
                }
                Label l = new Label(m.getNumeroMesa());
                StackPane st = new StackPane();
                st.getChildren().addAll(c,l);
                
                panel.getChildren().add(st);
                st.setLayoutX(m.getUbicacion().getX());
                st.setLayoutY(m.getUbicacion().getY());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    
        }

    @FXML
    private void añardirMesa(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
         
    }
}
