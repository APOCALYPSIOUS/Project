package com.example.demo;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {

    @FXML
    private JFXButton LogOut;

    @FXML
    void OnLogOutBtnClicked(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("LogIn.fxml");

    }

}
