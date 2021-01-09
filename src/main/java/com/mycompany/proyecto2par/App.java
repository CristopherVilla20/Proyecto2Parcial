package com.mycompany.proyecto2par;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Usuario user = null;
    /**
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("iniciarSesion"), 987, 720);
        stage.setScene(scene);
        stage.show();
    }

    public static Usuario getUser() {
        return user;
    }

    public static void setUser(Usuario user) {
        App.user = user;
    }   
    
    static void setRoot(String fxml) {
        try{
            scene.setRoot(loadFXML(fxml));
        }
        catch(IOException ex){
            System.out.println("Algo sucedio");
            System.out.println(ex);
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

}