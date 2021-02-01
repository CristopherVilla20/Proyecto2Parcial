/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.proyecto2par;



import static com.mycompany.proyecto2par.MesaData.cargarMesasArchivo;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
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
import javafx.geometry.Point2D;
import javafx.scene.control.Tab;
import javafx.scene.effect.Light.Point;

/**
 * FXML Controller class
 *
 * @author Stalin Garcia
 */
public class InterfazAdministradorController implements Initializable {
   
    static boolean agregando=false;
    private ObservableList<Venta> listaVentas = FXCollections.observableArrayList();
    static StackPane spNuevaMesa;
    private int totalComensales;

    public boolean isAgregando() {
        return agregando;
    }

    public void setAgregando(boolean agregando) {
        this.agregando = agregando;
    }
    
    public StackPane getSpNuevaMesa() {
        return spNuevaMesa;
    }

    public void setSpNuevaMesa(StackPane spNuevaMesa) {
        this.spNuevaMesa = spNuevaMesa;
    }

    
    int valedor;
    
    private double initX;
    
    private double initY;
    
    private Point2D posicionInitMouse;
    
    private Point offset;
    
    private String nombrePlato;
    
    private ArrayList<Comida> listaComidasHilo = new ArrayList<>();
     
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
        ponerMesas(panelSuelo);
        ponerMesas(panelSuelo2);
    //HILOS
    
        //Ubicar Dato Comensales (Hilo)
        Thread t = new Thread(new ComensalesRunnable());
        t.start();
        
        //Ubicar Total Facturado (Hilo)
        Thread t2 = new Thread(new TotalRunnable());
        t2.start();
        
        //ActualizarMesa (Hilo)
        //Thread t3 = new Thread(new ActualizarMesasRunnable());
        //t3.start();
        
        //Thread t4 = new Thread(new ActualizarListaMesas());
        //t4.start();
        
        //Thread t5 = new Thread(new ActualizarComidasRunnable());
        //t5.start();    
        
        
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
        ubicarComida();
        
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
   /**
    * Recibe un panel y ubica las mesas segun el panel recibido.
    * @param panel 
    */
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
            //Se crea el stackPane que tendra los datos de la mesa 
            Label l = new Label(m.getNumeroMesa());
            StackPane st = new StackPane();
            st.getChildren().addAll(c, l);
            
            //Se agrega la mesa al panel 
            panel.getChildren().add(st);
            
            //Se ubican las mesas 
            st.setLayoutX(m.getUbicacion().getX());
            st.setLayoutY(m.getUbicacion().getY());
            
