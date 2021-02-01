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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ULYSSES CUEVA
 */
public class InterfazMeseroController implements Initializable {

    @FXML
    private Label lbMesero;
    @FXML
    private Pane panelSuelo;
    @FXML
    private Button btnCerrarSesion;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbMesero.setText("Mesero: " + App.getUser().getNombre());

        List<Mesa> mesas = MesaData.mesas;
        for (Mesa m : mesas) {
            Circle c;
            //true esta ocupada
            if (m.getEstado()) {
                c = new Circle(m.getTamanio(), Color.RED);
            } else {
                c = new Circle(m.getTamanio(), Color.GREEN);

            }
            Label l = new Label(m.getNumeroMesa());
            StackPane st = new StackPane();
            st.getChildren().addAll(c, l);

            panelSuelo.getChildren().add(st);
            st.setLayoutX(m.getUbicacion().getX());
            st.setLayoutY(m.getUbicacion().getY());
            Circle circulito = (Circle) st.getChildren().get(0);
            if (circulito.getFill().equals(Color.GREEN) || circulito.getFill().equals(Color.YELLOW)) {
                st.setOnMouseClicked((MouseEvent event) -> {
                    try {
                        
                        Label lb = (Label) st.getChildren().get(1);

                        FXMLLoader fxml = new FXMLLoader(App.class.getResource("pedidos.fxml"));
                        Parent root = fxml.load();
                        PedidosController pc = fxml.getController();
                        //PedidosController.numeroMesas.add(m.getNumeroMesa());
                        pc.setSpMesa((StackPane) event.getSource());
                        //System.out.println(m.getNumeroMesa());
                        //numeroMesa = m.getNumeroMesa();
                        //System.out.println(numeroMesa);

                        Scene sc = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(sc);
                        stage.show();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                });

            }

        }

    }

    @FXML
    private void cerrarSesion(MouseEvent event) {
        App.setUser(null);
        App.setRoot("iniciarSesion");
        //System.out.println(App.getUser()==null);
    }

    
}
