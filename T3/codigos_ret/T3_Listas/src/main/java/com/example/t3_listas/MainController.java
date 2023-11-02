package com.example.t3_listas;

import com.example.t3_listas.model.Usuario;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.*;

public class MainController implements Initializable, EventHandler<ActionEvent> {

    @FXML
    private MenuItem menuWarning;
    @FXML
    private MenuItem manuAlert;
    @FXML
    private MenuItem menuConfirmacion;
    @FXML
    private MenuItem menuInput;
    @FXML
    private MenuItem menuChoice;
    @FXML
    private MenuItem menuPerso;
    @FXML
    private CheckMenuItem menuActivar;

    @FXML
    private RadioMenuItem menuDeshabilitar;

    @FXML
    private RadioMenuItem menuHabilitar;

    @FXML
    private MenuItem menuSalir;

    @FXML
    private GridPane parteCentral;

    private ToggleGroup grupoHabilitar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        instancias();
        personalizarMenu();
        acciones();
    }

    private void acciones() {

        menuSalir.setOnAction(this);
        menuWarning.setOnAction(this);
        manuAlert.setOnAction(this);
        menuInput.setOnAction(this);
        menuChoice.setOnAction(this);
        menuConfirmacion.setOnAction(this);
        grupoHabilitar.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                if (t1 != null) {
                    if (((RadioMenuItem) t1).getText().equalsIgnoreCase("habilitar")) {
                        parteCentral.setDisable(false);
                    } else if ((((RadioMenuItem) t1).getText().equalsIgnoreCase("deshabilitar"))) {
                        parteCentral.setDisable(true);
                    }
                } else {
                    grupoHabilitar.selectToggle(toggle);
                }

            }
        });
    }

    private void instancias() {
        grupoHabilitar = new ToggleGroup();
        grupoHabilitar.getToggles().addAll(menuHabilitar, menuDeshabilitar);
    }

    private void personalizarMenu() {
        menuSalir.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("salir.png"))));
        menuActivar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("activar.png"))));
        menuDeshabilitar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("hab.png"))));
        menuHabilitar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("des.png"))));
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        if (menuActivar.isSelected()) {
            // Construidos - Alert
            // ALERTA - WARN - INFO - CONF - INPUT - CHOICE
            Alert dialogoConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            dialogoConfirmacion.setTitle("¿Salir?");
            dialogoConfirmacion.setHeaderText("¿Estas seguro que quieres salir?");

            Optional<ButtonType> respuesta = dialogoConfirmacion.showAndWait();
            if (respuesta.get() == ButtonType.OK) {
                System.exit(0);
            } else if (respuesta.get() == ButtonType.CANCEL) {
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("¿Salir?");
                dialogoInfo.setHeaderText("Continuaras en la aplicacion");
                dialogoInfo.show();
            }

        } else if (actionEvent.getSource() == menuWarning) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Header");
            alert.setContentText("Content");
            alert.show();
        } else if (actionEvent.getSource() == manuAlert) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.show();
        } else if (actionEvent.getSource() == menuConfirmacion) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            ButtonType boton1 = new ButtonType("No me interesa");
            ButtonType boton2 = new ButtonType("Me interesa");
            alert.getButtonTypes().setAll(boton2, boton1, ButtonType.CLOSE);
            Optional<ButtonType> respuesta = alert.showAndWait();
            if (respuesta.get() == ButtonType.APPLY) {
                System.out.println("Seleccionado apply");
            } else if (respuesta.get() == boton1) {
                System.out.println("Seleccionado tal vez");
            }
        } else if (actionEvent.getSource() == menuInput) {
            TextInputDialog dialogoTexto = new TextInputDialog("introduce datos");
            dialogoTexto.setTitle("Titulo");
            dialogoTexto.setHeaderText("Header");
            dialogoTexto.setContentText("Content");
            Optional<String> respuesta = dialogoTexto.showAndWait();
            if (respuesta.isPresent()) {
                System.out.println(respuesta.get());
            }
        } else if (actionEvent.getSource() == menuChoice) {

            ObservableList<Usuario> listaUsuario = FXCollections.observableArrayList();
            listaUsuario.addAll(new Usuario("Usuario1", "Pass1", "Correo1@correo1"),
                    new Usuario("Usuario2", "Pass2", "Correo1@correo2"),
                    new Usuario("Usuario3", "Pass3", "Correo1@correo3"));

            ChoiceDialog<Usuario> dialogoLista = new ChoiceDialog(listaUsuario.get(2),listaUsuario);
            dialogoLista.showAndWait();

            // al seleccionar un usuario, aparece por consola su contraseña
        } else {
            System.exit(0);
        }
    }
}