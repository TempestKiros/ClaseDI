package com.example.t2_botones;

import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button botonComprobar;

    @FXML
    private ToggleButton toggleActivar;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        personalizarBotones();
        acciones();
    }

    private void acciones() {

        botonComprobar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                toggleActivar.setSelected(!toggleActivar.isSelected());
                /*if (toggleActivar.isSelected()){
                    toggleActivar.setSelected(false);
                } else {
                    toggleActivar.setSelected(true);
                }*/
            }
        });
        /*toggleActivar.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                if (t1) {
                    toggleActivar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("apagar.png"))));
                } else {
                    toggleActivar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("encender.png"))));
                }
            }
        });*/

    }

    private void personalizarBotones() {
        botonComprobar.setBackground(null);
        botonComprobar.setText(null);
        botonComprobar.setBorder(null);
        toggleActivar.setBackground(null);
        toggleActivar.setText(null);
        toggleActivar.setBorder(null);
        botonComprobar.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("start.png"))));
        ImageView imageView = new ImageView();
        toggleActivar.setGraphic(imageView);
        imageView.imageProperty().bind(Bindings.when(toggleActivar.selectedProperty())
                .then(new Image(getClass().getResourceAsStream("encender.png")))
                .otherwise(new Image(getClass().getResourceAsStream("apagar.png"))));
        /*toggleActivar.selectedProperty().bind(Bindings.when(imageView.imageProperty()).then(getClass()
                .getResourceAsStream("encender.png")).otherwise(getClass().getResourceAsStream("apagar.ong")));*/
        //imageView.imageProperty().bind(Bindings.);
        //botonComprobar.setGraphic(new ImageView(new Image("https://cdn-icons-png.flaticon.com/256/5331/5331494.png")));
    }
}