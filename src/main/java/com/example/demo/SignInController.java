package com.example.demo;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class SignInController {

    @FXML
    private JFXButton Close;

    @FXML
    private JFXButton Register;

    @FXML
    private Label accountCreated;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    @FXML
    private Label usernameNotExist;

    @FXML
    void OnRegisterBtnClicked(ActionEvent event) throws SQLException, IOException {
        User user = new User(username.getText(),password.getText());
        try{
            user.addUser();
            accountCreated.setText("Account Created");
            usernameNotExist.setVisible(false);


        }
        catch (Exception e ){
            usernameNotExist.setText("username already exist");
        }




    }
    @FXML
    void OnCloseBtnClicked(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("LogIn.fxml");

    }

}