            //Manejador que muestra la información de las mesas en la pestaña "MONITOREO"
            //Y las vistas para modificar, agregar y eliminar mesa en la pestaña "DISEÑO PLANO"
            //Según el panel en el que se encuentre el StackPane
            st.setOnMouseClicked(
                    (MouseEvent event) -> {
                        event.consume();
                        if (panel.getId().equals("panelSuelo")) {
                            mostrarInformacionMesa(m);
                        }
                        else{
                            try {                               
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
            ); 
            //solo para el 2do panel            
            if(panel.getId().equals("panelSuelo2")){
                
                //Manejador que asigna el valor en X,Y de la ubicacion del StackPane al darle click
                st.setOnMousePressed((MouseEvent event) -> {                                        initX = st.getLayoutX();
                    initY = st.getLayoutY();
                    posicionInitMouse = new Point2D(event.getSceneX(),event.getSceneY());
                });
                //Manejador de la posicion del StackPane al moverlo
                st.setOnMouseDragged((MouseEvent event)->{                   
                    double dragX = event.getSceneX() - posicionInitMouse.getX();
                    double dragY= event.getSceneY() - posicionInitMouse.getY();
                    
                    double newXPosition = initX + dragX;
                    double newYPosition = initY + dragY;
                    
                    st.setLayoutX(newXPosition);
                    st.setLayoutY(newYPosition);
                    UbicacionesMesas uM = new UbicacionesMesas(newXPosition,newYPosition);
                    try{ 
                    //MesaData.eliminarMesa(m);   
                    //m.setUbicacion(uM);
                    ArrayList<Mesa> aM = MesaData.cargarMesasArchivo();
                    for(Mesa mE: aM){
                        if(mE.getNumeroMesa().equals(m.getNumeroMesa()))
                            mE.setUbicacion(uM);
                    }
                    MesaData.sobreescribirArchivoMesa(aM);
                    }
                    catch(Exception ex){
                        System.out.println(ex.getMessage());                    
                    }                    
                });
                /*
                st.setOnMouseReleased((MouseEvent event)->{                    
                    try {
                        
                        MesaData.registrarMesa(m);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                
                });
                */
            }                                
        }    
    }

    /**
     * Buscador de ventas segun un rango de fecha
     * @param event
     * @throws IOException 
     */
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

    
    /**
     * Permite iniciar una nueva busqueda, limpiando los textfield y ubicando todas
     * las ventas de nuevo.
     * @param event 
     */
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

    /**
     * Abre la vista ventanaNuevaMesa
     * @param event 
     */
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

    /**
     * Muestra la informacion de las mesas al darle click, en el panel de monitoreo
     * @param m 
     */
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
    /**
     * Agrega un nuevo plato al menu
     * @param event 
     */
    @FXML
    private void agregarPlato(MouseEvent event) {

        String nombre = txtNombreAgregar.getText();
        this.nombrePlato = nombre;
        double precio = Double.parseDouble(txtPrecioAgregar.getText());
        String tipo = cbTipoComidaA.getValue();
        String ruta = txtRutaAgregar.getText();
        Comida c = new Comida(nombre, precio, tipo, ruta);
        //Thread t5 = new Thread(new ActualizarComidasRunnable());
        //t5.start();    
        List<Comida> comidas = ComidaData.comidas;
        if (!comidas.contains(c)) {
            try {
                VBox contenedor = new VBox();
                try {
                    InputStream inputImg = App.class.getResource(c.getImagen()).openStream();
                    ImageView imgv = new ImageView(new Image(inputImg));
                    contenedor.getChildren().add(imgv);
                } catch (Exception ex) {
                    //System.out.println(c);
                    ex.printStackTrace();
                }
                Label lb1 = new Label(c.getNombre());
                Label lb2 = new Label("$ " + String.valueOf(c.getPrecio()));
                contenedor.getChildren().addAll(lb1, lb2);
                ComidaData.registrarComida(c);
                panelComidas.getChildren().add(contenedor);
                //ubicarComida();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    /**
     * Limpia los textfield de la seccion AgregarPlato
     * @param event 
     */
    @FXML
    private void limpiarAgregarPlato(MouseEvent event) {
        txtNombreAgregar.clear();
        txtPrecioAgregar.clear();
        txtRutaAgregar.clear();
                
    }
    /**
     * Modifica los datos de un plato y lo presenta por pantalla
     * @param event 
     */
    @FXML
    private void modificarPlato(MouseEvent event) {
        String nombre = txtNombreModi.getText();        
        String nombreNuevo = txtNuevoNombreModi.getText();
        String tipo = cbTipoComidaM.getValue();
        double precio = Double.parseDouble(txtPrecioModi.getText());
        String rutaImg = txtRutaImgModi.getText();       
        ArrayList<Comida> LasComidas = ComidaData.cargarComidasArchivo();
        for (Comida c : LasComidas) {           
            if (c.getNombre().equals(nombre)) {
                c.setNombre(nombreNuevo);
                c.setTipo(tipo);
                c.setPrecio(precio);
                c.setImagen(rutaImg);               
            }
    
        }
        try {
                ComidaData.sobreescribirArchivoComida(LasComidas);
            } catch (URISyntaxException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        if(panelComidas.getChildren()!=null){
            panelComidas.getChildren().clear();
        }
        this.valedor = 0;
        Thread t5 = new Thread(new ActualizarComidasRunnable());
        t5.start(); 
    }
    /**
     * Limpia los textfield se la seccion Modificar Plato
     * @param event 
     */
    @FXML
    private void limpiarModificarPlato(MouseEvent event) {
        txtNombreModi.clear();
        txtNuevoNombreModi.clear();
        txtPrecioModi.clear();
        txtRutaImgModi.clear();
    }
    /**
     * Ubica los platos en la pestania "GESTION MENU"
     */
    public void ubicarComida() {
        
        ArrayList<Comida> lC = ComidaData.cargarComidasArchivo();
        for (Comida c : lC) {
            VBox contenedor = new VBox();
            try {
                InputStream inputImg = App.class.getResource(c.getImagen()).openStream();
                ImageView imgv = new ImageView(new Image(inputImg));
                contenedor.getChildren().add(imgv);
            } catch (Exception ex) {                
                ex.printStackTrace();
            }
            Label lb1 = new Label(c.getNombre());
            Label lb2 = new Label("$ " + String.valueOf(c.getPrecio()));
            contenedor.getChildren().addAll(lb1, lb2);
            panelComidas.getChildren().add(contenedor);
        }
        
    }
    
    //HILOS
    /**
     * Modifica el numero de clientes que se observa en la pestania "MONITOREO"
     * segun el numero de personas por mesa
     */
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
    /**
     * Actualizar total de las ventas que se observa en la pestania "MONITOREO"
     * cada vez que se realiza una venta
     */
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
    /**
     * Actualiza las mesas si se efectua un cambio
     */
    /*
    class ActualizarMesasRunnable implements Runnable {

        @Override
        public void run() {
            while (agregando) {
                try {                    
                    Platform.runLater(()->{
                        agregarMesa(spNuevaMesa);
                        agregando=false;
                    });                    
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
        }
    }
    }
    */
    /**
     * Actualiza las comidas en el panel si se efectua un cambio 
     */  
    
    class ActualizarComidasRunnable implements Runnable {

        @Override
        public void run() {
            while (valedor<1) {
                for (Comida c : ComidaData.cargarComidasArchivo()) {
                    try {
                        VBox contenedor = new VBox();
                        InputStream inputImg = App.class.getResource(c.getImagen()).openStream();
                        ImageView imgv = new ImageView(new Image(inputImg));
                        contenedor.getChildren().add(imgv);
                        Label lb1 = new Label(c.getNombre());
                        Label lb2 = new Label("$ " + String.valueOf(c.getPrecio()));
                        contenedor.getChildren().addAll(lb1, lb2);
                        Platform.runLater(() -> {
                            panelComidas.getChildren().add(contenedor);
                            valedor++;
                            
                        });
                        //Thread.sleep(1000);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    
    //Metodo que se llama dentro del hilo ActualizarMesasRunnable    
    public void agregarMesa(StackPane sp){
            panelSuelo.getChildren().add(sp);
            panelSuelo2.getChildren().add(sp);
                    
            
        }
   
}
