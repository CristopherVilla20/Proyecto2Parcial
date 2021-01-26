/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author User
 */
public class PedidosController implements Initializable {


    @FXML
    private Label lbNombreMesa;
    @FXML
    private Label lbTotal;
    @FXML
    private Button btnFinalizarOrden;
    @FXML
    private Button btnRegresar;
    @FXML
    private FlowPane panelPostres;
    @FXML
    private FlowPane panelBebidas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Comida> comidas = ComidaData.cargarComidasArchivo();
            //System.out.println("lee");
            for(Comida c: comidas){
                String tipo = c.getTipo();
                VBox contenedorPostres = new VBox();
                VBox contenedorBebidas = new VBox();
                
                try{
                InputStream inputImg = App.class.getResource(c.getImagen()).openStream();
                //System.out.println("si abre");
                ImageView imgv = new ImageView(new Image(inputImg));
                Label lb1 = new Label(c.getNombre());
                Label lb2 = new Label("$ " +String.valueOf(c.getPrecio()));
                if(tipo.equals("Postre")){
                    contenedorPostres.getChildren().addAll(imgv,lb1,lb2);
                
                }else{contenedorBebidas.getChildren().addAll(imgv,lb1,lb2);}
                
                
                
                }catch(Exception ex){
                    System.out.println(c);
                    ex.printStackTrace();
                }
                panelPostres.getChildren().add(contenedorPostres);
                panelBebidas.getChildren().add(contenedorBebidas);
                
            }
    }    

    @FXML
    private void finalizarOrden(MouseEvent event) {
    }

    @FXML
    private void regresar(MouseEvent event) {
        App.setRoot("interfazMesero");
    }
    
}
