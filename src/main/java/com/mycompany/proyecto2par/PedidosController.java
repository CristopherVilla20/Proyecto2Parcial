/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author User
 */
public class PedidosController implements Initializable {
    
    private double sumatoriatotal;
   
    private String numeroMesa;
    
    private ArrayList<Node> listaNodos = new ArrayList<Node>();
    
    private VBox contenedor = new VBox();
    
    private StackPane spMesa ;
   

    public void setSpMesa(StackPane spMesa) {
        this.spMesa = spMesa;
    }

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
    @FXML
    private VBox fpPanelElegidos;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //FXMLLoader fxml = new FXMLLoader(App.class.getResource("interfazMesero.fxml"));
        //Parent root = fxml.load();
        //InterfazMeseroController imc = fxml.getController();
        System.out.println(spMesa);
        lbNombreMesa.setText("Mesa: " + 5);
        //System.out.println(imc.getNumeroMesa());
        //setNumeroMesa(imc.getNumeroMesa());
        List<Comida> comidas = ComidaData.comidas;
        //System.out.println("lee");
        for (Comida c : comidas) {

            try {
                String tipo = c.getTipo();
                VBox contenedorPostres = new VBox();
                VBox contenedorBebidas = new VBox();
                InputStream inputImg = App.class.getResource(c.getImagen()).openStream();
                //System.out.println("si abre");
                ImageView imgv = new ImageView(new Image(inputImg));
                Label lb1 = new Label(c.getNombre());
                Label lb2 = new Label("$ " + String.valueOf(c.getPrecio()));
                if (tipo.equals("Postre")) {
                    contenedorPostres.getChildren().addAll(imgv, lb1, lb2);

                } else {
                    contenedorBebidas.getChildren().addAll(imgv, lb1, lb2);
                }
                panelPostres.getChildren().add(contenedorPostres);
                panelBebidas.getChildren().add(contenedorBebidas);
                 
                 
                contenedorPostres.setOnMouseClicked((MouseEvent event) -> {
                   
                    int cantidad = 1;
                    Label nombreComida = new Label();
                    nombreComida.setText(c.getNombre());
                    Label lbCosto = new Label();
                    lbCosto.setText(c.getPrecio() + "");
                    Label lbCantidad = new Label();
                    lbCantidad.setText(cantidad + "");
                   
                    contenedor.getChildren().addAll(nombreComida, lbCosto, lbCantidad);
                   
                    if(!listaNodos.contains(contenedor)){
                        
                        listaNodos.add(contenedor);
                       
                        //System.out.println(contenedor);
                    }
                    
                    
                    for(Node n:listaNodos){
                        if(!fpPanelElegidos.getChildren().contains(n)){
                            //System.out.println(n);
                            fpPanelElegidos.getChildren().add(n);
                        }
                        else{
                            System.out.println("ya lo contiene");}
                    }
                    
                   
                         
                    
                    /*
                    if (fpPanelElegidos.getChildren().size() > 0) {
                        VBox cajaV = (VBox) event.getSource();
                        Label lbNombreFood = (Label) cajaV.getChildren().get(1);
                        String nombreFood = lbNombreFood.getText();
                        for (Node n : fpPanelElegidos.getChildren()) {                                                       
                            VBox vB = (VBox) n;
                            Label lbXD = (Label) vB.getChildren().get(0);
                            String nombreVB = lbXD.getText();                            
                            if (nombreVB.equals(nombreFood)){
                                Label lbCant = (Label) vB.getChildren().get(2);
                                int cant1 = Integer.parseInt(lbCant.getText());
                                lbCant.setText(String.valueOf(cant1 + 1));
                                System.out.println("Cambia la cantidad");
                            }
                            else if(!nombreVB.equals(nombreFood)){                                
                                fpPanelElegidos.getChildren().add(contenedor);
                                System.out.println("no esta en el panel");
                                Label lbCant = (Label) vB.getChildren().get(2);
                                int cant1 = Integer.parseInt(lbCant.getText());
                                lbCant.setText(String.valueOf(cant1 + 1));
                                System.out.println("Cambia la cantidad");
                                
                            }
                        }
                                               
                    }else {
                        fpPanelElegidos.getChildren().add(contenedor);
                        System.out.println("Primer contenedor Agregado");

                    }
                    */
                    sumatoriatotal += c.getPrecio();
                    lbTotal.setText("Total:" + sumatoriatotal);

                });
                /*
                contenedorBebidas.setOnMouseClicked((MouseEvent e) -> {
                    int cantidad = 0;
                    Label nombreComida = new Label();
                    nombreComida.setText(c.getNombre());
                    Label lbCosto = new Label();
                    lbCosto.setText(c.getPrecio() + "");
                    sumatoriatotal += c.getPrecio();

                    Label lbCantidad = new Label();
                    cantidad += 1;
                    lbCantidad.setText(cantidad + "");
                    VBox contenedor = new VBox();
                    lbTotal.setText("Total:" + sumatoriatotal);
                    contenedor.getChildren().addAll(nombreComida, lbCosto, lbCantidad);
                    fpPanelElegidos.getChildren().add(contenedor);
                });
*/
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

       
        

    @FXML
    private void finalizarOrden(MouseEvent event) {
        try {
            FXMLLoader fxml = new FXMLLoader(App.class.getResource("finalizarOrden.fxml"));
            Parent root = fxml.load();
            FinalizarOrdenController foc = fxml.getController();
            foc.getLbTotalReporteFinal().setText(sumatoriatotal+"");
            Scene sc = new Scene(root);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();
            st.setResizable(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }

    @FXML
    private void regresar(MouseEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    


    public double getSumatoriatotal() {
        return sumatoriatotal;
    }

    public String getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(String numeroMesa) {
        this.numeroMesa = numeroMesa;
    }
    
    
}
