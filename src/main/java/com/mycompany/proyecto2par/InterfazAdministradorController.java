/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.stage.Stage;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

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
    @FXML
    private TableColumn<Venta,LocalDate> cln_fecha;
    @FXML
    private TableColumn<Venta,String> cln_mesa;
    @FXML
    private TableColumn<Venta,String> cln_mesero;
    @FXML
    private TableColumn<Venta,String> cln_cuenta;
    @FXML
    private TableColumn<Venta,String> cln_cliente;
    @FXML
    private TableColumn<Venta,Double> cln_total;
    @FXML
    private TableView<Venta> tabla;
    
    private ObservableList<Venta> listaVentas = FXCollections.observableArrayList();
    
    @FXML
    private TextField txt_dateIni;
    @FXML
    private TextField txt_dateFin;
    @FXML
    private Button btn_buscar;
    @FXML
    private Button btn_refrescar;
    @FXML
    private Label lbl_mensajeReporteVenta;
    
    
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ponerMesas(panelSuelo);
        ponerMesas(panelSuelo2);
        cbTipoComidaA.getItems().addAll("Postre", "Bebida");
        cbTipoComidaM.getItems().addAll("Postre", "Bebida");

        //VentasReporte
        ArrayList<Venta> ventas = VentasData.leerVentas();
        for (Venta v : ventas) {
            listaVentas.add(v);
        }

        cln_fecha.setCellValueFactory(new PropertyValueFactory<Venta, LocalDate>("fecha"));
        cln_mesa.setCellValueFactory(new PropertyValueFactory<Venta, String>("numMesa"));
        cln_mesero.setCellValueFactory(new PropertyValueFactory<Venta, String>("nombreMesero"));
        cln_cuenta.setCellValueFactory(new PropertyValueFactory<Venta, String>("cuenta"));
        cln_cliente.setCellValueFactory(new PropertyValueFactory<Venta, String>("nomCliente"));
        cln_total.setCellValueFactory(new PropertyValueFactory<Venta, Double>("total"));

        tabla.setItems(listaVentas);

        /*
        try {
            //System.out.println("antes de leer");
            List<Comida> comidas = ComidaData.cargarComidasArchivo();
            //System.out.println("lee");
            for(Comida c: comidas){
                VBox contenedor = new VBox();
                InputStream inputImg = App.class.getResource(c.getImagen()).openStream();
                //System.out.println("si abre");
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
         */
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
                Circle c; 
                //true esta ocupada
                if(m.getEstado()){
                    c = new Circle(m.getTamanio(),Color.RED);
                }else{
                    c = new Circle(m.getTamanio(),Color.GREEN);
                    
                }
                Label l = new Label(m.getNumeroMesa());
                StackPane st = new StackPane();
                st.getChildren().addAll(c,l);
                
                panel.getChildren().add(st);
                st.setLayoutX(m.getUbicacion().getX());
                st.setLayoutY(m.getUbicacion().getY());
                /*
                st.setOnMouseClicked(
                   (MouseEvent event)-> {
                       mostrarInformacionMesa(m);
                   }
                
                );
                */
                st.setOnMouseClicked(new ManejadorInfoMesa ());
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
    
    @FXML
    private void buscarReporteVentas(MouseEvent event) throws IOException {

        try {
            if (txt_dateIni.getText().isEmpty() || txt_dateFin.getText().isEmpty()) {
                throw new NullPointerException();
            }
            lbl_mensajeReporteVenta.setText(null);
            LocalDate fechaIni = LocalDate.parse(txt_dateIni.getText());
            LocalDate fechaFin = LocalDate.parse(txt_dateFin.getText());
            listaVentas.clear();
            ArrayList<Venta> ventas = VentasData.leerVentas();
            for (Venta v : ventas) {
                if (v.getFecha().isAfter(fechaIni) && v.getFecha().isBefore(fechaFin)) {
                    listaVentas.add(v);
                }
            }
            tabla.setItems(listaVentas);
            if (listaVentas.isEmpty()) {
                lbl_mensajeReporteVenta.setText("No existen ventas dentro del rango.");
            }
        } 
        catch(DateTimeParseException e){
            lbl_mensajeReporteVenta.setText("Ingrese la fecha en el siguiente formato (AAAA-MM-DD).");
        }
        catch (NullPointerException e) {
            lbl_mensajeReporteVenta.setText("Los campos no pueden estar vacíos.");
        } 
    }
    

    @FXML
    private void refrescarBusquedaReporteVentas(MouseEvent event) {
        listaVentas.clear();
        txt_dateIni.clear();
        txt_dateFin.clear();
        lbl_mensajeReporteVenta.setText(null);
        ArrayList<Venta> ventas = VentasData.leerVentas();
        for (Venta v : ventas) {
            listaVentas.add(v);
        }
        tabla.setItems(listaVentas);
    }
    
    /*
    private void mostrarInformacionMesa (Mesa m ){
        try {
            FXMLLoader fxmlLoader
            = new FXMLLoader(App.class.getResource("informacion_Mesas"));
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root);
            Stage stage = new Stage();
            Informacion_MesasController imc = fxmlLoader.getController();
            imc.getLbcapacidadMesa().setText(String.valueOf(m.getCapacidad()));
            imc.getLbestadoMesa().setText(String.valueOf(m.getEstado()));
            imc.getLbnumeroMesa().setText(String.valueOf(m.getNumeroMesa()));
            imc.getLbnombreMesero().setText("Mesero");
            
            stage.show();
            
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    */
    
    private class ManejadorInfoMesa implements EventHandler<MouseEvent> {

        public void handle(MouseEvent event) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("informacion_Mesas"));
                Parent root = fxmlLoader.load();
                Scene sc = new Scene(root);
                Stage stage = new Stage();

                Informacion_MesasController imc = fxmlLoader.getController();
                for (Node n : panelSuelo.getChildren()) {
                    if (event.getSource().equals(n)) {
                        StackPane sp = (StackPane) n;
                        Label lb = (Label) sp.getChildren().get(1);
                        List<Mesa> mesas = Mesa.cargarMesasArchivo("mesas.txt");
                        for (Mesa m : mesas) {
                            if (String.valueOf(m.getNumeroMesa()).equals(lb.getText())) {
                                imc.getLbcapacidadMesa().setText(String.valueOf(m.getCapacidad()));
                                imc.getLbestadoMesa().setText(String.valueOf(m.getEstado()));
                                imc.getLbnumeroMesa().setText(String.valueOf(m.getNumeroMesa()));
                            }

                        }
                    }

                }

                imc.getLbnombreMesero().setText("Mesero");

                stage.setScene(sc);
                stage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }
}

