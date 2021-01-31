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
import javafx.application.Platform;
import javafx.scene.control.Tab;

/**
 * FXML Controller class
 *
 * @author Stalin Garcia
 */
public class InterfazAdministradorController implements Initializable {

    private ObservableList<Venta> listaVentas = FXCollections.observableArrayList();
    
    private int totalComensales;
    
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
    @FXML
    private Tab pestañaMonitoreo;
    @FXML
    private Tab pestañaDiseño;
    @FXML
    private Label lbNumeroComensales;
    @FXML
    private Label lbTotalFacturado;
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //ponerMesas(panelSuelo);
        //ponerMesas(panelSuelo2);
    
    //HILOS
    
         //Ubicar Dato Comensales (Hilo)
        Thread t = new Thread(new ComensalesRunnable());
        t.start();
        
        //Ubicar Total Facturado (Hilo)
        Thread t2 = new Thread(new TotalRunnable());
        t2.start();
        
        //ActualizarMesa (Hilo)
        Thread t3 = new Thread(new ActualizarMesasRunnable());
        t3.start();
        
    //VENTAS

        //Ubicar ventas (VentasReporte)
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
        
    //GESTION COMIDA
    
        //Ubicar Tipo Comida (Combo Box - Gestion de menu)
        cbTipoComidaA.getItems().addAll("Postre", "Bebida");
        cbTipoComidaM.getItems().addAll("Postre", "Bebida");
       
        //Ubicar comida (Gestion Menu)
        List<Comida> comidas = ComidaData.cargarComidasArchivo();
         
        for(Comida c: comidas){
            VBox contenedor = new VBox();
            try{
                InputStream inputImg = App.class.getResource(c.getImagen()).openStream();           
                ImageView imgv = new ImageView(new Image(inputImg));
                contenedor.getChildren().add(imgv);
                }
            catch(Exception ex){
                    //System.out.println(c);
                    ex.printStackTrace();
                }
            Label lb1 = new Label(c.getNombre());
            Label lb2 = new Label("$ " +String.valueOf(c.getPrecio()));
            contenedor.getChildren().addAll(lb1,lb2);
            panelComidas.getChildren().add(contenedor);
        }       
    }
    
    //GETTERS
    
    public Pane getPanelSuelo(){
        return panelSuelo;
    }
    
    public Pane getPanelSuelo2(){
        return panelSuelo2;
    }
    
    @FXML
    private void cerrarSesion(MouseEvent event) {
        App.setUser(null);
        App.setRoot("iniciarSesion");
        
    }
    
    //METODOS ADMINISTRADOR
   
    public void ponerMesas(Pane panel) {
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
                        
            panel.getChildren().add(st);
            st.setLayoutX(m.getUbicacion().getX());
            st.setLayoutY(m.getUbicacion().getY());
            
            st.setOnMouseClicked(
                    (MouseEvent event) -> {
                        event.consume();
                        if (panel.getId().equals("panelSuelo")) {
                            mostrarInformacionMesa(m);
                        }
                        else {
                            try {
                                
                                FXMLLoader fxmlLoader2 = new FXMLLoader(App.class.getResource("modificador_mesa.fxml"));
                                Parent root2 = fxmlLoader2.load();
                                Modificador_mesaController mmc = fxmlLoader2.getController();
                                //mmc.setMesa(m);
                                mmc.setSpMesa((StackPane)event.getSource());
                                
                                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ventanaGestionMesa.fxml"));
                                Parent root = fxmlLoader.load();
                                VentanaGestionMesaController vgmc = fxmlLoader.getController();
                                vgmc.setMesa(m);
                                vgmc.setSpMesa(st);                                                                                                                              
                                Scene sc = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(sc);
                                stage.setResizable(false);
                                stage.show();
                                
                                
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
            ); st.setOnMouseDragged(
                    (MouseEvent event) -> {
                        
                        
                    });
        }

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

    @FXML
    private void abrirPanelAM(MouseEvent event) {
        //panelSuelo2.getChildren();
        if (!(event.getSource() instanceof StackPane)) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("ventanaNuevaMesa.fxml"));
                Parent root = fxmlLoader.load();
                VentanaNuevaMesaController vNmC = fxmlLoader.getController();
                vNmC.setX(event.getX());
                vNmC.setY(event.getY());
                Scene sc = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(sc);
                stage.setResizable(false);
                stage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    
    private void mostrarInformacionMesa(Mesa m) {
        try {
            FXMLLoader fxmlLoader
                    = new FXMLLoader(App.class.getResource("informacion_Mesas.fxml"));
            Parent root = fxmlLoader.load();
            Scene sc = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(sc);
            Informacion_MesasController imc = fxmlLoader.getController();
            imc.getLbcapacidadMesa().setText("Capacidad: " + String.valueOf(m.getCapacidad()));
            String estado = (m.getEstado()) ? " Ocupado" : " Disponible";
            imc.getLbestadoMesa().setText("Estado: " + estado);
            imc.getLbnumeroMesa().setText("Numero de mesa: " + String.valueOf(m.getNumeroMesa()));
            imc.getLbnombreMesero().setText("Mesero: ");

            stage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    class ComensalesRunnable implements Runnable {

        @Override
        public void run() {
            try {
                List<Mesa> mesas = MesaData.mesas;
                for (Mesa m : mesas) {
                    if (m.getEstado()) {
                        totalComensales += m.getCapacidad();
                        Platform.runLater(() -> {
                            lbNumeroComensales.setText("Numero de comensales: " + totalComensales);
                        });

                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }

    }

    class TotalRunnable implements Runnable {

        @Override
        public void run() {
            try {
                ArrayList<Venta> ventas = VentasData.leerVentas();
                double total = 0;
                for (Venta v : ventas) {
                    total += v.getTotal();
                    lbTotalFacturado.setText("Total facturado: " + "$" + total);

                }
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    class ActualizarMesasRunnable implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Platform.runLater(()->{
                    ponerMesas(panelSuelo);
                    ponerMesas(panelSuelo2);
                    });
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


}
